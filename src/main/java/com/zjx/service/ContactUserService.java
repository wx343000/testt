package com.zjx.service;

import com.zjx.client.Contact;
import com.zjx.entity.ContactUser;
import com.zjx.vo.ContactUserVo;

import java.util.List;

public interface ContactUserService {
    List<ContactUserVo> selectAllUser();

    String insertUser(ContactUser contactUser);

    String deleteUser(ContactUser contactUser);

    String updateUser(ContactUser contactUser);

    List<ContactUserVo> selectUserBy(String name, String phone);
}
