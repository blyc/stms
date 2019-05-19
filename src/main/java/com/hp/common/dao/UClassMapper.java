package com.hp.common.dao;

import com.hp.common.model.UClass;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UClassMapper {

    public UClass selectByPrimaryKey(Long ccid);

//    public int findExistingCount(UClass record);

    public int findMaxClassNum(@Param("mid")long mid,@Param("cid")long cid,@Param("grade")String grade);

    public List<Map<String, Object>> findMajorClassCountByParam(Map map);

    public List<UClass> findByCidAndMidAndGrade(UClass uClass);

    //班级人数增加
    public int updateNumByPrimayKey(UClass record);

    //批量增加数据
    public int insertManyUClass(List<UClass> uClassList);

    /*查询班级名称*/
    public List<UClass> selectALLUClassname();

    //查询班级人数
    public List<UClass> selectALLUClassnum(UClass uClass);

    //查询班级中的班级名字
    public List<UClass> findUClassByCid(Map map);

    public int updateByPrimaryKeySelective(UClass record);

}