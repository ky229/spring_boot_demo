package com.wky.spring_boot.mybaits.datasource.db;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.wky.spring_boot.mybaits.datasource.dbconfig.AdminDBConfig;
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
@MapperScan(basePackages="com.wky.spring_boot.mybaits.mapper.admindb",sqlSessionFactoryRef="adminSqlSessionFactory")
@EnableTransactionManagement
public class AdminDB {

    // 配置数据源
    @Bean(name = "adminSource")
    public DataSource adminDataSource(AdminDBConfig adminConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(adminConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(adminConfig.getPassword());
        mysqlXaDataSource.setUser(adminConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("adminDataSource");

        xaDataSource.setMinPoolSize(adminConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(adminConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(adminConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(adminConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(adminConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(adminConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(adminConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(adminConfig.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "adminSqlSessionFactory")
    public SqlSessionFactory adminSqlSessionFactory(@Qualifier("adminSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "adminSqlSessionTemplate")
    public SqlSessionTemplate adminSqlSessionTemplate(
            @Qualifier("adminSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = "adminTransactionManager")
//    @Primary    //设置为主事务管理
    public DataSourceTransactionManager transactionManager(@Qualifier("adminSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}

