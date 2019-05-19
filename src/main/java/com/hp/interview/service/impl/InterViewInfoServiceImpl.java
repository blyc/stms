package com.hp.interview.service.impl;

import com.hp.common.dao.UInterViewInfoMapper;
import com.hp.common.dao.UInterViewMapper;
import com.hp.common.model.UInterView;
import com.hp.common.model.UInterViewInfo;
import com.hp.common.utils.DateUtil;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.interview.service.InterViewInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/4/13.
 */
@Service
@Transactional(readOnly = true)
public class InterViewInfoServiceImpl extends BaseMybatisDao<UInterViewInfoMapper> implements InterViewInfoService {
    @Autowired
    private UInterViewInfoMapper uInterViewInfoMapper;

    @Autowired
    private UInterViewMapper uInterViewMapper;

    @Override
    public Pagination<UInterViewInfo> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public List<UInterViewInfo> selectByCid(Long cid) {
        return uInterViewInfoMapper.selectByCid(cid);
    }

    @Override
    public Double selectComrateByCid(Long cid) {
        return uInterViewInfoMapper.selectComrateByCid(cid);
    }

    @Override
    @Transactional(readOnly = false)
    public UInterViewInfo insertInterViewInfo(UInterViewInfo record) {
        if (record == null) {
            return null;
        }
        record.setState("1");
        record.setComdate(DateUtil.getCurrentDateTime());

        UInterView uInterView = uInterViewMapper.selectByPrimaryKey(record.getIid());
        if (uInterView == null) {
            return null;
        }

        Date begin = uInterView.getBegintime();
        Date end = uInterView.getEndtime();

        Date comdate = DateUtil.getCurrentDateTime();

        if (begin.compareTo(comdate) > 0 || end.compareTo(comdate) < 0) {
            return null;
        }
        uInterViewInfoMapper.updateUInterViewInfo(record);

        return record;
    }
}
