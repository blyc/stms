package com.hp.department.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.UDepartmentMapper;
import com.hp.common.model.UDepartment;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.department.service.DepartmentSevice;
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
public class DepartmentServiceImpl extends BaseMybatisDao<UDepartmentMapper> implements DepartmentSevice {
    @Autowired
    private UDepartmentMapper uDepartmentMapper;

    @Override
    public List<KeyAndValue> findAllDepartmentForSelect() {
        List<UDepartment> uDepartments = uDepartmentMapper.findAllDepartMent();
        if (uDepartments == null || uDepartments.size() <= 0) {
            return null;
        }
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();
        for (UDepartment u : uDepartments) {
            KeyAndValue keyAndValue = new KeyAndValue(u.getDid(), u.getName());
            list.add(keyAndValue);
        }
        return list;
    }

    @Override
    public Pagination<UDepartment> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public UDepartment selectByPrimaryKey(Long id) {
        return uDepartmentMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public UDepartment insert(UDepartment record) {
        uDepartmentMapper.insert(record);
        return record;
    }

    @Override
    public UDepartment findByName(String name) {
        return uDepartmentMapper.findByName(name);
    }

    @Override
    public Map<String, Map<String, Long>> findByEmployeeCount() {
        return uDepartmentMapper.findByEmployeeCount();
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(UDepartment record) {
        return uDepartmentMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long id) {
        return uDepartmentMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> deleteDepartMentById(String ids) {
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
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除部门出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }
}
