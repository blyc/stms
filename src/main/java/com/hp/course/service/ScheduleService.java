package com.hp.course.service;

import com.hp.common.model.USchedule;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by DELL on 2018/3/26.
 */
public interface ScheduleService {
    public boolean insert(USchedule record);
    public List<USchedule> selectUScheduleAllByccidService(Long ccid);
    public void findAllInfo(HttpServletResponse response,Long ccid);
}
