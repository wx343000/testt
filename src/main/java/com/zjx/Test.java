package com.zjx;

import com.zjx.entity.ContactGroup;
import com.zjx.entity.LoginUser;
import com.zjx.service.LoginUserService;
import com.zjx.service.impl.ContactGroupServiceImpl;
import com.zjx.service.impl.ContactUserServiceImpl;
import com.zjx.vo.ContactUserVo;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.UUID;

public class Test {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        /**
         *测试添加通讯录记录
         */
//        ContactUserServiceImpl contactUserServiceImpl = (ContactUserServiceImpl) classPathXmlApplicationContext.getBean("contactUserServiceImpl");
//        ContactUser contactUser = new ContactUser();
//        for (int i = 1; i <= 10; i++) {
//            contactUser.setNumber(i);
//            contactUser.setName("姓名" + i);
//            contactUser.setPhone("1887077386" + i);
//            contactUser.setSex("男");
//            contactUser.setAddress("地址" + i);
//            contactUser.setEmail(i + "49910111@qq.com");
//            contactUser.setUser_group("家人");
//            contactUser.setPs("备注" + i);
//            String s = contactUserServiceImpl.insertUser(contactUser);
//            System.out.println(s);
//        }
        /**
         *测试添加群组
         */
//        ContactGroupServiceImpl contactGroupServiceImpl = (ContactGroupServiceImpl)context.getBean("contactGroupServiceImpl");
//        ContactGroup contactGroup = new ContactGroup();
//        contactGroup.setGroup_id(UUID.randomUUID().toString());
//        contactGroup.setC_group("家人");
//        contactGroupServiceImpl.insertGroup(contactGroup);
        /**
         *测试添加用户
         */
//        LoginUserService loginUserServiceImpl = (LoginUserService)context.getBean("loginUserServiceImpl");
//        LoginUser loginUser = new LoginUser();
//        loginUser.setUsername("zjx");
//        loginUser.setPassword("aaa");
//        loginUserServiceImpl.insertLoginUser(loginUser);
        /**
         *测试登录用户
         */
//        LoginUserService loginUserServiceImpl = (LoginUserService)context.getBean("loginUserServiceImpl");
//        LoginUser zjx = loginUserServiceImpl.selectLoginUser("zjx");
//        System.out.println(zjx);
        /**
         *测试条件
         */
        ContactUserServiceImpl contactUserServiceImpl = (ContactUserServiceImpl) context.getBean("contactUserServiceImpl");
        List<ContactUserVo> contactUserVos = contactUserServiceImpl.selectUserBy("1", "1");
        System.out.println(contactUserVos);

//        List<ContactUser> contactUsers = contactUserServiceImpl.selectAllUser();
//        System.out.println(contactUsers);
    }

}
