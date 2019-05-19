package com.hp.project.service.impl;

import com.hp.common.dao.UClassProjectMapper;
import com.hp.common.dao.UStudentMapper;
import com.hp.common.dao.UStudentProjectMapper;
import com.hp.common.model.UClassProject;
import com.hp.common.model.UStudent;
import com.hp.common.model.UStudentProject;
import com.hp.common.model.UUser;
import com.hp.common.utils.DateUtil;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.project.bo.ExportClassProjectResults;
import com.hp.project.bo.ExportStudentProjectResults;
import com.hp.project.bo.UStudentProjectbo;
import com.hp.project.service.StudentProjectService;
import com.hp.project.util.ExporeStudentProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 金梦杰 on 2018/4/7/007.
 */
@Service
@Transactional(readOnly = true)
public class StudentProjectServiceImpl implements StudentProjectService {

    @Autowired
    private UStudentProjectMapper uStudentProjectMapper;
    @Autowired
    private UStudentMapper uStudentMapper;
    @Autowired
    private UClassProjectMapper uClassProjectMapper;

    /*添加项目信息*/
    @Override
    @Transactional(readOnly = false)
    public int insertBatch(UClassProject uClassProject) {
        UUser uUser = TokenManager.getToken();
        Long ccid = uClassProject.getCcid();
        Long cid = uUser.getCid();

        uClassProject.setCid(cid);
        uClassProject.setCreateId(uUser.getUid());
        uClassProject.setCreateTime(DateUtil.getCurrentDateTime());
        uClassProject.setFlg(0L);

        List<UStudentProject> uStudentProjects = new ArrayList<UStudentProject>();
        uClassProjectMapper.insert(uClassProject);
        List<UStudent> uStudentList = uStudentMapper.selectByCid(ccid);
        for (UStudent uStudent : uStudentList) {
            UStudentProject uStudentProject = new UStudentProject();
            uStudentProject.setCid(cid);
            uStudentProject.setCcid(ccid);

            Long sid = uStudent.getSid();/*学生id*/
            uStudentProject.setSid(sid);
            uStudentProject.setCpid(uClassProject.getCpid());

            uStudentProject.setVpass(0L);
            uStudentProject.setVsubmit(0L);
            uStudentProject.setCreateTime(uClassProject.getCreateTime());
            uStudentProject.setCreateId(uClassProject.getCreateId());
            uStudentProject.setRate(new BigDecimal(0));
            uStudentProjects.add(uStudentProject);
        }
        uStudentProjectMapper.insertBatch(uStudentProjects);
        return 0;
    }

    /*添加项目成绩*/
    @Override
    @Transactional(readOnly = false)
    public int updateProjectByPrimaryKeyService(Map<String, Object> map) {
        List<UStudentProjectbo> uStudentProjectbos = (List<UStudentProjectbo>) map.get("uStudentProjectbos");
        Long cpid = (Long) map.get("cpid");
        /*总人数的数据*/
        int countStudent = uStudentProjectbos.size();
        Long vpass = 0L;
        Long vsubmit = 0L;
        for (UStudentProjectbo u : uStudentProjectbos) {
            BigDecimal rate1 = new BigDecimal(0);
            if (u.getRate() != null && !u.getRate().equals("")) {
                rate1 = u.getRate().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
            }
            UStudentProject uStudentProject = new UStudentProject();
            uStudentProject.setSpid(u.getSpid());
            uStudentProject.setRate(rate1);
            uStudentProject.setVsubmit(u.getVsubmit());
            if (u.getVsubmit() != -1) {
                vsubmit = vsubmit + u.getVsubmit();
            }
            uStudentProject.setVaddr(u.getVaddr());
            uStudentProject.setVpass(u.getVpass());
            if (u.getVpass() != -1) {
                vpass = vpass + u.getVpass();
            }
            uStudentProject.setVtime(u.getVtime());
            uStudentProject.setCodeaddr(u.getCodeaddr());
            BigDecimal rate2 = new BigDecimal(0);
            if (u.getCoderate() != null && !u.getCoderate().equals("")) {
                rate2 = u.getCoderate().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);
            }
            uStudentProject.setCoderate(rate2);
            uStudentProject.setCreateTime(new Date());
            uStudentProjectMapper.updateProjectByPrimaryKey(uStudentProject);
        }
        BigDecimal d=new BigDecimal(0);
        if(vsubmit!=0){
            d= new BigDecimal(vpass).divide(new BigDecimal(vsubmit), 4, BigDecimal.ROUND_HALF_UP);
        }

        UClassProject uClassProject = new UClassProject();
        uClassProject.setCpid(cpid);
        uClassProject.setSqualified(d);
        uClassProjectMapper.updateProjectGaradeBycpid(uClassProject);
        return uClassProjectMapper.updateFlgByPrimaryKey(cpid);
    }


    @Override
    public List<UStudentProject> findProjectStudentName(Long cpid) {
        return uStudentProjectMapper.selectStudentNameProjectByCpid(cpid);
    }

    @Override
    public List<UStudentProject> showStudentProjectQualifiedInfoService(Long cpid) {
        return uStudentProjectMapper.showStudentProjectQualifiedInfo(cpid);
    }

    /*导出学生项目成绩信息*/
    @Override
    @Transactional(readOnly = false)
    public int exportStudentProjectGradeService(HttpServletResponse response, Long cpid) {
        //根据申请班级项目编号查询学生的项目成绩（合格与否）uStudentProjectbo
        /*先导出学生的项目成绩*/
        List<ExportStudentProjectResults> exportStudentProjectResultsList = uStudentProjectMapper.exporeStudentProjectGrade(cpid);
        /*在这个方法里，查询出该班级名称和班级合格率*/
        ExportClassProjectResults ecpr = uStudentProjectMapper.exporeClassProjectGrade(cpid);

        ExporeStudentProjectUtil poiExcelExport = new ExporeStudentProjectUtil("sheet1");
        String fileName = "exportStudentProject";
        poiExcelExport.wirteExcel(fileName, response, ecpr, exportStudentProjectResultsList);
        return 0;

    }
}
