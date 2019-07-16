package com.wky.spring_boot.mybaits.contorller;

import com.wky.spring_boot.mybaits.service.IDBTestService;
import com.wky.spring_boot.mybaits.service.admin.IAdminService;
import com.wky.spring_boot.mybaits.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("dbtest")
public class DBTestContrller {
    @Autowired
    private IDBTestService dbTestService;

    @RequestMapping("creat")
    public String creatUser(String userName, String userPassword,String adminName, String adminPassword){
       return dbTestService.createUserAndAdmin(userName,userPassword,adminName,adminPassword);
    }
}
