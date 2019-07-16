package com.wky.spring_boot.mybaits.mapper.admindb;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminMapper {

    @Insert("insert into t_admin (admin_name,admin_password) VALUES(#{adminName},#{adminPassword})")
    public void create(@Param("adminName") String adminName, @Param("adminPassword") String adminPassword);
    
}
