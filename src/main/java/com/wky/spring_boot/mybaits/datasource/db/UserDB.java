package com.wky.spring_boot.mybaits.datasource.db;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.wky.spring_boot.mybaits.datasource.dbconfig.UserDBConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration//注解到springboot容器中
@MapperScan(basePackages="com.wky.spring_boot.mybaits.mapper.userdb",sqlSessionFactoryRef="userSqlSessionFactory")
@EnableTransactionManagement
public class UserDB {

    // 配置数据源
    @Primary
    @Bean(name = "userSource")
    public DataSource userDataSource(UserDBConfig userConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(userConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(userConfig.getPassword());
        mysqlXaDataSource.setUser(userConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("userDataSource");

        xaDataSource.setMinPoolSize(userConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(userConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(userConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(userConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(userConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(userConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(userConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(userConfig.getTestQuery());
        return xaDataSource;
    }

    /**
     * @return 返回user数据库的会话工厂
     */
    @Bean(name = "userSqlSessionFactory")
    public SqlSessionFactory userSqlSessionFactory(@Qualifier("userSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    /**
     * @return 返回user数据库的会话模板
     */
    @Bean(name = "userSqlSessionTemplate")
    public SqlSessionTemplate userSqlSessionTemplate(
            @Qualifier("userSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * @return 返回user数据库的事务
     */
    @Bean(name = "userTransactionManager")
    @Primary    //设置为主事务管理
    public DataSourceTransactionManager transactionManager(@Qualifier("userSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}

