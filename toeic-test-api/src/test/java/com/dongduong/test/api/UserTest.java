package com.dongduong.test.api;

import com.dongduong.core.dao.UserDao;
import com.dongduong.core.dao.impl.UserDaoImpl;
import com.dongduong.core.persistence.entity.UserEntity;
import org.testng.annotations.Test;

public class UserTest {

    @Test
    public  void checkUserIsExist(){
        UserEntity entity = new UserEntity();
        UserDao userDao = new UserDaoImpl();
        String name = "Noname";
        String password = "123123";
        entity = userDao.userIsExistByUsernameAndPassword(name,password);

    }

    @Test
    public void checkFindRoleByUser(){
        UserEntity userEntity = new UserEntity();
        UserDao userDao = new UserDaoImpl();
        userEntity = userDao.userIsExistByUsernameAndPassword("Noname","123123");
        System.out.println(userEntity.getRoleEntity().getRoleId()+"----"+userEntity.getRoleEntity().getName());
    }
}
