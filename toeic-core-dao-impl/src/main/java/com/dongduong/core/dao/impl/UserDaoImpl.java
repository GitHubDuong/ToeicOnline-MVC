package com.dongduong.core.dao.impl;

import com.dongduong.core.common.util.HibernateUtil;
import com.dongduong.core.dao.UserDao;
import com.dongduong.core.data.daoimpl.AbstractDao;
import com.dongduong.core.persistence.entity.UserEntity;
import org.hibernate.*;

public class UserDaoImpl extends AbstractDao<Integer, UserEntity> implements UserDao {

    public UserEntity userIsExistByUsernameAndPassword(String name, String password) {
        UserEntity userEntity = new UserEntity();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            StringBuilder sql = new StringBuilder("FROM ").append(getPersistenceClassName()).append(" WHERE name= :name AND password= :password");
            Query query = session.createQuery(sql.toString());
            query.setParameter("name",name);
            query.setParameter("password",password);
            userEntity = (UserEntity) query.uniqueResult();
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return userEntity;
    }
}
