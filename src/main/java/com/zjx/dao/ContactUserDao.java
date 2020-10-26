package com.zjx.dao;

import com.zjx.entity.ContactUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ContactUserDao {
    @Resource(name = "emf")
    private EntityManagerFactory managerFactory;

    public List<ContactUser> selectAllUser() {
        EntityManager entityManager = managerFactory.createEntityManager();
        Query from_contactUser = entityManager.createQuery("from ContactUser");
        List<ContactUser> resultList = from_contactUser.getResultList();
        return resultList;
    }

    public List<ContactUser> selectUserBy(String name,String phone){
        EntityManager entityManager = managerFactory.createEntityManager();
        if (name.equals("") && phone.equals("")){
            List<ContactUser> resultList = selectAllUser();
            return resultList;
        }else if (name.equals("")){
            Query from_contactUser = entityManager.createQuery("from ContactUser where phone like '%"+phone+"%'");
            List<ContactUser> resultList = from_contactUser.getResultList();
            return resultList;
        }else if (phone.equals("")){
            Query from_contactUser = entityManager.createQuery("from ContactUser where name like '%"+name+"%'");
            List<ContactUser> resultList = from_contactUser.getResultList();
            return resultList;
        }else {
            Query from_contactUser = entityManager.createQuery("from ContactUser where name like '%"+name+"%' and phone like '%"+phone+"%'");
            List<ContactUser> resultList = from_contactUser.getResultList();
            return resultList;
        }
    }

    public String deleteUser(ContactUser contactUser) {
        EntityManager entityManager = managerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.remove(entityManager.merge(contactUser));
            entityManager.flush();
            entityManager.getTransaction().commit();
            return "删除成功";
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return "删除失败";
        } finally {
            entityManager.close();
        }
    }

    public String insertUser(ContactUser contactUser) {
        EntityManager entityManager = managerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(contactUser);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return "插入成功";
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return "插入失败";
        } finally {
            entityManager.close();
        }
    }

    public String updateUser(ContactUser contactUser) {
        EntityManager entityManager = managerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(contactUser);
            entityManager.flush();
            entityManager.getTransaction().commit();
            return "更新成功";
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
            return "更新失败";
        } finally {
            entityManager.close();
        }
    }
}
