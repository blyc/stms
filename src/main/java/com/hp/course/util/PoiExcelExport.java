package com.hp.course.util;


import com.hp.common.model.USchedule;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 */
public class PoiExcelExport {
   // HttpServletResponse response;
    // 文件名
    private String fileName ;
    //文件保存路径
    private String fileDir;
    //sheet名
    private String sheetName;
    //表头字体
    private String titleFontType = "Arial Unicode MS";
    //表头背景色
    private String titleBackColor = "C1FBEE";
    //表头字号
    private short titleFontSize = 12;
    //添加自动筛选的列 如 A:M
    private String address = "";
    //正文字体
    private String contentFontType = "Arial Unicode MS";
    //正文字号
    private short contentFontSize = 12;
    //Float类型数据小数位
    private String floatDecimal = ".00";
    //Double类型数据小数位
    private String doubleDecimal = ".00";
    //设置列的公式
    private String colFormula[] = null;

    private ServletOutputStream servletOutputStream;

    DecimalFormat floatDecimalFormat=new DecimalFormat(floatDecimal);
    DecimalFormat doubleDecimalFormat=new DecimalFormat(doubleDecimal);



    private XSSFWorkbook workbook = null;

    public PoiExcelExport() {
    }

    public PoiExcelExport(ServletOutputStream servletOutputStream, String sheetName){
        this.sheetName = sheetName;
        this.servletOutputStream=servletOutputStream;
    }

    public PoiExcelExport(String fileDir, String sheetName){
        this.fileDir = fileDir;
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
    }

