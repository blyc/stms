package com.hp.major.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.UMajorMapper;
import com.hp.common.model.UMajor;
import com.hp.common.utils.LoggerUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.major.service.MajorService;
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
public class MajorServiceImpl extends BaseMybatisDao<UMajorMapper> implements MajorService{
    @Autowired
    private UMajorMapper uMajorMapper;
    @Override
    public List<KeyAndValue> findAllMajorForSelect() {
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        List<UMajor> uMajors =uMajorMapper.findAllMajor();
        if(uMajors==null || uMajors.size()<=0){
            return list;
        }

        for(UMajor u:uMajors){
            KeyAndValue keyAndValue = new KeyAndValue(u.getMid(),u.getName(),u);
            list.add(keyAndValue);
        }
        return list;
    }

     @Override
    public Pagination<UMajor> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage("findAllWithNum","findCount",resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public UMajor insert(UMajor record) {
        uMajorMapper.insert(record);
        return record;
    }

    @Override
    public UMajor findByName(String name) {
        return uMajorMapper.findByName(name);
    }

    @Override
    public UMajor selectByPrimaryKey(Long id) {
        return uMajorMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(UMajor record) {
        return uMajorMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long id) {
        return uMajorMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> deleteMajorById(String ids) {
        Map<String,Object> resultMap = new HashMap<String,Object>();
        try {
            int count=0;
            String[] idArray = new String[]{};
            if(StringUtils.contains(ids, ",")){
                idArray = ids.split(",");
            }else{
                idArray = new String[]{ids};
            }

            for (String id : idArray) {
                count+=this.deleteByPrimaryKey(new Long(id));
            }
            resultMap.put("status", 200);
            resultMap.put("count", count);
        } catch (Exception e) {
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除专业出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

}
