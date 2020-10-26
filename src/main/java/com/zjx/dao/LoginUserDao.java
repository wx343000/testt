package com.zjx.dao;

import com.zjx.entity.LoginUser;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

public class LoginUserDao {
    @Resource(name = "emf")
    private EntityManagerFactory managerFactory;

    public LoginUser selectLoginUser(String username) {
        EntityManager entityManager = managerFactory.createEntityManager();
        Query from_contactUser = entityManager.createQuery("from LoginUser where username = ? ");
        from_contactUser.setParameter(1,username);
        LoginUser singleResult = (LoginUser) from_contactUser.getSingleResult();
        return singleResult;
    }

    public void insertLoginUser(LoginUser loginUser) throws Exception {
        EntityManager entityManager = managerFactory.createEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(loginUser);
            entityManager.flush();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
