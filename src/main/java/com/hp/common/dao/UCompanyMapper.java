package com.hp.common.dao;

import com.hp.common.model.UCompany;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

public interface UCompanyMapper {
    public List<UCompany> findAllCompany();

    public UCompany findByName(String name);

    /**
     * ��ѯ��У��ѧ������
     * @return Map<String,Map<String,String>> У������,У��ѧ������
     */
    @MapKey("name")
    public Map<String,Map<String,Long>> findByStudentCount();

    public UCompany selectByPrimaryKey(Long id);

    public int insert(UCompany record);

    public int updateByPrimaryKeySelective(UCompany record);

    public int deleteByPrimaryKey(Long id);

}
