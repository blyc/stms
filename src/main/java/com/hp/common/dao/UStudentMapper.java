package com.hp.common.dao;

import com.hp.common.model.UClass;
import com.hp.common.model.UStudent;

import java.util.List;
import java.util.Map;

public interface UStudentMapper {

    public int insertSelective(UStudent record);

    public UStudent selectByPrimaryKey(Long sid);

    public int updateByPrimaryKeySelective(UStudent record);

    public int updateByPrimaryKey(UStudent record);

    public List<UStudent> selectByCid(Long cid);

    public List<UStudent> selectWithoutClass(Map<String, Object> map);

    public int updateCidBySid(Map<String, Object> map);

    public List<UStudent> findAll();

    public List<UStudent> selectByParam(Map map);

    public int deleteStudentByCid(UClass uClass);

}