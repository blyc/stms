package com.hp.common.dao;


import com.hp.common.model.UStudentExam;
import com.hp.exam.bo.UCLassExamebo;

import java.util.List;

public interface UStudentExamMapper {

    int updateGradeByPrimaryKey(UStudentExam record);

    int insertBatch(List<UStudentExam> uStudentExamList);

    List<UStudentExam> selectExamDetails(Long ceid);

    List<UStudentExam> selectStudentExam(Long ceid);

    UCLassExamebo selectClassExameBo(Long ceid);

    int deleteByCeid(Long ceid);
}