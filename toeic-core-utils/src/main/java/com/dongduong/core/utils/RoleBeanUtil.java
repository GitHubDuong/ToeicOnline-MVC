package com.dongduong.core.utils;

import com.dongduong.core.dto.RoleDTO;
import com.dongduong.core.persistence.entity.RoleEntity;

public class RoleBeanUtil {
    public  static  RoleDTO entity2Dto(RoleEntity entity){
        RoleDTO dto = new RoleDTO();
        dto.setRoleId(entity.getRoleId());
        dto.setName(entity.getName());
        return dto;
    }
    public  static RoleEntity  dto2Entity( RoleDTO dto){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleId(dto.getRoleId());
        roleEntity.setName(dto.getName());
        return  roleEntity;
    }
}
