package com.hp.common.dao;

import com.hp.common.model.UInterView;

public interface UInterViewMapper {

    int insertSelective(UInterView record);

    int insert(UInterView record);

    UInterView selectByPrimaryKey(Long key);


}