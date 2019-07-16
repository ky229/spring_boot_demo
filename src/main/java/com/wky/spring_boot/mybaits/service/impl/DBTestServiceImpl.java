package com.wky.spring_boot.mybaits.service.impl;

import com.wky.spring_boot.mybaits.service.IDBTestService;
import com.wky.spring_boot.mybaits.service.admin.IAdminService;
import com.wky.spring_boot.mybaits.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DBTestServiceImpl implements IDBTestService {
    @Autowired
    IUserService userService;
    @Autowired
    IAdminService adminService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String createUserAndAdmin(String userName, String userPassword, String adminName, String adminPassword) {
        String statusUser = "", statusAdmin = "";
        statusAdmin = adminService.create( adminName,  adminPassword);
        statusUser = userService.creatByMybatis(userName, userPassword);
        Integer zero = 10 / 0;
        return "用户： " + userName + "  注册状态： " +   statusUser +" 管理员： " + adminName + "  注册状态： " +   statusAdmin;
    }
}
