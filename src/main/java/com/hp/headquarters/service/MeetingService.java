package com.hp.headquarters.service;

import com.hp.common.model.UCprizes;
import com.hp.common.model.UCscreen;
import com.hp.common.model.UCtype;
import com.hp.common.model.UCuser;

import java.util.List;

/**
 * @Author: boy
 * @Date: 2018/12/16
 * @Description:
 */
public interface MeetingService {

    public List<UCuser> selectCjUser();

    public List<UCuser> selectHjUser(Long flg);

    public int insert(UCuser record);

    public int update(String ids,Long flg);

    public UCtype selectTypeByName(UCtype record);

    public UCscreen insertScreen(UCscreen uCscreen);

    public List<UCscreen> selectUcreen(Long count);

    public List<UCprizes> selectPrizes();

    public int updatePrize(UCprizes record);


}
