package com.wky.spring_boot.mybaits.dto;

import lombok.Data;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Data   //lombok 插件，自动生成get,set,toString方法
public class TUser {
    private Integer userId;
    private String userName;
    private String passWord;
}
