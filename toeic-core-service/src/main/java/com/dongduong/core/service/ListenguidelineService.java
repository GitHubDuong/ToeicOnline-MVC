package com.dongduong.core.service;

import java.util.Map;

public interface ListenguidelineService {
    public Object[] findListenguidelineByPropertites(Map<String,Object>property, String sortExpression, String sortDirection, Integer offset, Integer limit);
}
