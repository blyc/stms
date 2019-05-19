package com.hp.project.service;

import com.hp.common.model.UClassProject;
import com.hp.common.model.UStudentProject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 金梦杰 on 2018/4/7/007.
 */
public interface StudentProjectService {
    /*申请项目用到的方法*/
    public int insertBatch(UClassProject uClassProject);
    /*记录考试详情，学生控制器用到的*/
    public  int updateProjectByPrimaryKeyService(Map<String, Object> map) ;
    /*记录成绩显示学生姓名-------学生成绩学生控制器*/
    public List<UStudentProject> findProjectStudentName(Long cpid);
    /*根据spid查询出学生项目合格详情*/
    List<UStudentProject> showStudentProjectQualifiedInfoService(Long cpid);
   /*导出学生信息*/
   public int exportStudentProjectGradeService(HttpServletResponse response,Long cpid);






}
