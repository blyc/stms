package com.hp.common.dao;

import com.hp.common.model.UTeacherClass;

import java.util.List;
import java.util.Map;

public interface UTeacherClassMapper {
    int insert(UTeacherClass record);
    int updateByCidAndPosition(UTeacherClass record);
    List<UTeacherClass> findByCidAndPosition(Map map);


}