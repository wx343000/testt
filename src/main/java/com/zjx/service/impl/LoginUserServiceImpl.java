package com.zjx.service.impl;

import com.zjx.dao.LoginUserDao;
import com.zjx.entity.LoginUser;
import com.zjx.service.LoginUserService;

import javax.annotation.Resource;

public class LoginUserServiceImpl implements LoginUserService {
    @Resource(name = "loginUserDao")
    private LoginUserDao loginUserDao;

    public LoginUser selectLoginUser(String username) {
        LoginUser loginUser = loginUserDao.selectLoginUser(username);
        return loginUser;
    }

    public void insertLoginUser(LoginUser loginUser) throws Exception {
        loginUserDao.insertLoginUser(loginUser);
    }
}
