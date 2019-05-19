package com.hp.common.dao;

import com.hp.common.model.VClass;
import com.hp.vclass.bo.VClassBo;

import java.util.List;
import java.util.Map;

public interface VClassMapper {
    public VClass SelectVClassByccid(Long ccid);

    public VClass findVClassByCcid(Long ccid);

    //查询班级根据条件（通用）
    public List<VClass> findClassByParam(Map map);

    //查询班级年级
    public List<String> findClassGradeByParam(Map map);

/*    *
     * 根据年级和专业，查询班级情况
     * @param map
     * @return*/

    public VClassBo findClassBoByParam(Map map);
}