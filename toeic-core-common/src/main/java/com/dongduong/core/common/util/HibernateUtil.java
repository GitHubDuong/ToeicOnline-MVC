package com.dongduong.core.common.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory(){
        try{
            return new Configuration().configure().buildSessionFactory();
        }catch (Throwable ex){
            System.out.println("ERRO CREATE SESSION FACTORY");
            throw  new ExceptionInInitializerError();
        }
    }

    public  static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}
