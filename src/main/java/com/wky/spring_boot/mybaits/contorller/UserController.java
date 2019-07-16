package com.wky.spring_boot.mybaits.contorller;


import com.wky.spring_boot.mybaits.dto.TUser;
import com.wky.spring_boot.mybaits.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    IUserService userService;

    @RequestMapping("creat")
    public String creatUser(String userName, String passWord){
        String status = userService.creatByMybatis(userName, passWord);
        return "用户： " + userName + "  注册状态： " +   status;
    }

    @RequestMapping("selectByName")
    public TUser selectByName(String userName){
        return userService.selectByName(userName);
    }

}
