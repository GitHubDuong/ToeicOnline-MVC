package com.dongduong.core.utils;

import com.dongduong.core.dto.UserDTO;
import com.dongduong.core.persistence.entity.UserEntity;

public class UserBeanUtil {
    public  static  UserDTO entity2Dto( UserEntity entity){
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setName(entity.getName());
        dto.setFullName(entity.getFullName());
        dto.setPassword(entity.getPassword());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setRoleDTO(RoleBeanUtil.entity2Dto(entity.getRoleEntity()));
        return dto;
    }
    public  static UserEntity dto2Entity(UserDTO userDTO){
        UserEntity entity = new UserEntity();
        entity.setUserId(userDTO.getUserId());
        entity.setName(userDTO.getName());
        entity.setPassword(userDTO.getPassword());
        entity.setFullName(userDTO.getFullName());
        entity.setCreatedDate(userDTO.getCreatedDate());
        entity.setRoleEntity(RoleBeanUtil.dto2Entity(userDTO.getRoleDTO()));

        return entity;
    }
}
