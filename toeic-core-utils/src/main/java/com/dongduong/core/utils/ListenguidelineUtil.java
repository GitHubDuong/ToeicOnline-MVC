package com.dongduong.core.utils;

import com.dongduong.core.dto.ListenguidelineDTO;
import com.dongduong.core.persistence.entity.ListenguidelineEntity;

public class ListenguidelineUtil {
    public static ListenguidelineDTO entity2dto(ListenguidelineEntity entity){
        ListenguidelineDTO dto = new ListenguidelineDTO();
        dto.setListenguidelineId(entity.getListenguidelineId());
        dto.setTitle(entity.getTitle());
        dto.setImage(entity.getImage());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setModifiedDate(entity.getModifiedDate());
        return dto;
    }
    public static ListenguidelineEntity dto2entity(ListenguidelineDTO dto){
        ListenguidelineEntity entity = new ListenguidelineEntity();
        entity.setListenguidelineId(dto.getListenguidelineId());
        entity.setTitle(dto.getTitle());
        entity.setImage(dto.getImage());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setModifiedDate(dto.getModifiedDate());

        return entity;
    }
}
