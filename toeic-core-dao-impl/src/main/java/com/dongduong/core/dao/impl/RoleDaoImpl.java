package com.dongduong.core.dao.impl;

import com.dongduong.core.dao.ListenGuideLineDao;
import com.dongduong.core.dao.RoleDao;
import com.dongduong.core.data.daoimpl.AbstractDao;
import com.dongduong.core.persistence.entity.RoleEntity;

public class RoleDaoImpl extends AbstractDao<Integer, RoleEntity> implements RoleDao {
    ListenGuideLineDao listenGuideLineDao = new ListenGuideLineDaoImpl();
    Object[] objects = listenGuideLineDao.findByProperty(null,null,null,null,null);


}
