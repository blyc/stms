package com.hp.exam.service;

import com.hp.common.model.UClassExam;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * Created by 晏利花 on 2018/3/20.
 */
public interface ClassExamService {
    public Pagination<UClassExam> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    public Pagination<UClassExam> findReviewByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    public int updateExameventByPrimaryKey(UClassExam record);
    public Map<String, Object> delete(String ids);

}
