package com.zjx.service;

import com.zjx.entity.ContactGroup;
import com.zjx.entity.ContactUser;
import com.zjx.vo.ContactUserVo;

import java.util.List;

public interface ContactGroupService {
    List<ContactGroup> selectAllGroup();

    String insertGroup(ContactGroup contactGroup);
}
