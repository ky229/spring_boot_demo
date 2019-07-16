package com.wky.spring_boot.mybaits.service.user;

import com.wky.spring_boot.mybaits.dto.TUser;

public interface IUserService {

    public String creatByMybatis(String userName, String passWord);

    public TUser selectByName(String userName);

}
