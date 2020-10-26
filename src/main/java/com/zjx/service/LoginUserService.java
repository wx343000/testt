package com.zjx.service;

import com.zjx.entity.LoginUser;

public interface LoginUserService {
    LoginUser selectLoginUser(String username);

    void insertLoginUser(LoginUser loginUser) throws Exception;
}
