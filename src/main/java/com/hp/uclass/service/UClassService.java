package com.hp.uclass.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UClass;
import com.hp.common.model.VClass;
import com.hp.student.bo.Paymentbo;
import com.hp.vclass.bo.VClassBo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/25 0025.
 */
public interface UClassService {

    public UClass selectByPrimaryKey(Long id);

    /**
     * 根据班级id查询班级信息
     *
     * @param vClass
     * @return
     */
    public VClassBo findClass(VClass vClass);

    /**
     * 根据校区id年级下拉框
     *
     * @return
     */
    public List<KeyAndValue> findUClassGradeByCid(Long id);

    public UClass insert(UClass record, HttpServletRequest request);

    public boolean delete(UClass record, HttpServletRequest request);

    //保存老师-学生关系
    public void insertTeacherClass(HttpServletRequest request);

    //根据班级id 职位查询老师
    public Map findByCidAndPosition(Map map);

    //查询现有的班级数量
//    public List<KeyAndValue> findExistingCount(UClass record);
    public List<KeyAndValue> findMajorClassCountByGrade(String Grade);


    //查询班级名字
    public List<UClass> findUClassByCid();

    //班级下拉框
    public List<KeyAndValue> findUClassByParam(Map map);


    public UClass updateStage(List<Paymentbo> paymentbos, Long id);

    //班级相关老师查询
//    public List<KeyAndValue> findUClassEmployee(Map map);

}
