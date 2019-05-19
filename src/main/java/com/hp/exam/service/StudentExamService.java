package com.hp.exam.service;

import com.hp.common.model.UClassExam;
import com.hp.common.model.UStudentExam;
import com.hp.exam.bo.UCLassExamebo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by 晏利花 on 2018/3/21.
 */
public interface StudentExamService {

    public int insertBatch(UClassExam uClassExam);

    public  int updateByPrimaryKey(Map<String, Object> map) ;

    public List<UStudentExam> findExamDetails(Long ceid);

    public List<UStudentExam> findExamStudentName(Long eid);

    public UCLassExamebo findClassExameBo(Long eid);

    //学生成绩导出
    public void exportStuentExam(HttpServletResponse response,Long ceid);

}
