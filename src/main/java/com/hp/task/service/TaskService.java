package com.hp.task.service;


import com.hp.common.model.UTask;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * Created by Dragon on 2018/4/11 0011.
 */
public interface TaskService {
    //根据班级查询作业显示列表
    public Pagination<UTask> findBypage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    //添加作业
    public int insertUtask(UTask uTask);
   /* //查询班级学生名字
    public List<UTask> findUtaskbytid(Long tid);*/

}
