package com.hp.project.util;


import com.hp.project.bo.ExportClassProjectResults;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by 晏利花 on 2018/4/1.
 */
public class ExporeStudentProjectUtil {
    // HttpServletResponse response;
    // 文件名
    private String fileName;
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

    DecimalFormat floatDecimalFormat = new DecimalFormat(floatDecimal);
    DecimalFormat doubleDecimalFormat = new DecimalFormat(doubleDecimal);


    private XSSFWorkbook workbook = null;

    public ExporeStudentProjectUtil() {
    }

    public ExporeStudentProjectUtil(ServletOutputStream servletOutputStream, String sheetName) {
        this.sheetName = sheetName;
        this.servletOutputStream = servletOutputStream;
    }

    public ExporeStudentProjectUtil(String fileDir, String sheetName) {
        this.fileDir = fileDir;
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
    }

    public ExporeStudentProjectUtil(String sheetName) {
        this.fileDir = fileDir;
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
    }
    public ExporeStudentProjectUtil(HttpServletResponse response, String fileName, String sheetName) {

        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
    }

    /**
     * 设置表头字体.
     *
     * @param titleFontType
     */
    public void setTitleFontType(String titleFontType) {
        this.titleFontType = titleFontType;
    }

    /**
     * 设置表头背景色.
     *
     * @param titleBackColor 十六进制
     */
    public void setTitleBackColor(String titleBackColor) {
        this.titleBackColor = titleBackColor;
    }

    /**
     * 设置表头字体大小.
     *
     * @param titleFontSize
     */
    public void setTitleFontSize(short titleFontSize) {
        this.titleFontSize = titleFontSize;
    }

    /**
     * 设置表头自动筛选栏位,如A:AC.
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 设置正文字体.
     *
     * @param contentFontType
     */
    public void setContentFontType(String contentFontType) {
        this.contentFontType = contentFontType;
    }

    /**
     * 设置正文字号.
     *
     * @param contentFontSize
     */
    public void setContentFontSize(short contentFontSize) {
        this.contentFontSize = contentFontSize;
    }

    /**
     * 设置float类型数据小数位 默认.00
     *
     * @param doubleDecimal 如 ".00"
     */
    public void setDoubleDecimal(String doubleDecimal) {
        this.doubleDecimal = doubleDecimal;
    }

    /**
     * 设置doubel类型数据小数位 默认.00
     *
     * @param floatDecimalFormat 如 ".00
     */
    public void setFloatDecimalFormat(DecimalFormat floatDecimalFormat) {
        this.floatDecimalFormat = floatDecimalFormat;
    }

    /**
     * 设置列的公式
     *
     * @param colFormula 存储i-1列的公式 涉及到的行号使用@替换 如A@+B@
     */
    public void setColFormula(String[] colFormula) {
        this.colFormula = colFormula;
    }







    /*创建excel表格*/
    /**
     * 写excel.
     *
     *
     * @param dataList    数据
     */
    public void wirteExcel(String fileName, HttpServletResponse response, ExportClassProjectResults ecpr, List<?> dataList) {


       /*在这里定义一下要查出的学生的成绩，这是一个独立的方法*/
        String titleColumn[] = {"name", "rate"};
        String titleName[] = {"姓名", "成绩"};

        int titleSize[] = {13,13};
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        XSSFSheet sheet = workbook.createSheet(this.sheetName);

        //新建文件
        OutputStream out = null;
        try {

            //否则，直接写到输出流中
            out = response.getOutputStream();
            fileName = fileName + ".xlsx";
            response.setContentType("application/x-msdownload");
            response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "UTF-8"));


            //写入excel的表头
            XSSFRow titleNameRow = workbook.getSheet(sheetName).createRow(1);
            //设置样式
            XSSFCellStyle titleStyle = workbook.createCellStyle();
            titleStyle = (XSSFCellStyle) setFontAndBorder(titleStyle, titleFontType, (short) titleFontSize);
            titleStyle = (XSSFCellStyle) setColor(titleStyle, titleBackColor, (short) 10);
            CellRangeAddress cra = new CellRangeAddress(0,0,0,1);
            sheet.setColumnWidth(0, titleSize[0] * 256);
            sheet.addMergedRegion(cra);
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell1 = row.createCell(0);
            cell1.setCellStyle(titleStyle);

            //放班级名
            cell1.setCellValue(ecpr.getCname().toString());


