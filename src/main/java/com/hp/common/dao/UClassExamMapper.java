package com.hp.common.dao;

import com.hp.common.model.UClassExam;

import java.util.Map;

public interface UClassExamMapper {

    public int insert(UClassExam record);

    public int updateMarkByPrimaryKey(Map<String, Object> map);

    public int updateExameventByPrimaryKey(UClassExam record);

    public UClassExam selectByPrimaryKey(Long ceid);

    int deleteByPrimaryKey(Long ceid);
}
