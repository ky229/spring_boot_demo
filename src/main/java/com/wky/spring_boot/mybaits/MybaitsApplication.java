package com.wky.spring_boot.mybaits;

import com.wky.spring_boot.mybaits.datasource.dbconfig.AdminDBConfig;
import com.wky.spring_boot.mybaits.datasource.dbconfig.UserDBConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@MapperScan("com.wky.spring_boot.mybaits.mapper")
@EnableConfigurationProperties(value = {UserDBConfig.class, AdminDBConfig.class})
public class MybaitsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybaitsApplication.class, args);
	}

}
