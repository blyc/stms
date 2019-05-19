package com.hp.practice.service;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.model.UPosition;
import com.hp.common.model.UQuestionRelease;
import com.hp.common.model.VQuestionRelease;
import com.hp.core.mybatis.page.Pagination;

import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/02/11
 * @Description:
 */
public interface VQuestionReleaseService {
    public List<KeyAndValue> findAllReleaseForSelect();
    public Pagination<VQuestionRelease> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize);
}
