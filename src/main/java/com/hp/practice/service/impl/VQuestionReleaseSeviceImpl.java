package com.hp.practice.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.VQuestionReleaseMapper;
import com.hp.common.model.UPosition;
import com.hp.common.model.UQuestionRelease;
import com.hp.common.model.VQuestionRelease;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.practice.service.VQuestionReleaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/02/11
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class VQuestionReleaseSeviceImpl extends BaseMybatisDao<VQuestionReleaseMapper> implements VQuestionReleaseService {
    @Autowired
    private VQuestionReleaseMapper vQuestionReleaseMapper;

    @Override
    public List<KeyAndValue> findAllReleaseForSelect() {
        List<VQuestionRelease> vQuestionReleases = vQuestionReleaseMapper.findAllQuestionRelease(new HashMap());
        if (vQuestionReleases == null || vQuestionReleases.size() <= 0) {
            return null;
        }
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        for (VQuestionRelease v : vQuestionReleases) {
            KeyAndValue keyAndValue = new KeyAndValue(v.getQbid(), v.getName());
            list.add(keyAndValue);
        }
        return list;
    }

    @Override
    public Pagination<VQuestionRelease> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }
}
