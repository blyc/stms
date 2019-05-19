package com.hp.common.dao;

import com.hp.common.model.USchedule;

import java.util.List;

public interface UScheduleMapper {
    int insert(USchedule record);

    List<USchedule> selectUScheduleWhetherInsert(USchedule uSchedule);

    List<USchedule> selectUScheduleAllByccid(Long ccid);
}