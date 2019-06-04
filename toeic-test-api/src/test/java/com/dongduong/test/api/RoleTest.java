package com.dongduong.test.api;

import com.dongduong.core.dao.RoleDao;
import com.dongduong.core.dao.impl.RoleDaoImpl;
import com.dongduong.core.persistence.entity.RoleEntity;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RoleTest {
    @Test
    public void testFindAll(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        List<RoleEntity> list = new ArrayList<RoleEntity>();
        list = roleDao.findAll();
    }

    @Test
    public void testUpdate(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("USER_1");
        roleEntity.setRoleId(2);
        roleDao.update(roleEntity);
    }

    @Test
    public void testSave(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(3);
        roleEntity.setName("USER_2");
        roleDao.save(roleEntity);
    }

    @Test
    public void checkFindById(){
        RoleDao roleDao = new RoleDaoImpl();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity = roleDao.findById(2);
    }

  /*  @Test
   *//* public void findByPrprerty(){
        RoleDao roleDao = new RoleDaoImpl();
        Object[] objects = new Object[2];
        String property = null;
        Object value = null;
        String sortPress = null;
        String sortDirection = null;
        objects = *//*roleDao.findByProperty(property,value,sortPress,sortDirection,null,null);
    }*/

    @Test
    public void checkDelete(){
        RoleDao roleDao = new RoleDaoImpl();
        List<Integer> id = new ArrayList<Integer>();
        id.add(1);
        id.add(2);
        id.add(3);
        int a = roleDao.delete(id);
    }
}
