package com.hp.uclass.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.*;
import com.hp.common.model.*;
import com.hp.common.utils.DateUtil;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.core.statics.Constant;
import com.hp.student.bo.Paymentbo;
import com.hp.uclass.service.UClassService;
import com.hp.vclass.bo.VClassBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

/**
 * Created by Administrator on 2018/2/25 0025.
 */
@Service
@Transactional(readOnly = true)
public class UClassServiceImpl extends BaseMybatisDao<UClassMapper> implements UClassService {
    @Autowired
    private UClassMapper uClassMapper;
    @Autowired
    private UTeacherClassMapper uTeacherClassMapper;
    @Autowired
    private UMajorMapper uMajorMapper;

    @Autowired
    private VClassMapper vClassMapper;

    @Autowired
    private UStudentMapper uStudentMapper;

    @Autowired
    private UPaymentMapper uPaymentMapper;

    @Override
    public UClass selectByPrimaryKey(Long id) {
        return uClassMapper.selectByPrimaryKey(id);
    }

    @Override
    public VClassBo findClass(VClass vClass) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mid", vClass.getMid());
        map.put("cid", vClass.getCid());
        map.put("stage", vClass.getStage());
        map.put("grade", vClass.getGrade());

        VClassBo vClassBo = vClassMapper.findClassBoByParam(map);
        return vClassBo;
    }

    @Override
    public List<KeyAndValue> findUClassGradeByCid(Long id) {
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("cid", id);
        List<String> grades = vClassMapper.findClassGradeByParam(map);
        if (grades == null || grades.size() <= 0) {
            return list;
        }
        for (String u : grades) {
            KeyAndValue keyAndValue = new KeyAndValue(u, u + "级");
            list.add(keyAndValue);
        }
        return list;
    }

    @Override
    public List<KeyAndValue> findMajorClassCountByGrade(String grade) {
        //校区
        //校区id
        Long cid = TokenManager.getToken().getCid();

        List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("grade", grade);
        map.put("cid", cid);
        List<Map<String, Object>> mapList = uClassMapper.findMajorClassCountByParam(map);
        if (mapList == null) {
            return null;
        }

        for (Map m : mapList) {
            Long count = (Long) m.get("classCount");
            if (count == null) {
                count = 0L;
            }
//            KeyAndValue keyAndValue = new KeyAndValue(m.get("majorName"), count);
            KeyAndValue keyAndValue = new KeyAndValue(m.get("mid"), count);
            list.add(keyAndValue);
        }
        return list;
    }

    @Override
    public List<UClass> findUClassByCid() {
        Long cid = TokenManager.getToken().getCid();

        Map map = new HashMap();
        map.put("cid", cid);

        List<UClass> uClassList = uClassMapper.findUClassByCid(map);
        if (uClassList == null || uClassList.size() <= 0) {
            return null;
        }

       /* List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        for(UClass u:uClassList){
            KeyAndValue keyAndValue1 = new KeyAndValue(u.getCcid(),u.getName());
            KeyAndValue keyAndValue2 = new KeyAndValue(u.getCid(),u.getuCompany().getName());
            list.add(keyAndValue1);
            list.add(keyAndValue2);
        }*/
        return uClassList;
    }

    /**
     * 根据条件查询班级id和name（key value）
     *
     * @param map
     * @return
     */
    @Override
    public List<KeyAndValue> findUClassByParam(Map map) {
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        map.put("state","删除");
        List<VClass> uClasses = vClassMapper.findClassByParam(map);
        if (uClasses == null || uClasses.size() <= 0) {
            return list;
        }

        for (VClass u : uClasses) {
            KeyAndValue keyAndValue = new KeyAndValue(u.getCcid(), u.getClassname());
            list.add(keyAndValue);
        }

        return list;
    }


    public Map<String, Long> findAllMajorMidByName() {
        List<UMajor> uMajors = uMajorMapper.findAllMajor();
        if (uMajors == null || uMajors.size() <= 0) {
            return null;
        }

        Map<String, Long> map = new HashMap<String, Long>();

        for (UMajor u : uMajors) {
            map.put(u.getName(), u.getMid());
        }
        return map;
    }

    public Map<Long, String> findAllMajorNameByMid() {
        List<UMajor> uMajors = uMajorMapper.findAllMajor();
        if (uMajors == null || uMajors.size() <= 0) {
            return null;
        }

        Map<Long, String> map = new HashMap<Long, String>();

        for (UMajor u : uMajors) {
            map.put(u.getMid(), u.getName());
        }
        return map;
    }

    @Override
    @Transactional(readOnly = false)
    public UClass insert(UClass record, HttpServletRequest request) {
        //参数
        String[] mids = request.getParameterValues("mids[]");

        //批量添加数据
        List<UClass> uClassList = new ArrayList<UClass>();

        //校区id
        Long cid = TokenManager.getToken().getCid();
        //校区
        Map<Long, String> majorMap = findAllMajorNameByMid();

        for (int i = 0; i < mids.length; i++) {
            long mid = Long.parseLong(mids[i]);
            String tnum = request.getParameter("t"+mid+"num"); //新增班级数量
            if (tnum != null && !"".equals(tnum)) {
                int tn = Integer.parseInt(tnum);
                //该校区该年级班级后缀名最大的值
                int maxClassNum = uClassMapper.findMaxClassNum(mid, cid, record.getGrade());
                for (int j = maxClassNum + 1; j <= maxClassNum + tn; j++) {
                    String className; //班级名称
                    className = majorMap.get(mid) + record.getGrade() + "-" + j + "班";
                    UClass uClass = new UClass();
                    uClass.setCid(cid);
                    uClass.setMid(mid);
                    uClass.setGrade(record.getGrade());
                    uClass.setName(className);
                    uClass.setNum((long) 0);
                    uClass.setStage(Constant.LEARNING_STAGE_S1);
                    uClassList.add(uClass);
                }
            }
        }
        uClassMapper.insertManyUClass(uClassList);
        return record;
    }

    //删除班级 --> 将班级state改为“删除”
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public boolean delete(UClass record, HttpServletRequest request){
        record.setState("删除");
        boolean delS = uStudentMapper.deleteStudentByCid(record) > 0;
        boolean delC = uClassMapper.updateByPrimaryKeySelective(record) > 0;
        return delC&&delS;
    }

    //保存老师-学生关系
    @Override
    @Transactional(readOnly = false)
    public void insertTeacherClass(HttpServletRequest request) {
        // 获取参数
        String id = request.getParameter("ccid");
        long ccid = Long.parseLong(id); //班级id
        String js = request.getParameter("js");
        String ds = request.getParameter("ds");
        String bzr = request.getParameter("bzr");
        Map map = new HashMap();
        map.put("ccid", ccid);
        UTeacherClass uTeacherClass = new UTeacherClass();
        //讲师-班级
        if (js != null && !"".equals(js)) {
            uTeacherClass.setCid(ccid);
            uTeacherClass.setEid(Long.parseLong(js));
            uTeacherClass.setPosition("js");
            Map mp = findByCidAndPosition(map);
            if (mp.get("js") != null) {
                uTeacherClassMapper.updateByCidAndPosition(uTeacherClass);
            } else {
                uTeacherClassMapper.insert(uTeacherClass);
            }
        }
        //导师-班级
        if (ds != null && !"".equals(ds)) {
            uTeacherClass.setCid(ccid);
            uTeacherClass.setEid(Long.parseLong(ds));
            uTeacherClass.setPosition("ds");
            Map mp = findByCidAndPosition(map);
            if (mp.get("ds") != null) {
                uTeacherClassMapper.updateByCidAndPosition(uTeacherClass);
            } else {
                uTeacherClassMapper.insert(uTeacherClass);
            }
        }
        //班主任-班级
        if (bzr != null && !"".equals(bzr)) {
            uTeacherClass.setCid(ccid);
            uTeacherClass.setEid(Long.parseLong(bzr));
            uTeacherClass.setPosition("bzr");
            Map mp = findByCidAndPosition(map);
            if (mp.get("bzr") != null) {
                uTeacherClassMapper.updateByCidAndPosition(uTeacherClass);
            } else {
                uTeacherClassMapper.insert(uTeacherClass);
            }
        }
    }

    //根据班级id 职位查询老师
    @Override
    public Map findByCidAndPosition(Map map) {
        List<UTeacherClass> list = uTeacherClassMapper.findByCidAndPosition(map);
        Map mp = new HashMap();
        mp.put("ccid", map.get("ccid"));  //班级id
        for (UTeacherClass u : list) {
            mp.put(u.getPosition(), u.getEid()); //职位:老师名称
        }
        return mp;
    }

    @Override
    @Transactional(readOnly = false)
    public UClass updateStage(List<Paymentbo> paymentbos, Long id) {
        UClass uClass = uClassMapper.selectByPrimaryKey(id);
        if (uClass == null) {
            return null;
        }

        String stage = uClass.getStage();

        if (Constant.LEARNING_STAGE_S1.equals(stage)) {
            stage = Constant.LEARNING_STAGE_S2;
        } else if (Constant.LEARNING_STAGE_S2.equals(stage)) {
            stage = Constant.LEARNING_STAGE_S3;
        }

        //记录旧数据
        Map<Long, Paymentbo> upayMap = new HashMap<Long, Paymentbo>();
        for (Paymentbo p : paymentbos) {
            upayMap.put(p.getSid(), p);
        }

        List<UStudent> students = uStudentMapper.selectByCid(id);
        if (students == null && students.size() <= 0) {
            return null;
        }

        Long num = 0L;

        for (UStudent u : students) {
            Paymentbo p = upayMap.get(u.getSid());
            if (p.getRecord() == 1) {
                u.setStage(stage);
                num++;
            } else if (p.getRecord() == 2) {
                u.setStage("");
                u.setState("退学");
                u.setCid(-1L);
            } else if (p.getRecord() == 3) {
                u.setStage("");
                u.setState("转专业");
                u.setCid(-1L);
            } else if (p.getRecord() == 4) {
                u.setStage("");
                u.setState("其他");
                u.setCid(-1L);
            }
            uStudentMapper.updateByPrimaryKeySelective(u);
        }

        //升学率
        Long oldnum = uClass.getNum();

        uClass.setNum(num);
        uClass.setStage(stage);
        uClassMapper.updateByPrimaryKeySelective(uClass);


        UPayment uPayment = new UPayment();
        uPayment.setCid(uClass.getCid());
        uPayment.setCcid(uClass.getCcid());
        uPayment.setNum(oldnum);
        uPayment.setPaynum(num);
        BigDecimal b1 = new BigDecimal(num);
        BigDecimal b2 = new BigDecimal(oldnum);

        uPayment.setPayval(b1.divide(b2, 2, RoundingMode.HALF_UP));
        uPayment.setCreateTime(DateUtil.getCurrentDateTime());

        if (Constant.LEARNING_STAGE_S2.equals(stage)) {
            uPayment.setStage("s1升s2");
        } else if (Constant.LEARNING_STAGE_S3.equals(stage)) {
            uPayment.setStage("s2升s3");
        }

        uPaymentMapper.insertSelective(uPayment);

        return uClass;
    }

  /*  public List<KeyAndValue> findUClassEmployee(Map map){


        vClassMapper.findClassByParam()
    }*/
}
