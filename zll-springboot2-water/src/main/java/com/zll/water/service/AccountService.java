package com.zll.water.service;

import org.apache.ibatis.annotations.Param;

public interface AccountService {
    boolean login(@Param("userName") String userName, @Param("userPwd") String userPwd);

    int updateUserPwd(String userName, String newUserPwd);
}
