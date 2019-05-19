package com.hp.common.dao;

import com.hp.common.model.VQuestionRelease;

import java.util.List;
import java.util.Map;

public interface VQuestionReleaseMapper {
    public List<VQuestionRelease> findAllQuestionRelease(Map map);
}