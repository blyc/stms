package com.hp.exam.service.impl;

import com.hp.common.dao.UClassExamMapper;
import com.hp.common.dao.UClassMapper;
import com.hp.common.dao.UStudentExamMapper;
import com.hp.common.dao.UStudentMapper;
import com.hp.common.model.*;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.StudentGradeUtil;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.exam.bo.ExportStudentExambo;
import com.hp.exam.bo.UCLassExamebo;
import com.hp.exam.bo.UStudentExambo;
import com.hp.exam.service.StudentExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by 晏利花 on 2018/3/21.
 */
@Service
@Transactional(readOnly = true)
public class StudentExamServiceImpl implements StudentExamService {
    @Autowired
    private UStudentExamMapper uStudentExamMapper;
    @Autowired
    private UStudentMapper uStudentMapper;
    @Autowired
    private UClassMapper uClassMapper;
    @Autowired
    private UClassExamMapper uClassExamMapper;


    @Override
    @Transactional(readOnly = false)
    public int insertBatch(UClassExam uClassExam) {
        UUser uUser = TokenManager.getToken();
        uClassExam.setCid(uUser.getCid());
        uClassExam.setCreatetime(DateUtil.getCurrentDateTime());
        uClassExam.setUid(uUser.getUid());
        uClassExam.setMark(0);

        Long ccid = uClassExam.getCcid();
        Long cid = uClassExam.getCid();

        List<UStudentExam> uStudentExamList = new ArrayList<UStudentExam>();
        uClassExamMapper.insert(uClassExam);
        List<UStudent> uStudentList = uStudentMapper.selectByCid(ccid);
        for (UStudent uStudent : uStudentList) {
            UStudentExam uStudentExam = new UStudentExam();
            uStudentExam.setCid(cid);
            uStudentExam.setCcid(ccid);
            Long sid = uStudent.getSid();
            uStudentExam.setSid(sid);
            uStudentExam.setCeid(uClassExam.getCeid());
            uStudentExam.setRegisterexam(uClassExam.getExamtime());
            uStudentExam.setExamgrade(0.0);
            uStudentExamList.add(uStudentExam);
        }
        uStudentExamMapper.insertBatch(uStudentExamList);
        return 0;
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(Map<String, Object> map) {

        List<UStudentExambo> uStudentExambos = (List<UStudentExambo>) map.get("studentExambos");
        Long ceid = (Long) map.get("ceid");
        Double rateStandard = null;


        UClassExam uClassExam =  uClassExamMapper.selectByPrimaryKey(ceid);
        if(uClassExam!=null){
            rateStandard = uClassExam.getRateStandard();
        }

        if(rateStandard==null){
            rateStandard = 60D;
        }

        //学生数量
        int studentCount = uStudentExambos.size();
        int gradeokcount = 0;
        for (UStudentExambo u : uStudentExambos) {
            UStudentExam uStudentExam = new UStudentExam();
            uStudentExam.setUseid(u.getUseid());
            uStudentExam.setExamgrade(u.getExamgrade());
            uStudentExam.setRegisterexam(new Date());
            uStudentExam.setEaddr(u.getEaddr());

            uStudentExamMapper.updateGradeByPrimaryKey(uStudentExam);
            if (u.getExamgrade() >= rateStandard) {
                gradeokcount++;
            }
        }
        BigDecimal qualifiedrate = new BigDecimal(gradeokcount).divide(new BigDecimal(studentCount), 4,BigDecimal.ROUND_HALF_UP);
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("ceid", ceid);
        map1.put("qualifiedrate", qualifiedrate);
        return uClassExamMapper.updateMarkByPrimaryKey(map1);
    }


    @Override
    public List<UStudentExam> findExamDetails(Long ceid) {
        return uStudentExamMapper.selectExamDetails(ceid);
    }

    @Override
    public List<UStudentExam> findExamStudentName(Long eid) {
        return uStudentExamMapper.selectStudentExam(eid);
    }

    @Override
    public UCLassExamebo findClassExameBo(Long eid) {
        return uStudentExamMapper.selectClassExameBo(eid);
    }

    @Override
    public void exportStuentExam(HttpServletResponse response, Long ceid) {
        //根据申请班级考试编号查询学生考试信息selectClassExameBo
        List<UStudentExam> uStudentExamList = uStudentExamMapper.selectStudentExam(ceid);
        UCLassExamebo uclassExamebo = uStudentExamMapper.selectClassExameBo(ceid);
        UClass uClass = uClassMapper.selectByPrimaryKey(uStudentExamList.get(0).getCcid());
        List<ExportStudentExambo> exportStudentExamboList = new ArrayList<ExportStudentExambo>();

        ExportStudentExambo exportStudentExambo = null;
        for (int i = 0; i < uStudentExamList.size(); i++) {
            UStudentExam uStudentExam = uStudentExamList.get(i);
            exportStudentExambo = new ExportStudentExambo();
            exportStudentExambo.setExamgrade(uStudentExam.getExamgrade());
            exportStudentExambo.setSname(uStudentExam.getuStudent().getName());

            uStudentExam.setCeid((long) (i + 1));
            exportStudentExambo.setCeid(uStudentExam.getCeid());
            exportStudentExamboList.add(exportStudentExambo);
        }


        StudentGradeUtil studentGradeUtil = new StudentGradeUtil("sheet1");
        String titleName[] = {"编号", "姓名", "成绩"};
        String titleColumn[] = {"ceid", "sname", "examgrade"};

        int titleSize[] = {13, 13, 13};

        String endName[] = {"最高分", "最低分", "合格人数", "平均分", "合格率"};
        String endColumn[] = {"hightgrade", "lowgrade", "qualified", "avggrade", "qualifiedrate"};


        String fileName = uClass.getName();
        //String fileName ="exportStudentinfo";
        studentGradeUtil.wirteExcel(fileName, response, titleColumn, titleName, titleSize, exportStudentExamboList, uclassExamebo, endColumn, endName);

        return;
    }
}
