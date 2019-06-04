package com.dongduong.core.service.impl;

import com.dongduong.core.dao.ListenGuideLineDao;
import com.dongduong.core.dao.impl.ListenGuideLineDaoImpl;
import com.dongduong.core.dto.ListenguidelineDTO;
import com.dongduong.core.persistence.entity.ListenguidelineEntity;
import com.dongduong.core.service.ListenguidelineService;
import com.dongduong.core.utils.ListenguidelineUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ListenguidelineServiceImpl implements ListenguidelineService {
    private ListenGuideLineDao listenGuideLineDao = new ListenGuideLineDaoImpl();
    public Object[] findListenguidelineByPropertites(Map<String,Object> property, String sortExpression, String sortDirection, Integer offset, Integer limit) {
        List<ListenguidelineDTO> result = new ArrayList<ListenguidelineDTO>();
        Object[] objects = listenGuideLineDao.findByProperty(property,sortExpression,sortDirection,offset,limit);
        for(ListenguidelineEntity item : (List<ListenguidelineEntity>)objects[1]){
            ListenguidelineDTO listenguidelineDTO = ListenguidelineUtil.entity2dto(item);
            result.add(listenguidelineDTO);
        }
        objects[1]=result;

        return objects ;
    }
}
