package com.hp.stock.service.impl;

import com.hp.common.bo.KeyAndValue;
import com.hp.common.dao.UMajorMapper;
import com.hp.common.dao.UStorageMapper;
import com.hp.common.dao.UdetailsMapper;
import com.hp.common.model.UMajor;
import com.hp.common.model.UStorage;
import com.hp.common.model.Udetails;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.stock.service.UdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018-04-15.
 */
@Service
public class UdetailsServiceimpl extends BaseMybatisDao<UdetailsMapper> implements UdetailsService {
    @Autowired
    private UdetailsMapper udetailsMapper;
    @Autowired
    private UStorageMapper uStorageMapper;
    @Autowired
    private UMajorMapper uMajorMapper;
    @Override
    public Pagination<Udetails> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public List<KeyAndValue> selectUdetails() {
        List<KeyAndValue> list = new LinkedList<KeyAndValue>();

        List<UMajor> uMajors = uMajorMapper.findAllMajor();
        for (UMajor m:uMajors){
            KeyAndValue keyAndValue = new KeyAndValue(m.getMid(), m.getName());
            list.add(keyAndValue);

        }

        return list;
    }

    @Override
    public int insertUStorage(UStorage uStorage,Udetails udetails) {
           List<Udetails> udetailsList=  udetailsMapper.selectUdetails(udetails);
            if(udetailsList==null || udetailsList.size()<=0){
                udetails.setTotal(uStorage.getSnumber());
                udetailsMapper.insertUdetails(udetails);
                System.out.println(udetails.getDid()+"123456789");
                uStorage.setDid(udetails.getDid());
                uStorageMapper.insertUStorage(uStorage);
            }else {
                for (Udetails u:udetailsList){
                    udetails.setDid(u.getDid());
                    uStorage.setDid(u.getDid());
                }
         uStorageMapper.insertUStorage(uStorage);
        udetails.setMid(null);
        udetails.setTotal(uStorage.getSnumber());
        udetailsMapper.updateUdetails(udetails);
    }

            return 0;
    }
}
