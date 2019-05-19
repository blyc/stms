package com.hp.vclass.service.impl;

import com.hp.common.dao.VClassMapper;
import com.hp.common.model.VClass;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.vclass.service.VClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2018/2/25 0025.
 */
@Service
@Transactional(readOnly = true)
public class VClassServiceImpl extends BaseMybatisDao<VClassMapper> implements VClassService {

    @Autowired
    private VClassMapper vClassMapper;

    @Override
    public Pagination<VClass> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public VClass SelectVClassByccidService(Long ccid) {
        return vClassMapper.SelectVClassByccid(ccid);
    }

    @Override
    public VClass findVClassByCcid(Long ccid) {
        return vClassMapper.findVClassByCcid(ccid);
    }



}
