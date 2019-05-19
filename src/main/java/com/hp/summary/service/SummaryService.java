package com.hp.summary.service;

import com.hp.common.model.VSummaryData;
import com.hp.core.mybatis.page.Pagination;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/10/28
 * @Description:
 */
public interface SummaryService {
    public Pagination<VSummaryData> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    public Pagination<VSummaryData> findTeacherByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    public Pagination<VSummaryData> findMajorByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
    public Pagination<VSummaryData> findStudentByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);

}
