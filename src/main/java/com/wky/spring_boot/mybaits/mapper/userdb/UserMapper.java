package com.wky.spring_boot.mybaits.mapper.userdb;

import com.wky.spring_boot.mybaits.dto.TUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Insert("insert into t_user (user_name,pass_word) VALUES(#{userName},#{passWord})")
    public void create(@Param("userName") String userName, @Param("passWord") String passWord);

    @Select("select *  from t_user where user_name = #{userName}")
    public TUser selectByUserName(@Param("userName") String userName);
}
