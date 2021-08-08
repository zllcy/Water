package com.zll.water.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.zll.water.entities.Account;
import com.zll.water.mapper.AccountMapper;
import com.zll.water.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public boolean login(String userName, String userPwd) {
        String userPwd2 = SecureUtil.md5(userPwd);
        Account account = accountMapper.login(userName, userPwd);
        if (account == null) {
            return false;
        }

        if (userPwd2.equals(account.getUserPwd())) {
            return true;
        }
        return false;
    }

    @Override
    public int updateUserPwd(String userName, String newUserPwd) {
        newUserPwd = SecureUtil.md5(newUserPwd);
        return accountMapper.updateUserPwd(userName, newUserPwd);
    }
}
