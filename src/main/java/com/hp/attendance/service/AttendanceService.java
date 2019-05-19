package com.hp.attendance.service;


import com.hp.attendance.bo.Attendancebo;
import com.hp.attendance.bo.Remarksbo;
import com.hp.common.model.UClassAttendance;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/03/03
 * @Description:
 */
public interface AttendanceService {
    public Pagination<UClassAttendance> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public UClassAttendance insertAttendance(List<Attendancebo> attendanceboList, Long id);

    public UClassAttendance insertRemarks(List<Remarksbo> remarksboList, Long id);

    public UClassAttendance insertUClassAttendance(UClassAttendance uClassAttendance);

    public List<Attendancebo> findDetail(long id);

    public List<Remarksbo> findRemarksDetail(long id);

}
