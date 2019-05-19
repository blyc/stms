package com.hp.student.service;

import com.hp.common.model.UStudent;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/02/25
 * @Description:
 */
public interface StudentService {
    public Pagination<UStudent> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

    public UStudent insertSelective(UStudent record);

    public int updateByPrimaryKeySelective(UStudent record);

    public List<UStudent> findByCid(Long cid);

    /**
     * 根据姓名查询有班级的学生
     * @param cid
     * @param name
     * @return
     */
    public List<UStudent> findByCidName(Long cid, String name);

    public List<UStudent> findWithoutClass(Long mid);

    /**
     * 根据姓名查询无班级的学生
     * @param mid
     * @param name
     * @return
     */
    public List<UStudent> findByNameWithoutClass(Long mid, String name);


    public int updateCidBySid(Map<String, Object> map, int flg);

    public UStudent findByPrimaryKey(Long sid);

    public UStudent findByIdCard(String idCard);
}
