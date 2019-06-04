package com.dongduong.core.service.impl;

import com.dongduong.core.dao.UserDao;
import com.dongduong.core.dao.impl.UserDaoImpl;
import com.dongduong.core.dto.UserDTO;
import com.dongduong.core.persistence.entity.UserEntity;
import com.dongduong.core.service.UserService;
import com.dongduong.core.utils.UserBeanUtil;

public class UserServiceImpl implements UserService {
    static  UserDTO userDTO = new UserDTO();
    public UserDTO userIsExist(UserDTO eUserDTO) {
        UserDao userDao = new UserDaoImpl();
        UserEntity entity = userDao.userIsExistByUsernameAndPassword(eUserDTO.getName(),eUserDTO.getPassword());
        return UserBeanUtil.entity2Dto(entity);
    }

    public UserDTO findRoleId(UserDTO userDTO) {
        UserDao userDao = new UserDaoImpl();
        UserEntity userEntity = userDao.userIsExistByUsernameAndPassword(userDTO.getName(),userDTO.getPassword());
        return UserBeanUtil.entity2Dto(userEntity);
    }
}
