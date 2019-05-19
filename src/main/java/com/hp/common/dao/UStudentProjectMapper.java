package com.hp.common.dao;

import com.hp.common.model.UStudentProject;
import com.hp.project.bo.ExportClassProjectResults;
import com.hp.project.bo.ExportStudentProjectResults;

import java.util.List;

public interface UStudentProjectMapper {

    int insertBatch(List<UStudentProject> uStudentProjectList);

    /*记录考试成绩--对项目成绩作更新*/
    int updateProjectByPrimaryKey(UStudentProject record);

    /*记录增加成绩中学生姓名显示(根据班级考试id)*/
    List<UStudentProject> selectStudentNameProjectByCpid(Long cpid);

    /*根据spid查询出学生项目合格详情*/
    List<UStudentProject> showStudentProjectQualifiedInfo(Long cpid);

    /*导出学生项目成绩（只有学生姓名和成绩）*/
    List<ExportStudentProjectResults> exporeStudentProjectGrade(long cpid);

    /*导出学生成绩（只有班级名称和班级项目合格率）*/
    ExportClassProjectResults exporeClassProjectGrade(long cpid);

    List<UStudentProject> selectProjectDetails(Long cpid);

    int deleteByCpid(Long cpid);

}