package com.hp.teachingmaterial.service.impl;

import com.hp.common.dao.UApplicationDetailMapper;
import com.hp.common.model.UApplicationDetail;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.teachingmaterial.service.UApplicationDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/08/09
 * @Description:
 */
@Service
public class UApplicationDetailServiceImpl  extends BaseMybatisDao<UApplicationDetailMapper> implements UApplicationDetailService {

    @Autowired
    private UApplicationDetailMapper uApplicationDetailMapper;
    @Override
    public Pagination<UApplicationDetail> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public UApplicationDetail saveUapplicationDetail(UApplicationDetail u) {
        uApplicationDetailMapper.insertSelective(u);
        return u;
    }


}
