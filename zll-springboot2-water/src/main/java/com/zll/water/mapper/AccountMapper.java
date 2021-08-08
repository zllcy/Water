package com.zll.water.mapper;

import com.zll.water.entities.Account;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface AccountMapper {

    Account login(@Param("userName") String userName, @Param("userPwd") String userPwd);

    @Update("update account set user_pwd = #{newUserPwd} where user_name = #{userName}")
    int updateUserPwd(@Param("userName") String userName, @Param("newUserPwd") String newUserPwd);
}
