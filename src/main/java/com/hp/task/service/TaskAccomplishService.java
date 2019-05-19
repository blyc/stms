package com.hp.task.service;

import com.hp.common.model.UTaskAccomplish;
import com.hp.task.bo.UTaskAccomplishbo;
import com.hp.task.bo.UTaskbo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Dragon on 2018/4/13 0013.
 */
public interface TaskAccomplishService {

    //查询班级学生名字
    public List<UTaskAccomplish> findUtaskaccomplishName(Long tid);
    //修改作业成绩
    public  int updateByPrimaryKey(List<UTaskAccomplishbo> uTaskAccomplishbos,Long tid);
    //显示学生完成情况
    public List<UTaskAccomplish> findExamDetails(Long tid);

    public UTaskbo findUTaskboeBo(Long tid);
    /*导出学生信息*/
    public int exportStudentProjectGradeService(HttpServletResponse response, Long tid);


}
