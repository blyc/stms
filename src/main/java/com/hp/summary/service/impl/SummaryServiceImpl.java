package com.hp.summary.service.impl;

import com.hp.common.dao.VSummaryDataMapper;
import com.hp.common.model.VSummaryData;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.summary.service.SummaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/10/28
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class SummaryServiceImpl extends BaseMybatisDao<VSummaryDataMapper> implements SummaryService {

    @Override
    public Pagination<VSummaryData> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }
    @Override
    public Pagination<VSummaryData> findTeacherByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findTeacherAll", "findTeacherCount", resultMap, pageNo, pageSize);
    }

    @Override
    public Pagination<VSummaryData> findMajorByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findMajorAll", "findMajorCount", resultMap, pageNo, pageSize);
    }
    @Override
    public Pagination<VSummaryData> findStudentByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findStudentAll", "findStudentCount", resultMap, pageNo, pageSize);
    }
}
