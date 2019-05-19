package com.hp.common.dao;


import com.hp.common.model.UTaskAccomplish;
import com.hp.task.bo.UTaskAccomplishResults;
import com.hp.task.bo.UTaskResults;
import com.hp.task.bo.UTaskbo;

import java.util.List;

public interface UTaskAccomplishMapper {

    //添加作业时自动添加学生作业成绩
    int insertbatch(List<UTaskAccomplish> uTaskAccomplishList);
    //查询班级学生名字
    List<UTaskAccomplish> findUtaskaccomplishName(Long tid);
    //修改成绩
    int updateGradeByPrimaryKey(List<UTaskAccomplish> uTaskAccomplishList);

    List<UTaskAccomplish> selectExamDetails(Long tid);

    List<UTaskAccomplish>  selectBytid (Long tid);

    UTaskbo selectUtaskBo(Long tid);
    /*导出学生项目成绩（只有学生姓名和成绩）*/
    List<UTaskAccomplishResults> exporeStudentProjectGrade(long tid);
    /*导出学生成绩（只有班级名称和班级项目合格率）*/
    UTaskResults exporeClassProjectGrade(long tid);
}