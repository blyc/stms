package com.hp.task.service.impl;

import com.hp.common.dao.UTaskAccomplishMapper;
import com.hp.common.dao.UTaskMapper;
import com.hp.common.model.UTaskAccomplish;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.task.bo.UTaskAccomplishResults;
import com.hp.task.bo.UTaskAccomplishbo;
import com.hp.task.bo.UTaskResults;
import com.hp.task.bo.UTaskbo;
import com.hp.task.service.TaskAccomplishService;
import com.hp.task.utils.ExporeStudentProjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragon on 2018/4/13 0013.
 */
@Service
@Transactional(readOnly = true)
public class TaskAccomplishServiceImpl extends BaseMybatisDao<UTaskAccomplishMapper> implements TaskAccomplishService {
    @Autowired
    private UTaskAccomplishMapper uTaskAccomplishMapper;
    @Autowired
    private UTaskMapper uTaskMapper;
    @Override
    public List<UTaskAccomplish> findUtaskaccomplishName(Long tid) {
        return uTaskAccomplishMapper.findUtaskaccomplishName(tid);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKey(List<UTaskAccomplishbo> uTaskAccomplishbos,Long tid) {
        uTaskMapper.updateMarkByPrimaryKey(tid);
        List<UTaskAccomplish> list = uTaskAccomplishMapper.selectBytid(tid);

        List<UTaskAccomplish> taskAccomplishList = new ArrayList<UTaskAccomplish>();


            for (int i=0;i<list.size();i++){
                UTaskAccomplish uTaskAccomplish=new UTaskAccomplish();
                uTaskAccomplish.setUtid(list.get(i).getUtid());
                uTaskAccomplish.setTaskgrade(uTaskAccomplishbos.get(i).getTaskgrade());
                taskAccomplishList.add(uTaskAccomplish);


        }
        uTaskAccomplishMapper.updateGradeByPrimaryKey(taskAccomplishList);
        return 0;
    }

    @Override
    public List<UTaskAccomplish> findExamDetails(Long tid) {
        return uTaskAccomplishMapper.selectExamDetails(tid);
    }

    @Override
    public UTaskbo findUTaskboeBo(Long tid) {
        return uTaskAccomplishMapper.selectUtaskBo(tid);
    }

    @Override
    @Transactional(readOnly = false)
    public int exportStudentProjectGradeService(HttpServletResponse response, Long tid) {
        //根据申请班级项目编号查询学生的项目成绩（合格与否）uStudentProjectbo
        /*先导出学生的项目成绩*/
        List<UTaskAccomplishResults> exportStudentProjectResultsList = uTaskAccomplishMapper.exporeStudentProjectGrade(tid);

        UTaskResults ecpr = uTaskAccomplishMapper.exporeClassProjectGrade(tid);
        ExporeStudentProjectUtil poiExcelExport = new ExporeStudentProjectUtil( "sheet1");
        String fileName = "taskaccomplish";
        poiExcelExport.wirteExcel(fileName, response, ecpr, exportStudentProjectResultsList);
        return 0;
    }
}
