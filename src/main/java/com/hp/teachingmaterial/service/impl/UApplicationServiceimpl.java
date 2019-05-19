package com.hp.teachingmaterial.service.impl;

import com.hp.common.dao.UApplicationMapper;
import com.hp.common.dao.UFeedbackMapper;
import com.hp.common.dao.UTheibraryMapper;
import com.hp.common.dao.UdetailsMapper;
import com.hp.common.model.*;
import com.hp.common.utils.DateUtil;
import com.hp.common.utils.PoiExcelExport;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.core.shiro.token.manager.TokenManager;
import com.hp.teachingmaterial.service.UApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018/3/23.
 */
@Service
public class UApplicationServiceimpl extends BaseMybatisDao<UApplicationMapper> implements UApplicationService {
    @Autowired
    private UApplicationMapper uApplicationMapper;
    @Autowired
    private UFeedbackMapper uFeedbackMapper;
    @Autowired
    private UTheibraryMapper uTheibraryMapper;
    @Autowired
    private UdetailsMapper udetailsMapper;


    @Override
    public Pagination<UApplication> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public UApplication findById(Long id) {
        return uApplicationMapper.selectByPrimaryKey(id);
    }


    @Override
    public int insertSelective(UFeedback uFeedback) {
        uFeedbackMapper.insertSelective(uFeedback);
        UTheibrary uTheibrary = new UTheibrary();
        uTheibrary.setTnumber(uFeedback.getFnumber());
        uTheibrary.setTdate(uFeedback.getGrantdate());
        uTheibrary.setEdition(uFeedback.getEdition());
        UMajor uMajor = new UMajor();
        uMajor.setName(uFeedback.getFname());
        Udetails udetails2 = new Udetails();

        /*udetails.setDname(uFeedback.getFname());*/
        List<Udetails> udetails = udetailsMapper.selectUdetailsDname(uMajor);
        for (Udetails u : udetails) {
            uTheibrary.setDid(u.getDid());
            udetails2.setDid(u.getDid());
        }


        udetails2.setTotal(uFeedback.getFnumber());

        uTheibraryMapper.insertUTheibrary(uTheibrary);
        udetailsMapper.updateUdetailsCk(udetails2);
        return 0;
    }

    @Override
    public List<UApplication> selectUApplicationBycampus(UApplication uApplication) {
        return uApplicationMapper.selectUApplicationBycampus(uApplication);
    }

    @Override
    public int saveUApplication(UApplication uApplication) {
        UUser uUser = TokenManager.getToken();
        uApplication.setAdate(DateUtil.getCurrentDateTime());
        uApplication.setCid(uUser.getCid());
        uApplication.setApplicant(uUser.getNickname());
        uApplication.setPrice(0L);
        uApplication.setNumber(0L);
        uApplication.setState(0L);
        uApplicationMapper.saveUApplication(uApplication);
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(UApplication uApplication) {
        uApplicationMapper.updateByPrimaryKeySelective(uApplication);
        return 0;
    }

    @Override
    public void findAllInfo(HttpServletResponse response) {
        //将需要从数据库导入到表格的信息读出来
        List<UApplication> uApplications = uApplicationMapper.selectALL();
        PoiExcelExport poiExcelExport = new PoiExcelExport("sheet1");
        // PoiExcelExport poiExcelExport = new PoiExcelExport("E:/test.xlsx", "sheet1");
        String titleColumn[] = {"aid", "adate", "Applicant", "campus", "Zipcode", "address", "name", "Addressee1", "Telephone1", "Tel1", "Addressee2", "Telephone2", "Tel2", "Number", "Remarks"};
        String titleName[] = {"序号", "申请时间", "申请人", "订购校区", "邮编", "校区地址", "专业名称", "申请人1", "固定电话1", "手机2", "申请人2", "固定电话2", "手机2", "申请数量", "备注"};

        int titleSize[] = {13, 13, 13, 13, 13, 35, 13, 13, 13, 13, 13, 13, 13, 13, 13};
        String fileName = "exportStudentinfo";
        poiExcelExport.wirteExcel(fileName, response, titleColumn, titleName, titleSize, uApplications);
        return;
    }

    @Override
    public List<Udetails> selectUdetailsprice(Udetails udetails) {
        return udetailsMapper.selectUdetailsdid(udetails);
    }

    @Override
    public UApplication updateState(String action, Long id) {

        UApplication uApplication = uApplicationMapper.selectByPrimaryKey(id);
        Long state = uApplication.getState();
        if (state == 0) {
            //确定订单
            uApplication.setState(1L);
        } else if (state == 1) {
            //发货
            uApplication.setState(2L);
        } else if (state == 2) {
            //收货
            uApplication.setState(3L);
        }
        uApplicationMapper.updateByPrimaryKeySelective(uApplication);
        return uApplication;
    }

}