    public PoiExcelExport(HttpServletResponse response, String fileName, String sheetName){

        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
    }
    /**
     * 设置表头字体.
     * @param titleFontType
     */
    public void setTitleFontType(String titleFontType) {
        this.titleFontType = titleFontType;
    }
    /**
     * 设置表头背景色.
     * @param titleBackColor 十六进制
     */
    public void setTitleBackColor(String titleBackColor) {
        this.titleBackColor = titleBackColor;
    }
    /**
     * 设置表头字体大小.
     * @param titleFontSize
     */
    public void setTitleFontSize(short titleFontSize) {
        this.titleFontSize = titleFontSize;
    }
    /**
     * 设置表头自动筛选栏位,如A:AC.
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * 设置正文字体.
     * @param contentFontType
     */
    public void setContentFontType(String contentFontType) {
        this.contentFontType = contentFontType;
    }
    /**
     * 设置正文字号.
     * @param contentFontSize
     */
    public void setContentFontSize(short contentFontSize) {
        this.contentFontSize = contentFontSize;
    }
    /**
     * 设置float类型数据小数位 默认.00
     * @param doubleDecimal 如 ".00"
     */
    public void setDoubleDecimal(String doubleDecimal) {
        this.doubleDecimal = doubleDecimal;
    }
    /**
     * 设置doubel类型数据小数位 默认.00
     * @param floatDecimalFormat 如 ".00
     */
    public void setFloatDecimalFormat(DecimalFormat floatDecimalFormat) {
        this.floatDecimalFormat = floatDecimalFormat;
    }
    /**
     * 设置列的公式
     * @param colFormula  存储i-1列的公式 涉及到的行号使用@替换 如A@+B@
     */
    public void setColFormula(String[] colFormula) {
        this.colFormula = colFormula;
    }
    /**
     * 写excel.
     * @param titleColumn  对应bean的属性名
     * @param titleName   excel要导出的表名
     * @param titleSize   列宽
     * @param dataList  数据
     */
    public void wirteExcel(String fileName,HttpServletResponse response, String uclassname,List<USchedule> dataList){
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        XSSFSheet sheet = workbook.createSheet(this.sheetName);

        //新建文件
        OutputStream out = null;
        String titleName[] = {"第一节", "第二节", "第三节", "第四节", "第五节","第六节","第七节","第八节","第九节","第十节"};
        String titleColumn[] = {"周一", "周二", "周三", "周四","周五","周六","周日"};
        int titleSize[] = {10, 10, 10, 10, 10,10,10,10,10,10};
        Integer weeksint [] = {2,3,4,5,6,7,8};
        Integer courseint [] = {1,2,3,4,5,6,7,8,9,10};

        try {
                //否则，直接写到输出流中
                out = response.getOutputStream();
                fileName = fileName+".xlsx";
                response.setContentType("application/x-msdownload");
                response.setHeader("Content-Disposition", "attachment; filename="
                        + URLEncoder.encode(fileName, "UTF-8"));
            XSSFRow ucalssNameRow = workbook.getSheet(sheetName).createRow(0);
            //写入excel的表头
            XSSFRow titleNameRow = workbook.getSheet(sheetName).createRow(1);
            //设置样式
            XSSFCellStyle titleStyle = workbook.createCellStyle();

            titleStyle = (XSSFCellStyle) setFontAndBorder(titleStyle, titleFontType, (short) titleFontSize);
            titleStyle = (XSSFCellStyle) setColor(titleStyle, titleBackColor, (short)10);
            titleStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
            titleStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            titleStyle.setBorderBottom(BorderStyle.THIN);
            titleStyle.setBorderTop(BorderStyle.THIN);
            titleStyle.setBorderLeft(BorderStyle.THIN);
            titleStyle.setBorderRight(BorderStyle.THIN);
            sheet.setColumnWidth(0, 5*256);
            CellRangeAddress cra = new CellRangeAddress(0,0,0,10);
            sheet.addMergedRegion(cra);
            XSSFCell xcell = ucalssNameRow.createCell(0);
            xcell.setCellStyle(titleStyle);
            xcell.setCellValue(uclassname);
            for(int i = 0;i <titleName.length;i++){
                sheet.setColumnWidth(i+1, titleSize[i]*256);    //设置宽度
                XSSFCell cell = titleNameRow.createCell(i+1);
                cell.setCellStyle(titleStyle);
                cell.setCellValue(titleName[i].toString());
            }
            sheet.createRow(2);sheet.createRow(3);sheet.createRow(4);sheet.createRow(5);sheet.createRow(6);sheet.createRow(7);sheet.createRow(8);
            for(int i=0;i<titleColumn.length;i++){
                XSSFRow Row = sheet.createRow(i+2);
                Row.setHeight((short) (300*3));
                XSSFCell cell = Row.createCell(0);
                cell.setCellStyle(titleStyle);
                cell.setCellValue(titleColumn[i].toString());
            }
            if(dataList!=null&&dataList.size()>=0){
                XSSFCellStyle dataStyle = workbook.createCellStyle();
                dataStyle.setWrapText(true);
                dataStyle.setAlignment(HorizontalAlignment.CENTER);
                dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                /*dataStyle.setBorderBottom(BorderStyle.THIN);
                dataStyle.setBorderTop(BorderStyle.THIN);
                dataStyle.setBorderLeft(BorderStyle.THIN);
                dataStyle.setBorderRight(BorderStyle.THIN);*/
                for(USchedule us:dataList){
                    for (int i=0;i<weeksint.length;i++){
                        if(us.getWeeks().equals(titleColumn[i])){
                            XSSFRow Row = sheet.getRow(weeksint[i]);
                            for(int j=0;j<courseint.length;j++){
                                if(us.getSection().equals(titleName[j])){
                                    XSSFCell cell = Row.createCell(courseint[j]);
                                    cell.setCellStyle(dataStyle);
                                    String str = (us.getCourse().toString()+"\n"+us.getTname().toString()+"\n"+us.getPlace().toString());
                                    cell.setCellValue(str);
                                }
                            }
                        }
                    }
                }
            }

            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 将16进制的颜色代码写入样式中来设置颜色
     * @param style  保证style统一
     * @param color 颜色：66FFDD
     * @param index 索引 8-64 使用时不可重复
     * @return
     */
    public XSSFCellStyle setColor(XSSFCellStyle style, String color, short index){
        if(color!=""&&color!=null){
            //转为RGB码
            int r = Integer.parseInt((color.substring(0,2)),16);   //转为16进制
            int g = Integer.parseInt((color.substring(2,4)),16);
            int b = Integer.parseInt((color.substring(4,6)),16);
            //自定义cell颜色

            XSSFCellStyle style1=workbook.createCellStyle();
            style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(r,g,b)));
            //style1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

           /* HSSFPalette palette = workbook.getCustomPalette();
            palette.setColorAtIndex((short)index, (byte) r, (byte) g, (byte) b);*/

            //style.setFillPattern(CellStyle.SOLID_FOREGROUND);
            style.setFillForegroundColor(index);
        }
        return style;
    }

