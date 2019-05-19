package com.hp.interview.service.impl;

import com.hp.common.dao.UInterViewInfoMapper;
import com.hp.common.dao.UInterViewMapper;
import com.hp.common.dao.UStudentMapper;
import com.hp.common.model.UInterView;
import com.hp.common.model.UInterViewInfo;
import com.hp.common.model.UStudent;
import com.hp.common.model.UUser;
import com.hp.common.utils.DateUtil;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.interview.service.InterViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2018/4/13.
 */
@Service
@Transactional(readOnly = true)
public class InterViewServiceImpl extends BaseMybatisDao<UInterViewMapper> implements InterViewService {
    @Autowired
    private UInterViewMapper uInterViewMapper;

    @Autowired
    private UInterViewInfoMapper uInterViewInfoMapper;

    @Autowired
    private UStudentMapper uStudentMapper;

    @Override
    public Pagination<UInterView> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public UInterView insertInterView(UInterView record) {
        UUser uUser = TokenManager.getToken();
        record.setCid(uUser.getCid());
        record.setCreatename(uUser.getNickname());
        record.setCreatetime(DateUtil.getCurrentDateTime());

        uInterViewMapper.insert(record);

        List<UStudent> uStudentList = uStudentMapper.selectByCid(record.getCcid());
        List<UInterViewInfo> uInterViewInfos = new ArrayList<UInterViewInfo>();
        if (uStudentList != null && uStudentList.size() > 0) {
            for (UStudent us : uStudentList) {
                UInterViewInfo uInterViewInfo = new UInterViewInfo();
                uInterViewInfo.setSid(us.getSid());
                uInterViewInfo.setIid(record.getIid());
                uInterViewInfos.add(uInterViewInfo);
            }
            uInterViewInfoMapper.insertBatch(uInterViewInfos);
        }
        return record;
    }
}
