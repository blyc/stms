package com.hp.common.dao;

import com.hp.common.model.UTask;

public interface UTaskMapper {

    //添加作业
    int insertUtask(UTask uTask);
    //修该状态
    int updateMarkByPrimaryKey(Long tid);

}