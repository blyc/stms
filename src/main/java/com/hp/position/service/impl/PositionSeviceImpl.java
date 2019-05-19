package com.hp.position.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.UPositionMapper;
import com.hp.common.model.UPosition;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.position.service.PositionService;
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
public class PositionSeviceImpl extends BaseMybatisDao<UPositionMapper> implements PositionService {
    @Autowired
    private UPositionMapper uPositionMapper;

    @Override
    public List<KeyAndValue> findAllPositionForSelect() {
        List<UPosition> uPositions = uPositionMapper.findAllPosition(new HashMap());
        if (uPositions == null || uPositions.size() <= 0) {
            return null;
        }
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        for (UPosition u : uPositions) {
            KeyAndValue keyAndValue = new KeyAndValue(u.getPid(), u.getName());
            list.add(keyAndValue);
        }
        return list;
    }

    @Override
    public Pagination<UPosition> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findAllWithNum","findCount",resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public UPosition insert(UPosition record) {
        uPositionMapper.insert(record);
        return record;
    }

    @Override
    public UPosition findByName(String name) {
        Map map = new HashMap<String, Object>();
        map.put("name", name);
        List<UPosition> uPositionList = uPositionMapper.findAllPosition(map);
        if (uPositionList == null || uPositionList.size() == 0) {
            return null;
        }
        return uPositionList.get(0);
    }

    @Override
    public UPosition selectByPrimaryKey(Long id) {
        Map map = new HashMap<String, Object>();
        map.put("pid", id);
        List<UPosition> uPositionList = uPositionMapper.findAllPosition(map);
        if (uPositionList == null || uPositionList.size() == 0) {
            return null;
        }
        return uPositionList.get(0);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(UPosition record) {
        return uPositionMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long id) {
        return uPositionMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> deleteUPositionById(String ids) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            int count = 0;
            String[] idArray = new String[]{};
            if (StringUtils.contains(ids, ",")) {
                idArray = ids.split(",");
            } else {
                idArray = new String[]{ids};
            }

            for (String id : idArray) {
                count += this.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除职位出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }
}
