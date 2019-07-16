package com.wky.spring_boot.mybaits.service.user.impl;


import com.wky.spring_boot.mybaits.dto.TUser;
import com.wky.spring_boot.mybaits.mapper.userdb.UserMapper;
import com.wky.spring_boot.mybaits.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public  class UserServiceIpml implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public String creatByMybatis(String userName, String passWord){
        try {
            userMapper.create(userName,passWord);
            return "通过";
        }catch (Exception e){
            e.printStackTrace();
            return "未通过";
        }
    };

    @Override
    public TUser selectByName(String userName) {
        TUser TUser = userMapper.selectByUserName(userName);
        return TUser;
    }
}