    /**
     * 设置字体并加外边框
     * @param style  样式
     * @param style  字体名
     * @param style  大小
     * @return
     */
    public XSSFCellStyle setFontAndBorder(XSSFCellStyle style,String fontName,short size){
       XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(size);
        font.setFontName(fontName);
        font.setBold(true);
        style.setFont(font);
       /* style.setBorderBottom(XSSFCellStyle.BORDER_THIN); //下边框
        style.setBorderLeft(XSSFCellStyle.BORDER_THIN);//左边框
        style.setBorderTop(XSSFCellStyle.BORDER_THIN);//上边框
        style.setBorderRight(XSSFCellStyle.BORDER_THIN);//右边框*/
        return style;
    }
    /**
     * 删除文件
     * @return
     */
    public boolean deleteExcel(){
        boolean flag = false;
        File file = new File(this.fileDir);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }
    /**
     * 删除文件
     * @return
     */
    public boolean deleteExcel(String path){
        boolean flag = false;
        File file = new File(path);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                file.delete();
                flag = true;
            }
        }
        return flag;
    }


}

//通过反射获取数据并写入到excel中
           /* if(dataList!=null&&dataList.size()>0){
                //设置样式
                XSSFCellStyle dataStyle = workbook.createCellStyle();
                titleStyle = (XSSFCellStyle) setFontAndBorder(titleStyle, contentFontType, (short) contentFontSize);

                if(titleColumn.length>0){
                    for(int rowIndex = 1;rowIndex<=dataList.size();rowIndex++){
                        Object obj = dataList.get(rowIndex-1);     //获得该对象
                        Class clsss = obj.getClass();     //获得该对对象的class实例
                        XSSFRow dataRow = workbook.getSheet(sheetName).createRow(rowIndex);

                        for(int columnIndex = 0;columnIndex<titleColumn.length;columnIndex++){
                            String title = titleColumn[columnIndex].toString().trim();
                            if(!"".equals(title)){  //字段不为空
                                //使首字母大写
                                String UTitle = Character.toUpperCase(title.charAt(0))+ title.substring(1, title.length()); // 使其首字母大写;

                                String methodName  = "get"+UTitle;

                                // 设置要执行的方法
                                Method method = clsss.getDeclaredMethod(methodName);

                                //获取返回类型
                                String returnType = method.getReturnType().getName();

                                XSSFCell cell = dataRow.createCell(columnIndex);
                                Object data1 = method.invoke(obj)==null?"":method.invoke(obj);
                                String data = method.invoke(obj)==null?"":method.invoke(obj).toString();
                                if(data!=null&&!"".equals(data)){
                                    if("int".equals(returnType)){
                                        cell.setCellValue(Integer.parseInt(data));
                                    }else if("long".equals(returnType)){
                                        cell.setCellValue(Long.parseLong(data));
                                    }else if("float".equals(returnType)){
                                        cell.setCellValue(floatDecimalFormat.format(Float.parseFloat(data)));
                                    }else if("double".equals(returnType)){
                                        cell.setCellValue(doubleDecimalFormat.format(Double.parseDouble(data)));
                                    } else if ("java.util.Date".equals(returnType)){
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        cell.setCellValue(sdf.format(data1));
                                    }else{
                                        cell.setCellValue(data);
                                    }
                                }
                            }else{   //字段为空 检查该列是否是公式
                                if(colFormula!=null){
                                    String sixBuf = colFormula[columnIndex].replace("@", (rowIndex+1)+"");
                                    XSSFCell cell = dataRow.createCell(columnIndex);
                                    cell.setCellFormula(sixBuf.toString());
                                }
                            }
                        }
                    }
                }
            }*/