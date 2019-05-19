package com.hp.check.service.impl;


import com.hp.check.service.UCheckClassService;
import com.hp.common.dao.UCheckClassMapper;
import com.hp.common.model.UCheckClass;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by lenovo on 2018/3/28.
 */
@Service
@Transactional(readOnly = true)
public class UCheckClassServiceImpl extends BaseMybatisDao<UCheckClassMapper> implements UCheckClassService {
    @Autowired
    UCheckClassMapper uCheckClassMapper;

    @Override
    public Pagination<UCheckClass> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public UCheckClass insertCheckClass(UCheckClass record) {

        if (null == record) {
            return null;
        }
        uCheckClassMapper.insertCheckClass(record);

        return record;

    }

    @Override
    @Transactional(readOnly = false)
    public UCheckClass updateUCheckClassByChid(UCheckClass record) {
        uCheckClassMapper.updateUCheckClassByChid(record);
        return record;
    }

}
