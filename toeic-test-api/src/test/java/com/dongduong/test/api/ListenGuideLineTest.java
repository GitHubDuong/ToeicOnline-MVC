package com.dongduong.test.api;

import com.dongduong.core.dao.ListenGuideLineDao;
import com.dongduong.core.dao.impl.ListenGuideLineDaoImpl;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class ListenGuideLineTest {
    ListenGuideLineDao listenGuideLineDao;
    @BeforeTest
    public void initData(){
        listenGuideLineDao = new ListenGuideLineDaoImpl();
    }

    @Test
    public void TestFindByProperty(){
        Map<String, Object> property = new HashMap<String, Object>();
        property.put("listenguidelineid",1);
        Object[] objects = listenGuideLineDao.findByProperty(property,null,null,null,null);
    }
}
