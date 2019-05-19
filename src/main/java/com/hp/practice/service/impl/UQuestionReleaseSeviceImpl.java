package com.hp.practice.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.UPositionMapper;
import com.hp.common.dao.UQuestionReleaseMapper;
import com.hp.common.model.UPosition;
import com.hp.common.model.UQuestionRelease;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.position.service.PositionService;
import com.hp.practice.service.UQuestionReleaseService;
import org.apache.commons.lang.StringUtils;
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
public class UQuestionReleaseSeviceImpl extends BaseMybatisDao<UQuestionRelease> implements UQuestionReleaseService {
    @Autowired
    private UQuestionReleaseMapper uQuestionReleaseMapper;


    @Override
    public List<KeyAndValue> findAllReleaseForSelect() {
        return null;
    }

    @Override
    public UPosition insert(UPosition record) {
        return null;
    }

    @Override
    public UPosition findByName(String name) {
        return null;
    }

    @Override
    public UPosition selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(UPosition record) {
        return 0;
    }

    @Override
    public Map<String, Object> deleteUPositionById(String ids) {
        return null;
    }
}
