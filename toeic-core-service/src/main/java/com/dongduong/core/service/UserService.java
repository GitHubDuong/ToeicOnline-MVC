package com.dongduong.core.service;

import com.dongduong.core.dto.UserDTO;

public interface UserService {
    UserDTO userIsExist(UserDTO eUserDTO);
    UserDTO findRoleId(UserDTO userDTO);

}
