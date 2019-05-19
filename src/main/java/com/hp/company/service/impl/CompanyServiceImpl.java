package com.hp.company.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.UCompanyMapper;
import com.hp.common.model.UCompany;
import com.hp.common.utils.LoggerUtils;
import com.hp.company.service.CompanyService;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly = true)
public class CompanyServiceImpl extends BaseMybatisDao<UCompanyMapper> implements CompanyService {
    @Autowired
    private UCompanyMapper uCompanyMapper;
    @Override
    public List<KeyAndValue> findAllCompanyForSelect() {
        List<UCompany> uCompanies =uCompanyMapper.findAllCompany();
        if(uCompanies==null || uCompanies.size()<=0){
            return null;
        }

        List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        for(UCompany u:uCompanies){
            KeyAndValue keyAndValue = new KeyAndValue(u.getCid(),u.getName());
            list.add(keyAndValue);
        }
        return list;
    }
    @Override
    public Pagination<UCompany> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    @Transactional(readOnly = false)
    public UCompany insert(UCompany record) {
        uCompanyMapper.insert(record);
        return record;
    }

    @Override
    public UCompany findByName(String name) {
        return uCompanyMapper.findByName(name);
    }

    @Override
    public Map<String,Map<String,Long>> findByStudentCount() {
        return uCompanyMapper.findByStudentCount();
    }


    @Override
    public UCompany selectByPrimaryKey(Long id) {
        return uCompanyMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int updateByPrimaryKeySelective(UCompany record) {
        return uCompanyMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional(readOnly = false)
    public int deleteByPrimaryKey(Long id) {
        return uCompanyMapper.deleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public Map<String, Object> deleteCompanyById(String ids) {
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
            LoggerUtils.fmtError(getClass(), e, "根据IDS删除校区出现错误，ids[%s]", ids);
            resultMap.put("status", 500);
            resultMap.put("message", "删除出现错误，请刷新后再试！");
        }
        return resultMap;
    }

}
