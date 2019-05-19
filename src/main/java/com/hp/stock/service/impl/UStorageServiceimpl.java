package com.hp.stock.service.impl;

import com.hp.common.dao.UStorageMapper;
import com.hp.common.model.UStorage;
import com.hp.common.model.UStorageALL;
import com.hp.common.utils.PoiExcelExport;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.stock.service.UStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018-04-15.
 */
@Service
public class UStorageServiceimpl extends BaseMybatisDao<UStorageMapper> implements UStorageService {
   @Autowired
   private UStorageMapper uStorageMapper;
    @Override
    public Pagination<UStorage> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public void findAllInfo(HttpServletResponse response) {
        //将需要从数据库导入到表格的信息读出来
        List<UStorageALL> uStorageALLList = uStorageMapper.findAllUSTorage();
        PoiExcelExport poiExcelExport = new PoiExcelExport("sheet1");
        // PoiExcelExport poiExcelExport = new PoiExcelExport("E:/test.xlsx", "sheet1");
        String titleColumn[] = {"sid", "sdate", "name", "snumber", "sname", "edition"};
        String titleName[] = {"编号", "入库时间", "教材名称", "入库数量", "负责人", "版本"};

        int titleSize[] = {13, 13, 13, 13, 13, 13};
        String fileName = "Storage";
        poiExcelExport.wirteExcel(fileName, response, titleColumn, titleName, titleSize, uStorageALLList);
        return;
    }

}
