package com.hp.stock.service.impl;

import com.hp.common.dao.UTheibraryMapper;
import com.hp.common.model.UTheibrary;
import com.hp.common.model.UTheibraryALL;
import com.hp.common.utils.PoiExcelExport;
import com.hp.core.mybatis.BaseMybatisDao;
import com.hp.core.mybatis.page.Pagination;
import com.hp.stock.service.UtheibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created by slm on 2018-04-15.
 */
@Service
public class UtheibraryServiceimpl extends BaseMybatisDao<UTheibraryMapper> implements UtheibraryService {
    @Autowired
    private UTheibraryMapper uTheibraryMapper;
    @Override
    public Pagination<UTheibrary> findByPage(Map<String, Object> resultMap, Integer pageNo, Integer pageSize) {
        return super.findPage(resultMap, pageNo, pageSize);
    }

    @Override
    public void findAllInfo(HttpServletResponse response) {
        //将需要从数据库导入到表格的信息读出来
        List<UTheibraryALL> uTheibraryALLS = uTheibraryMapper.findAllUTheibrary();

        PoiExcelExport poiExcelExport = new PoiExcelExport("sheet1");
        // PoiExcelExport poiExcelExport = new PoiExcelExport("E:/test.xlsx", "sheet1");
        String titleColumn[] = {"tid", "tnumber", "tdate", "name", "edition"};
        String titleName[] = {"编号", "出库数量", "出库时间", "教材名称", "版本"};

        int titleSize[] = {13, 13, 13, 13, 13};
        String fileName = "UTheibrary";
        poiExcelExport.wirteExcel(fileName, response, titleColumn, titleName, titleSize, uTheibraryALLS);
        return;
    }
}
