package com.dongduong.command;

import com.dongduong.core.dto.ListenguidelineDTO;
import com.dongduong.core.web.command.AbstractCommand;

public class ListenguidelineCommand extends AbstractCommand<ListenguidelineDTO> {
    public ListenguidelineCommand() {
        this.pojo = new ListenguidelineDTO();
    }
}
