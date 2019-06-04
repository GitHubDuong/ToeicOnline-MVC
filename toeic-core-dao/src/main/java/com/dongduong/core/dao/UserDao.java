package com.dongduong.core.dao;

import com.dongduong.core.data.dao.GenericDao;
import com.dongduong.core.persistence.entity.UserEntity;

public interface UserDao extends GenericDao<Integer, UserEntity> {
       UserEntity userIsExistByUsernameAndPassword(String name, String password);


}
