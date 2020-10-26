package com.zjx.dao;

import com.zjx.entity.ContactGroup;
import com.zjx.entity.ContactUser;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class ContactGroupDao {
    @Resource(name = "emf")
    private EntityManagerFactory managerFactory;

    public List<ContactGroup> selectAllGroup() {
        EntityManager entityManager = managerFactory.createEntityManager();
        Query from_contactGroup = entityManager.createQuery("from ContactGroup");
        List<ContactGroup> resultList = from_contactGroup.getResultList();
        return resultList;
    }

    public String insertGroup(ContactGroup contactGroup) {
        EntityManager entityManager = managerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(contactGroup);
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
}
