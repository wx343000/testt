package com.zjx.common;

import com.zjx.entity.ContactUser;
import com.zjx.vo.ContactUserVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

public class ListToVoList {
    /**
     *处理数据项，将ContactUser列表转化为vo列表
     */
    public static List<ContactUserVo> userTo(List<ContactUser> contactUsers){
        List<ContactUserVo> contactUserVos = new ArrayList<ContactUserVo>();
        int i = 1;
        for (ContactUser contactUser : contactUsers) {
            ContactUserVo contactUserVo = new ContactUserVo();
            BeanUtils.copyProperties(contactUser, contactUserVo);
            contactUserVo.setNumber_vo(i);
            contactUserVos.add(contactUserVo);
            i++;
        }
        return contactUserVos;
    }
}
