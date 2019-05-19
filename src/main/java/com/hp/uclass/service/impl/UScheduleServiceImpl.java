package com.hp.uclass.service.impl;

import com.hp.common.dao.UScheduleMapper;
import com.hp.common.model.USchedule;
import com.hp.common.utils.SubscriptUtil;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.uclass.service.UScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by DELL on 2018/3/23.
 */
@Service
@Transactional(readOnly = true)
public class UScheduleServiceImpl extends BaseMybatisDao<USchedule> implements UScheduleService{
    @Autowired
    private UScheduleMapper uScheduleMapper;



    @Override
    @Transactional(readOnly = false)
    public int insert(USchedule record) {
        return uScheduleMapper.insert(SubscriptUtil.Generatingsubscript(record));
    }
}
