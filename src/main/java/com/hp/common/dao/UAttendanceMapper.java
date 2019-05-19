package com.hp.common.dao;

import com.hp.attendance.bo.Attendancebo;
import com.hp.attendance.bo.Remarksbo;
import com.hp.common.model.UAttendance;

import java.util.List;
import java.util.Map;

public interface UAttendanceMapper {

    int  deleteBatchBySid(Map<String,Object> map);

    int insertBatch(List<UAttendance> uAttendanceList);

    int insertRemarks(List<Remarksbo> remarksboList);

    List<UAttendance> findDetailByRecordTime(Map<String, Object> map);

    List<Attendancebo> findDetailByymd(Map<String, Object> map);

    List<Remarksbo> findRemarksDetail(Long caid);
}