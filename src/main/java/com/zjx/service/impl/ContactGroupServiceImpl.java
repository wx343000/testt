package com.zjx.service.impl;

import com.zjx.dao.ContactGroupDao;
import com.zjx.entity.ContactGroup;
import com.zjx.service.ContactGroupService;

import javax.annotation.Resource;
import java.util.List;

public class ContactGroupServiceImpl implements ContactGroupService {
    @Resource(name = "contactGroupDao")
    private ContactGroupDao contactGroupDao;

    public List<ContactGroup> selectAllGroup() {
        List<ContactGroup> contactGroups = contactGroupDao.selectAllGroup();
        return contactGroups;
    }

    public String insertGroup(ContactGroup contactGroup) {
        String s = contactGroupDao.insertGroup(contactGroup);
        return s;
    }
}
