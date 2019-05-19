package com.hp.employee.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.UJsRankMapper;
import com.hp.common.model.UJsRank;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.employee.service.EmployeeRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/11/28
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class EmployeeRankServiceImpl extends BaseMybatisDao<UJsRankMapper> implements EmployeeRankService {

    @Autowired
    private UJsRankMapper uJsRankMapper;

    @Override
    public Pagination<UJsRank> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public UJsRank addUJsRank(UJsRank u) {
        uJsRankMapper.insertSelective(u);
        return u;
    }

    @Override
    public List<KeyAndValue> findAllUJsRankForSelect() {
        List<UJsRank> uJsRankList = uJsRankMapper.selectAllForSelect();
        if (uJsRankList == null || uJsRankList.size() <= 0) {
            return null;
        }
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        for (UJsRank u : uJsRankList) {
            KeyAndValue keyAndValue = new KeyAndValue(u.getLid(), u.getType() + "-" + u.getLevel());
            list.add(keyAndValue);
        }
        return list;
    }

    @Override
    @Transactional(readOnly = false)
    public int update(UJsRank record) {
        UJsRank uJsRank1 = this.findByPk(record.getLid());
        if (uJsRank1 == null) {
            return -1;
        }
        uJsRank1.setType(record.getType());
        uJsRank1.setLevel(record.getLevel());
        uJsRank1.setSubsidy(record.getSubsidy());
        uJsRankMapper.updateByPrimaryKeySelective(uJsRank1);
        return 0;
    }

    @Override
    public UJsRank findByPk(Long id) {
        UJsRank uJsRank = uJsRankMapper.selectByPrimaryKey(id);
        return uJsRank;
    }
}
