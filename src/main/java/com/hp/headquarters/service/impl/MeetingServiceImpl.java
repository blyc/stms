package com.hp.headquarters.service.impl;

import com.hp.common.dao.UCprizesMapper;
import com.hp.common.dao.UCscreenMapper;
import com.hp.common.dao.UCtypeMapper;
import com.hp.common.dao.UCuserMapper;
import com.hp.common.model.UCprizes;
import com.hp.common.model.UCscreen;
import com.hp.common.model.UCtype;
import com.hp.common.model.UCuser;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.StringUtils;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.headquarters.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: boy
 * @Date: 2018/12/16
 * @Description:
 */
@Service
@Transactional(readOnly = true)
public class MeetingServiceImpl extends BaseMybatisDao<UCuserMapper> implements MeetingService {

    @Autowired
    private UCuserMapper uCuserMapper;
    @Autowired
    private UCtypeMapper uCtypeMapper;
    @Autowired
    private UCscreenMapper uCscreenMapper;

    @Autowired
    private UCprizesMapper uCprizesMapper;

    @Override
    public List<UCuser> selectCjUser() {
        return uCuserMapper.selectWCJ();
    }

    @Override
    public List<UCuser> selectHjUser(Long flg) {
        return uCuserMapper.selectHJ(flg);
    }

    @Override
    @Transactional(readOnly = false)
    public List<UCprizes> selectPrizes() {
        List<UCprizes>  uCprizesList = uCprizesMapper.selectPrizes();
        for(UCprizes u:uCprizesList){
            u.setFlg(1L);
            uCprizesMapper.updateByPrimaryKey(u);
        }
        return uCprizesList;
    }

    @Override
    @Transactional(readOnly = false)
    public int updatePrize(UCprizes record) {
        UCprizes uCprizes = uCprizesMapper.selectByPrimaryKey(record.getId());
        uCprizes.setFlg(record.getFlg());
        return uCprizesMapper.updateByPrimaryKey(uCprizes);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(UCuser record) {
        return uCuserMapper.insert(record);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(String ids,Long flg) {


        if(StringUtils.isNotBlank(ids)){
            String[] idArray = null;
            //�����е���ϰ�ߣ�ֱ��ids.split(",") �����ԣ���ϰ����ôд��������ˡ�
            if(StringUtils.contains(ids, ",")){
                idArray = ids.split(",");
            }else{
                idArray = new String[]{ids};
            }
            //����µġ�
            for (String rid : idArray) {
                //�����Ͻ�������жϣ�Ҳ���Բ��жϡ����{@link StringUtils ������д�˵�}
                if(StringUtils.isNotBlank(rid)){
                    UCuser uCuser = uCuserMapper.selectByPrimaryKey(new Long(rid));
                    uCuser.setFlg(-1L);
                    uCuserMapper.updateByPrimaryKey(uCuser);

                }
            }
            UCtype uCtype = new UCtype();
            uCtype.setId(flg);
            uCtype.setTcount((long) idArray.length);
            return uCtypeMapper.updateTcountByPrimaryKey(uCtype);
        }
        return -1;
    }

    @Override
    public UCtype selectTypeByName(UCtype record) {
        return uCtypeMapper.selectByTname(record);
    }

    @Override
    @Transactional(readOnly = false)
    public UCscreen insertScreen(UCscreen uCscreen) {
        uCscreen.setShowWeight(System.currentTimeMillis());
        uCscreen.setCreateTime(DateUtil.getCurrentDateTime());
        uCscreenMapper.insertSelective(uCscreen);
        return uCscreen;
    }

    @Override
    public List<UCscreen> selectUcreen(Long count) {
        Map<String, Object> params = new HashMap<String, Object>();
        String page_sql = String.format(" limit  %s , %s ", 0, count);
        params.put("page_sql", page_sql);
        return uCscreenMapper.selectByLimit(params);
    }
}
