package com.dongduong.command;

import com.dongduong.core.dto.UserDTO;
import com.dongduong.core.web.command.AbstractCommand;

public class UserCommand extends AbstractCommand<UserDTO> {
    private String comfirmPassword;
    public UserCommand(){
        this.pojo = new UserDTO();
    }

    public String getComfirmPassword() {
        return comfirmPassword;
    }

    public void setComfirmPassword(String comfirmPassword) {
        this.comfirmPassword = comfirmPassword;
    }
}
