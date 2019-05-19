package com.hp.course.service.impl;

import com.hp.common.dao.UScheduleMapper;
import com.hp.common.dao.VClassMapper;
import com.hp.common.model.USchedule;
import com.hp.common.model.VClass;
import com.hp.common.utils.SubscriptUtil;
import com.hp.course.service.ScheduleService;
import com.hp.course.util.PoiExcelExport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by DELL on 2018/3/26.
 */
@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private UScheduleMapper uScheduleMapper;
    @Autowired
    private VClassMapper vClassMapper;

    @Override
    public boolean insert(USchedule record) {
        record = SubscriptUtil.Generatingsubscript(record);
        List<USchedule> list = uScheduleMapper.selectUScheduleWhetherInsert(record);
        if(list==null||list.size()<=0){
            uScheduleMapper.insert(record);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<USchedule> selectUScheduleAllByccidService(Long ccid) {
        return uScheduleMapper.selectUScheduleAllByccid(ccid);
    }

    @Override
    public void findAllInfo(HttpServletResponse response,Long ccid) {
        List<USchedule> list = uScheduleMapper.selectUScheduleAllByccid(ccid);
        VClass vClass = vClassMapper.findVClassByCcid(ccid);
        PoiExcelExport poiExcelExport = new PoiExcelExport("E:/test.xlsx", "sheet1");
        int titleSize[] = {10, 10, 10, 10, 10,10,10,10,10,10};
        String fileName = "exportSchedule";
        String ucalssname = vClass.getClassname();
        poiExcelExport.wirteExcel(fileName, response,ucalssname,list);
    }
}
