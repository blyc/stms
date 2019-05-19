package com.hp.task.service.impl;

import com.hp.common.dao.UStudentMapper;
import com.hp.common.dao.UTaskAccomplishMapper;
import com.hp.common.dao.UTaskMapper;
import com.hp.common.model.UStudent;
import com.hp.common.model.UTask;
import com.hp.common.model.UTaskAccomplish;
import com.hp.common.model.UUser;
import com.hp.common.utils.DateUtil;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dragon on 2018/4/11 0011.
 */
@Service
@Transactional(readOnly = true)
public class TaskServiceImpl extends BaseMybatisDao<UTaskMapper> implements TaskService {
    @Autowired
    private UTaskMapper uTaskMapper;
    @Autowired
    private UTaskAccomplishMapper uTaskAccomplishMapper;
    @Autowired
    private UStudentMapper uStudentMapper;


    @Override
    public Pagination<UTask> findBypage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public int insertUtask(UTask uTask) {
        UUser uUser = TokenManager.getToken();

        uTask.setCid(uUser.getCid());
        uTask.setUid(uUser.getUid());
        uTask.setMark((long) 0);
        uTask.setArrangementtime(DateUtil.getCurrentDateTime());
        uTaskMapper.insertUtask(uTask);

        List<UTaskAccomplish> uTaskAccomplishList = new ArrayList<UTaskAccomplish>();

        List<UStudent> uStudentList = uStudentMapper.selectByCid(uTask.getCcid());
        for (UStudent uStudent : uStudentList) {
            UTaskAccomplish uTaskAccomplish = new UTaskAccomplish();
            uTaskAccomplish.setCid(uTask.getCid());
            uTaskAccomplish.setCcid(uTask.getCcid());
            uTaskAccomplish.setSid(uStudent.getSid());
            uTaskAccomplish.setTid(uTask.getTid());
            uTaskAccomplish.setArrangementtime(uTask.getArrangementtime());
            uTaskAccomplish.setTaskgrade((long) 0);
            uTaskAccomplishList.add(uTaskAccomplish);
        }
        uTaskAccomplishMapper.insertbatch(uTaskAccomplishList);

        return 0;
    }
}
