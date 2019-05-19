package com.hp.uclass.service.impl;

import com.hp.common.dao.UClassHourMapper;
import com.hp.common.model.UClassHour;
import com.hp.common.model.UUser;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.uclass.service.UClassHourService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/23
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class UClassHourServiceImpl extends BaseMybatisDao<UClassHourMapper> implements UClassHourService {

    @Autowired
    private UClassHourMapper uClassHourMapper;

    @Override
    public Pagination<UClassHour> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public UClassHour addUClassHour(UClassHour u) {
        UUser uUser = TokenManager.getToken();
        u.setSurveyTime(DateUtil.getCurrentDateTime());
        u.setCreateTime(DateUtil.getCurrentDateTime());
        u.setCreateId(uUser.getUid());
        u.setCid(uUser.getCid());
        uClassHourMapper.insertSelective(u);
        return u;
    }

    @Override
    @Transactional(readOnly = false)
    public int update(UClassHour record) {
        if (record == null || record.getUchid() == null) {
            return -1;
        }
        UClassHour uClassHour = this.findByPk(record.getUchid());
        if (uClassHour == null) {
            return -1;
        }
        uClassHour.setCount(record.getCount());
        uClassHour.setDayCount(record.getDayCount());
        uClassHour.setSurveyTime(DateUtil.getCurrentDateTime());

        return uClassHourMapper.updateByPrimaryKeySelective(uClassHour);
    }

    @Override
    public UClassHour findByPk(Long id) {
        return uClassHourMapper.selectByPrimaryKey(id);
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
                count += uClassHourMapper.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除课时出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }
}
