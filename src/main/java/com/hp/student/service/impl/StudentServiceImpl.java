package com.hp.student.service.impl;

import com.hp.common.dao.*;
import com.hp.common.model.*;
import com.hp.common.utils.DateUtil;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.core.statics.Constant;
import com.hp.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: boy
 * @Date: 2018/02/25
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class StudentServiceImpl extends BaseMybatisDao<UStudentMapper> implements StudentService {

    @Autowired
    private UStudentMapper uStudentMapper;
    @Autowired
    private UClassMapper uClassMapper;
    @Autowired
    private UMajorMapper uMajorMapper;
    @Autowired
    private UStudenteFinalMapper uStudenteFinalMapper;
    @Autowired
    private VClassMapper vClassMapper;

    @Override
    public Pagination<UStudent> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public UStudent findByIdCard(String idCard) {
        Map map = new HashMap<String, Object>();
        map.put("idcard", idCard);
        List<UStudent> uStudents = uStudentMapper.selectByParam(map);
        if (uStudents == null || uStudents.size() == 0) {
            return null;
        }
        return uStudents.get(0);
    }

    @Override
    @Transactional(readOnly = false)
    public UStudent insertSelective(UStudent record) {
        if (null == record) {
            return null;
        }
        UStudent uStudent = findByIdCard(record.getIdcard());
        if (null != uStudent) {
            return null;
        }

        UUser uUser = TokenManager.getToken();

        record.setuCCid(uUser.getCid());
        record.setCreateId(uUser.getUid());
        record.setCreateName(uUser.getEmail());
        record.setCreateTime(new Date());
        record.setState(Constant.STUDENT_READING);
        record.setStage(Constant.LEARNING_STAGE_S1);
        uStudentMapper.insertSelective(record);

        Long cid = record.getCid();

        if (null != cid) {
            UClass uClass = new UClass();
            uClass.setCcid(cid);
            uClass.setNum((long) 1);
            uClassMapper.updateNumByPrimayKey(uClass);
        }
        return record;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(UStudent record) {
        UStudent student = uStudentMapper.selectByPrimaryKey(record.getSid());
        if (student == null) {
            return -1;
        }

        VClass vClass = vClassMapper.SelectVClassByccid(student.getCid());
        if (vClass != null && vClass.getCcid() != null) {
            if (record.getState().equals(Constant.STUDENT_READING)) {
                //有班级，班级专业和变更专业不同
                if (record.getMid() != vClass.getMid()) {
                    return -1;
                }
            } else  if (record.getState().equals(Constant.STUDENT_DROPED)
                    || record.getState().equals(Constant.STUDENT_JOBED)
                    || record.getState().equals(Constant.STUDENT_SELECT)) {
                student.setCid(null);
                UClass uClass = new UClass();
                uClass.setCcid(vClass.getCcid());
                uClass.setNum((long) (-1));
                uClassMapper.updateNumByPrimayKey(uClass);
            }
            else{
                return -1;
            }
        }

        if (record.getState().equals(Constant.STUDENT_DROPED)
                || record.getState().equals(Constant.STUDENT_JOBED)
                || record.getState().equals(Constant.STUDENT_SELECT)) {
            //更新学生状态表
            UStudenteFinal uStudenteFinal = uStudenteFinalMapper.selectBySid(student.getSid());
            if (uStudenteFinal == null) {
                uStudenteFinal = new UStudenteFinal();
                uStudenteFinal.setSid(student.getSid());
                uStudenteFinal.setSname(student.getName());
                if (vClass != null && vClass.getCcid() != null) {
                    uStudenteFinal.setCcid(vClass.getCcid());
                    uStudenteFinal.setCcname(vClass.getClassname());
                    uStudenteFinal.setCname(vClass.getCompanyname());
                }

                uStudenteFinal.setCid(student.getuCCid());
                uStudenteFinal.setStage(record.getState());
                uStudenteFinal.setCreatetime(DateUtil.getCurrentDateTime());
                uStudenteFinalMapper.insert(uStudenteFinal);
            } else {
                uStudenteFinal.setSid(student.getSid());
                uStudenteFinal.setSname(student.getName());
                if (vClass != null && vClass.getCcid() != null) {
                    uStudenteFinal.setCcid(vClass.getCcid());
                    uStudenteFinal.setCcname(vClass.getClassname());
                    uStudenteFinal.setCname(vClass.getCompanyname());
                }
                uStudenteFinal.setCid(student.getuCCid());
                uStudenteFinal.setStage(record.getState());
                uStudenteFinal.setCreatetime(DateUtil.getCurrentDateTime());
                uStudenteFinalMapper.updateByPrimaryKey(uStudenteFinal);
            }

        }

        student.setName(record.getName());
        student.setSex(record.getSex());
        student.setTel(record.getTel());
        student.setQq(record.getQq());
        student.setRoomcode(record.getRoomcode());
        student.setEmail(record.getEmail());
        student.setFatherTel(record.getFatherTel());
        student.setMotherTel(record.getMotherTel());
        student.setOtherName(record.getOtherName());
        student.setOtherTel(record.getOtherTel());
        student.setMid(record.getMid());
        student.setState(record.getState());
        return uStudentMapper.updateByPrimaryKey(student);
    }

    @Override
    public List<UStudent> findByCid(Long ccid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ccid", ccid);
        return uStudentMapper.selectByParam(map);
    }

    @Override
    public List<UStudent> findByCidName(Long ccid, String name) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ccid", ccid);
        map.put("name", name);
        return uStudentMapper.selectByParam(map);
    }

    @Override
    public List<UStudent> findWithoutClass(Long mid) {
        UUser uUser = TokenManager.getToken();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mid", mid);
        map.put("cid", uUser.getCid());
        return uStudentMapper.selectWithoutClass(map);
    }

    @Override
    public List<UStudent> findByNameWithoutClass(Long mid, String name) {
        UUser uUser = TokenManager.getToken();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mid", mid);
        map.put("name", name);
        map.put("cid", uUser.getCid());
        return uStudentMapper.selectWithoutClass(map);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateCidBySid(Map<String, Object> map, int flg) {
        int ret = uStudentMapper.updateCidBySid(map);
        Long cid = (Long) map.get("updatecid");
        List<UStudent> uStudentList = (List<UStudent>) map.get("list");
        int snum = uStudentList.size();

        if (null != cid & snum != 0) {
            UClass uClass = new UClass();
            uClass.setCcid(cid);
            if (flg == 0) {
                uClass.setNum((long) snum);
            } else {
                uClass.setNum((long) (0 - snum));
            }
            uClassMapper.updateNumByPrimayKey(uClass);
        }

        return ret;
    }

    @Override
    public UStudent findByPrimaryKey(Long sid) {
        return uStudentMapper.selectByPrimaryKey(sid);
    }


}
