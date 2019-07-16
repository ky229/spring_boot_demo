package com.wky.spring_boot.mybaits.datasource.dbconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("mysql.datasource.admindb")    //读取配置前缀
@Data   //lombok 插件，自动生成get,set,toString方法
public class AdminDBConfig {
    private String url;
    private String username;
    private String password;
    private int minPoolSize;
    private int maxPoolSize;
    private int maxLifetime;
    private int borrowConnectionTimeout;
    private int loginTimeout;
    private int maintenanceInterval;
    private int maxIdleTime;
    private String testQuery;
}
