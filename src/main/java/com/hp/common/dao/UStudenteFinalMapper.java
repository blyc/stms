package com.hp.common.dao;


import com.hp.common.model.UStudenteFinal;

public interface UStudenteFinalMapper {
    int deleteByPrimaryKey(Long ufid);

    int insert(UStudenteFinal record);

    int insertSelective(UStudenteFinal record);

    UStudenteFinal selectByPrimaryKey(Long ufid);

    int updateByPrimaryKeySelective(UStudenteFinal record);

    int updateByPrimaryKey(UStudenteFinal record);

    UStudenteFinal selectBySid(Long sid);
}