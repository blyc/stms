package com.hp.project.service.impl;

import com.hp.common.dao.UClassProjectMapper;
import com.hp.common.dao.UStudentProjectMapper;
import com.hp.common.model.UClassProject;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.project.service.ClassProjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/03/29
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class ClassProjectServiceImpl extends BaseMybatisDao<UClassProjectMapper> implements ClassProjectService {
    @Autowired
    private UClassProjectMapper uClassProjectMapper;

    @Autowired
    private UStudentProjectMapper uStudentProjectMapper;

    @Override
    public Pagination<UClassProject> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public Pagination<UClassProject> findReviewByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findReviewAll", "findReviewCount", resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> delete(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String[] idArray = new String[]{};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[]{ids};
            }

            for (String id : idArray) {
                uStudentProjectMapper.deleteByCpid(new Long(id));
                count += uClassProjectMapper.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除项目出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }
}
