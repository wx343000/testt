package com.zjx.service.impl;

import com.zjx.common.ListToVoList;
import com.zjx.dao.ContactUserDao;
import com.zjx.entity.ContactUser;
import com.zjx.service.ContactUserService;
import com.zjx.vo.ContactUserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


public class ContactUserServiceImpl implements ContactUserService {

    @Resource(name = "contactUserDao")
    private ContactUserDao contactUserDao;

    //处理列表，返回vo列表
    public List<ContactUserVo> selectAllUser() {
        List<ContactUser> contactUsers = contactUserDao.selectAllUser();
        List<ContactUserVo> contactUserVos = ListToVoList.userTo(contactUsers);
        return contactUserVos;
    }

    public String insertUser(ContactUser contactUser) {
        String s = contactUserDao.insertUser(contactUser);
        return s;
    }

    public String deleteUser(ContactUser contactUser) {
        String s = contactUserDao.deleteUser(contactUser);
        return s;
    }

    public String updateUser(ContactUser contactUser) {
        String s = contactUserDao.updateUser(contactUser);
        return s;
    }

        public List<ContactUserVo> selectUserBy(String name, String phone) {
        List<ContactUser> contactUsers = contactUserDao.selectUserBy(name, phone);
        List<ContactUserVo> contactUserVos = ListToVoList.userTo(contactUsers);
        return contactUserVos;
    }

}
