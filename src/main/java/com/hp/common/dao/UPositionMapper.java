package com.hp.common.dao;

import com.hp.common.model.UPosition;

import java.util.List;
import java.util.Map;


public interface UPositionMapper {
    public List<UPosition> findAllPosition(Map map);
    public int insert(UPosition record);
    public int updateByPrimaryKeySelective(UPosition record);
    public int deleteByPrimaryKey(Long id);
}
