package com.dongduong.core.data.daoimpl;

import com.dongduong.core.common.constands.CoreConstant;
import com.dongduong.core.common.util.HibernateUtil;
import com.dongduong.core.data.dao.GenericDao;
import org.hibernate.*;


import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class AbstractDao<ID extends Serializable, T> implements GenericDao<ID,T> {

    private final Logger log = (Logger) Logger.getLogger(String.valueOf(this.getClass()));
    private Class<T> persistenceClass;

    public AbstractDao() {
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    public String getPersistenceClassName() {
        return persistenceClass.getSimpleName();
    }

    //API FindAll To Table
    public List<T> findAll() {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            StringBuilder sql = new StringBuilder("from ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
        return list;
    }

    public T update(T entity) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
          Object object = session.merge(entity);
          result = (T) object;
          transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }
        return result;
    }

    public void save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.persist(entity);
            transaction.commit();
        }catch (HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally {
            session.close();
        }

    }

    public T findById(ID id) {
        T result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
           result = (T) session.get(persistenceClass,id);
            if(result ==null){
                throw  new ObjectNotFoundException("NOT FOUND" + id , null);
            }
        }catch (HibernateException e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return result;
    }

/*    public Object[] findByProperty(String property, Object value, String sortExpression, String sortDirection,Integer offset, Integer limit) {*/
public Object[] findByProperty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<T> list = new ArrayList<T>();
        Object total_item = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String[] paremeters = new String[property.size()];
        Object[] values = new Object[property.size()];
        int i = 0;
        //GET DATA VAO TRONG MANG
         for (Map.Entry item : property.entrySet()) {
              paremeters[i] = (String) item.getKey();
              values[i] = item.getValue();
                i++;
                          }
            try{
                StringBuilder sql = new StringBuilder("from ");
             sql.append(getPersistenceClassName());
            if(property.size()>0) {

                for(int t1 = 0 ; t1 < property.size();t1++){
                    if(t1==0){
                        sql.append(" where ").append(paremeters[t1]);
                        sql.append("= :"+paremeters[t1]+"");
                    }else{
                        sql.append(" AND ").append(paremeters[t1]);
                        sql.append("= :"+paremeters[t1]+"");
                    }
                }
            }
                //



                if (sortExpression != null && sortDirection != null){
                    sql.append(" order by ").append(sortExpression);
                    sql.append(" "+(sortDirection.equals(CoreConstant.SORT_ASC)?"asc":"desc"));
                }
                Query query_sql = session.createQuery(sql.toString());
                if(paremeters.length > 0){
                    for(int t2 =  0 ; t2<paremeters.length ; t2++){
                        query_sql.setParameter(paremeters[t2],values[t2]);
                    }
                }

                if(offset != null && offset >= 0){
                    query_sql.setFirstResult(offset);
                }
                if(limit != null && limit >0){
                    query_sql.setMaxResults(limit);
                }
                list = query_sql.list();
                //COUNT
                StringBuilder sql1 = new StringBuilder("select count(*) from ");
                sql1.append(getPersistenceClassName());
                if(property != null) {
                    for (int t1 = 0; t1 < property.size(); t1++) {
                        if (t1 == 0) {
                            sql1.append(" where ").append(paremeters[t1]);
                            sql1.append("= :" + paremeters[t1] + " ");
                        } else {
                            sql1.append("AND ").append(paremeters[t1]);
                            sql1.append("= :" + paremeters[t1]+ " ");
                        }
                    }
                }

                Query query_sql1 = session.createQuery(sql1.toString());

                    if(paremeters.length>0){
                        for(int t2 =  0 ; t2<paremeters.length ; t2++){
                            query_sql1.setParameter(paremeters[t2],values[t2]);
                        }
                    }

                total_item = query_sql1.list().get(0);
                transaction.commit();
            }catch (HibernateException e){
                e.printStackTrace();
                transaction.rollback();
            }finally {
                session.close();
            }
        return new Object[]{total_item,list};
    }

    public Integer delete(List<ID> ids) {
        Integer count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
           for(ID id : ids){
               T t = (T) session.get(persistenceClass,id);
               session.delete(t);
               count++;
           }
                transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            transaction.rollback();
        }finally {
            session.close();
        }
        return count;
    }
}
