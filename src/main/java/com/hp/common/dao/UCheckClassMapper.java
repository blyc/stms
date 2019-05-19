package com.hp.common.dao;

import com.hp.common.model.UCheckClass;

public interface UCheckClassMapper {

    //通过Chid更新数据
    int updateUCheckClassByChid(UCheckClass record);

    //添加数据
    public int insertCheckClass(UCheckClass record);
}