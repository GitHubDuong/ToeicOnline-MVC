package com.dongduong.core.data.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDao<ID extends Serializable, T> {
    List<T> findAll();

    //update
    T update(T entity);

    //Save
    void save(T entity);

    //FindById
    T findById(ID id);

    //findByProperty
    Object[] findByProperty(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit);

    //Delete
    Integer delete(List<ID> ids);

}
