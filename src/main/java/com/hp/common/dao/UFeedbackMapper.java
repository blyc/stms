package com.hp.common.dao;

import com.hp.common.model.UFeedback;

import java.util.List;

public interface UFeedbackMapper {
    /*添加反馈表*/
    int insertSelective(UFeedback uFeedback);

   List<UFeedback> selectUFeedbackANdaid(UFeedback uFeedback);
}