            for (int i = 0; i < titleName.length; i++) {
                sheet.setColumnWidth(i, titleSize[i] * 256);    //设置宽度
                XSSFCell cell = titleNameRow.createCell(i);
                cell.setCellStyle(titleStyle);
                cell.setCellValue(titleName[i].toString());
            }
            //通过反射获取数据并写入到excel中
            if (dataList != null && dataList.size() > 0) {
                //设置样式
                XSSFCellStyle dataStyle = workbook.createCellStyle();
                titleStyle = (XSSFCellStyle) setFontAndBorder(titleStyle, contentFontType, (short) contentFontSize);
                if (titleColumn.length > 0) {
                    /*原来rowIndex = 2，现在把它==让他等于一是为了空出班级这一行，并且对单元格进行合并操作，（在原来的基础上+1或者+（-1））*/
                    for (int rowIndex = 2; rowIndex <= dataList.size()+1; rowIndex++) {
                        Object obj = dataList.get(rowIndex - 2);     //获得该对象
                        Class clsss = obj.getClass();     //获得该对对象的class实例
                        XSSFRow dataRow = workbook.getSheet(sheetName).createRow(rowIndex);
                        for (int columnIndex = 0; columnIndex < titleColumn.length; columnIndex++) {
                            String title = titleColumn[columnIndex].toString().trim();
                            if (!"".equals(title)) {  //字段不为空
                                //使首字母大写
                                String UTitle = Character.toUpperCase(title.charAt(0)) + title.substring(1, title.length()); // 使其首字母大写;
                                String methodName = "get" + UTitle;

                                // 设置要执行的方法
                                Method method = clsss.getDeclaredMethod(methodName);
                                /*Method method = clsss.getMethod(methodName);*/

                                //获取返回类型
                                String returnType = method.getReturnType().getName();

                                XSSFCell cell = dataRow.createCell(columnIndex);
                                Object data1 = method.invoke(obj) == null ? "" : method.invoke(obj);
                                String data = method.invoke(obj) == null ? "" : method.invoke(obj).toString();
                                /*在这里判断项目成绩的格式，与界面的数据作对比，把成绩转成汉字放到excel表格里*/
                                if (data != null && !"".equals(data)) {
                                   if ("java.lang.Long".equals(returnType)) {
                                        /*cell.setCellValue(Long.parseLong(data));*/
                                        if (data1.equals(1l)){
                                            cell.setCellValue("合格");
                                        }if (data1.equals(2l)){
                                           cell.setCellValue("不合格");
                                       }if (data1.equals(3l)){
                                           cell.setCellValue("未交");
                                       }
                                    }  else {
                                        cell.setCellValue(data);
                                    }
                                }
                            } else {   //字段为空 检查该列是否是公式
                                if (colFormula != null) {
                                    String sixBuf = colFormula[columnIndex].replace("@", (rowIndex + 1) + "");
                                    XSSFCell cell = dataRow.createCell(columnIndex);
                                    cell.setCellFormula(sixBuf.toString());
                                }
                            }
                        }
                    }

                }
            }
            /*通过反射获取数据并写入到excel中，把算出的合格率显示到excel表格上，显示dataList.size()*/
            Integer integer= dataList.size()+2;
            XSSFRow row2 = sheet.createRow(integer);
            XSSFCell cell2 = row2.createCell(0);
            XSSFCell cell3 = row2.createCell(1);
            cell2.setCellStyle(titleStyle);
            cell3.setCellStyle(titleStyle);
            cell2.setCellValue("总合格率");

            //放合格率
            cell3.setCellValue(ecpr.getSqualified().toString());

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
     *
     * @param style 保证style统一
     * @param color 颜色：66FFDD
     * @param index 索引 8-64 使用时不可重复
     * @return
     */
    public XSSFCellStyle setColor(XSSFCellStyle style, String color, short index) {
        if (color != "" && color != null) {
            //转为RGB码
            int r = Integer.parseInt((color.substring(0, 2)), 16);   //转为16进制
            int g = Integer.parseInt((color.substring(2, 4)), 16);
            int b = Integer.parseInt((color.substring(4, 6)), 16);
            //自定义cell颜色

            XSSFCellStyle style1 = workbook.createCellStyle();
            style1.setFillForegroundColor(new XSSFColor(new java.awt.Color(r, g, b)));

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
     *
     * @param style 样式
     * @param style 字体名
     * @param style 大小
     * @return
     */
    public XSSFCellStyle setFontAndBorder(XSSFCellStyle style, String fontName, short size) {
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
     *
     * @return
     */
    public boolean deleteExcel() {
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
     *
     * @return
     */
    public boolean deleteExcel(String path) {
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

