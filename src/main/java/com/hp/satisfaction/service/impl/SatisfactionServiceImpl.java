package com.hp.satisfaction.service.impl;

import com.hp.common.dao.USatisfactionMapper;
import com.hp.common.model.USatisfaction;
import com.hp.common.model.UUser;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.satisfaction.service.SatisfactionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/08/10
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class SatisfactionServiceImpl extends BaseMybatisDao<USatisfactionMapper> implements SatisfactionService {
    @Autowired
    private USatisfactionMapper uSatisfactionMapper;

    @Override
    public Pagination<USatisfaction> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public USatisfaction addUSatisfaction(USatisfaction u) {
        UUser uUser = TokenManager.getToken();
        u.setSurveyTime(DateUtil.getCurrentDateTime());
        u.setCreateTime(DateUtil.getCurrentDateTime());
        u.setCreateId(uUser.getUid());
        u.setCid(uUser.getCid());
        u.setScore(u.getScore().divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP));
        uSatisfactionMapper.insertSelective(u);
        return u;
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
                count += uSatisfactionMapper.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除满意度出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;

    }
}
