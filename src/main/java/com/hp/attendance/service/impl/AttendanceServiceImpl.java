package com.hp.attendance.service.impl;

import com.hp.attendance.bo.Attendancebo;
import com.hp.attendance.bo.Remarksbo;
import com.hp.attendance.service.AttendanceService;
import com.hp.common.dao.UAttendanceMapper;
import com.hp.common.dao.UClassAttendanceMapper;
import com.hp.common.model.UAttendance;
import com.hp.common.model.UClassAttendance;
import com.hp.common.model.UUser;
import com.hp.common.utils.DateUtil;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author: boy
 * @Date: 2018/03/03
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class AttendanceServiceImpl extends BaseMybatisDao<UClassAttendanceMapper> implements AttendanceService {
    @Autowired
    private UAttendanceMapper uAttendanceMapper;
    @Autowired
    private UClassAttendanceMapper uClassAttendanceMapper;

    @Override
    public Pagination<UClassAttendance> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Transactional(readOnly = false)
    public void setRecord(UAttendance uAttendance, Long record, String day) {

        Class<?> clazz = UAttendance.class;
        String methodName = "setRecord" + Integer.parseInt(day);
        //System.out.println(methodName);
        try {
            Method method = clazz.getMethod(methodName, Long.class);
            method.invoke(uAttendance, record);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional(readOnly = false)
    public UClassAttendance insertAttendance(List<Attendancebo> attendanceboList, Long id) {
        UUser uUser = TokenManager.getToken();
        UClassAttendance uClassAttendance = uClassAttendanceMapper.selectByPrimaryKey(id);
        //获取出勤日期
        Date d = DateUtil.stringToDate(uClassAttendance.getRecordTime(), DateUtil.ISO_EXPANDED_DATE_FORMAT);
        //dd
        String day = DateUtil.getDay(d);
        //yyyy-MM
        String recordtime = DateUtil.dateToString(d, DateUtil.ISO_EXPANDED_DATE_FORMAT_1);

        //获取班级id
        Long ccid = uClassAttendance.getCcid();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("record_time", recordtime);
        map.put("ccid", ccid);

        List<UAttendance> uAttendanceList1 = uAttendanceMapper.findDetailByRecordTime(map);

        //记录旧数据
        Map<Long, UAttendance> uAttendanceMap = new HashMap<Long, UAttendance>();

        if (uAttendanceList1 != null && uAttendanceList1.size() > 0) {
            for (UAttendance u2 : uAttendanceList1) {
                uAttendanceMap.put(u2.getSid(), u2);
            }
        }

        List<Long> sidList = new ArrayList<Long>();

        //出勤
        Long count1 = 0L;
        //迟到
        Long count2 = 0L;
        //旷课
        Long count3 = 0L;
        //请假
        Long count4 = 0L;
        //早退
        Long count5 = 0L;

        List<UAttendance> uAttendanceList = new ArrayList<UAttendance>();

        for (Attendancebo a : attendanceboList) {
            UAttendance u = null;

            //已有学生
            if (uAttendanceMap != null || uAttendanceMap.size() > 0) {
                u = uAttendanceMap.get(a.getSid());
                sidList.add(a.getSid());
            }

            //新学生
            if (u == null) {
                u = new UAttendance();
                u.setCreateTime(DateUtil.getCurrentDateTime());
                u.setCreateId(uUser.getUid());
                u.setCcid(ccid);
                u.setRecordTime(recordtime);
            }

            u.setSid(a.getSid());
            //设置出勤率
            setRecord(u, a.getRecord(), day);
            u.setUpdateTime(DateUtil.getCurrentDateTime());
            u.setUpdateId(uUser.getUid());
            uAttendanceList.add(u);

            if (a.getRecord().equals(1L)) {
                count1++;
            } else if (a.getRecord().equals(2L)) {
                count2++;
            } else if (a.getRecord().equals(3L)) {
                count3++;
            } else if (a.getRecord().equals(4L)) {
                count4++;
            } else if (a.getRecord().equals(5L)) {
                count5++;
            }
        }


        int len = attendanceboList.size();

        BigDecimal rate = BigDecimal.valueOf((count1 + count5) * 1.0 / len);
        uClassAttendance.setScounts((long) len);
        uClassAttendance.setNormalCount(count1);
        uClassAttendance.setTruantCount(count3);
        uClassAttendance.setLateCount(count2);
        uClassAttendance.setLeaveCount(count4);
        uClassAttendance.setEarlyCount(count5);
        uClassAttendance.setRate(rate);
        uClassAttendance.setUpdateTime(DateUtil.getCurrentDateTime());
        uClassAttendance.setUpdateId(uUser.getUid());
        uClassAttendanceMapper.updateByPrimaryKeySelective(uClassAttendance);


        if (sidList != null && sidList.size() > 0) {
            map.put("list", sidList);
            uAttendanceMapper.deleteBatchBySid(map);
        }

        uAttendanceMapper.insertBatch(uAttendanceList);
        return uClassAttendance;
    }

    @Override
    @Transactional(readOnly = false)
    public UClassAttendance insertRemarks(List<Remarksbo> remarksboList, Long id) {
        UUser uUser = TokenManager.getToken();
        UClassAttendance uClassAttendance = uClassAttendanceMapper.selectByPrimaryKey(id);

        if(uClassAttendance==null){
            return uClassAttendance;

        }
        uAttendanceMapper.insertRemarks(remarksboList);
        //出勤
        Long count1 = 0L;
        //迟到
        Long count2 = 0L;
        //旷课
        Long count3 = 0L;
        //请假
        Long count4 = 0L;
        //早退
        Long count5 = 0L;

        for (Remarksbo a : remarksboList) {
            if (a.getRecord().equals(1L)) {
                count1++;
            } else if (a.getRecord().equals(2L)&&a.getRemarks().trim().length()>0) {
                count2++;
            } else if (a.getRecord().equals(3L)&&a.getRemarks().trim().length()>0) {
                count3++;
            } else if (a.getRecord().equals(4L)&&a.getRemarks().trim().length()>0) {
                count4++;
            } else if (a.getRecord().equals(5L)&&a.getRemarks().trim().length()>0) {
                count5++;
            }
        }

        Long scounts = uClassAttendance.getScounts();
        Long normalCount = uClassAttendance.getNormalCount();
        Long earlyCount = uClassAttendance.getEarlyCount();
        //请假若为有效备注计入出勤
        //+count4

        BigDecimal rate = BigDecimal.valueOf((normalCount + earlyCount) * 1.0 / (scounts-count4));
        uClassAttendance.setRate(rate);
        uClassAttendance.setUpdateTime(DateUtil.getCurrentDateTime());
        uClassAttendance.setUpdateId(uUser.getUid());
        uClassAttendanceMapper.updateByPrimaryKeySelective(uClassAttendance);
        return uClassAttendance;
    }

    @Override
    @Transactional(readOnly = false)
    public UClassAttendance insertUClassAttendance(UClassAttendance uClassAttendance) {
        UUser uUser = TokenManager.getToken();
        uClassAttendance.setRecordTime(DateUtil.getCurrentDateString());
        uClassAttendance.setCreateTime(DateUtil.getCurrentDateTime());
        uClassAttendance.setCreateId(uUser.getUid());
        uClassAttendanceMapper.insertSelective(uClassAttendance);
        return uClassAttendance;
    }

    @Override
    public List<Attendancebo> findDetail(long id) {
        UClassAttendance uClassAttendance = uClassAttendanceMapper.selectByPrimaryKey(id);

        //获取出勤日期
        Date d = DateUtil.stringToDate(uClassAttendance.getRecordTime(), DateUtil.ISO_EXPANDED_DATE_FORMAT);
        //dd
        String day = DateUtil.getDay(d);
        //yyyy-MM
        String recordtime = DateUtil.dateToString(d, DateUtil.ISO_EXPANDED_DATE_FORMAT_1);

        //获取班级id
        Long ccid = uClassAttendance.getCcid();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("record_time", recordtime);
        map.put("ccid", ccid);
        map.put("recordDay", day);

        return uAttendanceMapper.findDetailByymd(map);
    }

    @Override
    public List<Remarksbo> findRemarksDetail(long id) {
        return uAttendanceMapper.findRemarksDetail(id);
    }
}
