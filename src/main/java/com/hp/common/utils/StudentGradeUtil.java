package com.hp.common.utils;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.List;


public class StudentGradeUtil {

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
    private short titleFontSize = 16;
    //添加自动筛选的列 如 A:M
    private String address = "";
    //正文字体
    private String contentFontType = "Arial Unicode MS";
    //正文字号
    private short contentFontSize = 16;
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

    public StudentGradeUtil() {
    }

    public StudentGradeUtil(ServletOutputStream servletOutputStream, String sheetName) {
        this.sheetName = sheetName;
        this.servletOutputStream = servletOutputStream;
    }

    public StudentGradeUtil(String fileDir, String sheetName) {
        this.fileDir = fileDir;
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
    }

    public StudentGradeUtil(String sheetName) {
        this.fileDir = fileDir;
        this.sheetName = sheetName;
        workbook = new XSSFWorkbook();
    }

    public StudentGradeUtil(HttpServletResponse response, String fileName, String sheetName) {

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

    /**
     * 写excel.
     *
     * @param titleColumn 对应bean的属性名
     * @param titleName   excel要导出的表名
     * @param titleSize   列宽
     * @param dataList    数据
     */
    public void wirteExcel(String fileName, HttpServletResponse response, String titleColumn[], String titleName[], int titleSize[], List<?> dataList, Object object, String endColumn[], String endName[]) {
        //添加Worksheet（不添加sheet时生成的xls文件打开时会报错)
        XSSFSheet sheet = workbook.createSheet(this.sheetName);
        //新建文件
        OutputStream out = null;
        try {

            //否则，直接写到输出流中
            out = response.getOutputStream();
            //fileName = fileName + ".xlsx";

            response.setContentType("application/msexcel");
           // response.setContentType("application/x-msdownload");
            //response.reset();
            //response.setContentType("application/msexcel");
           /* response.setHeader("Content-Disposition", "attachment; filename="
                    + URLEncoder.encode(fileName, "UTF-8"));*/
           // response.setHeader("Content-Disposition", "inline; filename=" +toUtf8String("高润"));

            response.setHeader("Content-Disposition", "attachment;filename="+new String(fileName.getBytes("gbk"), "iso8859-1")+".xlsx");
            response.setCharacterEncoding("GBK");

            XSSFRow AlltitleNameRow = workbook.getSheet(sheetName).createRow(0);
            XSSFCell cell2 = AlltitleNameRow.createCell(0);
            cell2.setCellValue("考试成绩");
            //设置表头信息样式
            XSSFCellStyle alltitleStyle = workbook.createCellStyle();
            alltitleStyle = (XSSFCellStyle) setFontAndBorder(alltitleStyle, "黑体", (short) 25, true, String.valueOf((short) IndexedColors.WHITE.getIndex()), FillPatternType.SOLID_FOREGROUND);
            // 将字体样式添加到单元格样式中
            // 边框，居中
            alltitleStyle.setAlignment(HorizontalAlignment.CENTER);
            cell2.setCellStyle(alltitleStyle); // 为B2单元格添加样式
            //合并单元格
            CellRangeAddress cra = new CellRangeAddress(0, 0, 0, 2);
            sheet.addMergedRegion(cra);


            XSSFCellStyle titleStyle = workbook.createCellStyle();
            //写入excel的表头
            XSSFRow titleNameRow = workbook.getSheet(sheetName).createRow(1);
            //设置样式
            titleStyle = (XSSFCellStyle) setFontAndBorder(titleStyle, titleFontType, (short) titleFontSize, true, String.valueOf((short) IndexedColors.GREY_25_PERCENT.getIndex()), FillPatternType.SOLID_FOREGROUND);


            for (int i = 0; i < titleName.length; i++) {
                sheet.setColumnWidth(i, titleSize[i] * 500);    //设置宽度
                XSSFCell cell = titleNameRow.createCell(i);
                cell.setCellStyle(titleStyle);
                cell.setCellValue(titleName[i].toString());
            }
            //通过反射获取数据并写入到excel中
            if (dataList != null && dataList.size() > 0) {
                //设置样式
                XSSFCellStyle dataStyle = workbook.createCellStyle();
                dataStyle = (XSSFCellStyle) setFontAndBorder(dataStyle, "宋体", (short) contentFontSize, false, String.valueOf((short) IndexedColors.WHITE.getIndex()), FillPatternType.SOLID_FOREGROUND);

                if (titleColumn.length > 0) {
                    for (int rowIndex = 2; rowIndex <= dataList.size() + 1; rowIndex++) {
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
                                  /*反射中getMethods 与 getDeclaredMethods 的区别
                               public Method[] getMethods()返回某个类的所有公用（public）方法包括其继承类的公用方法，
                                当然也包括它所实现接口的方法。
                              public Method[] getDeclaredMethods()对象表示的类或接口声明的所有方法，
                                包括公共、保护、默认（包）访问和私有方法，但不包括继承的方法。当然也包括它所实现接口的方法。*/
                                Method method = clsss.getMethod(methodName);

                                //获取返回类型
                                String returnType = method.getReturnType().getName();

                                XSSFCell cell = dataRow.createCell(columnIndex);
                                cell.setCellStyle(dataStyle);
                                Object data1 = method.invoke(obj) == null ? "" : method.invoke(obj);
                                String data = method.invoke(obj) == null ? "" : method.invoke(obj).toString();
                                if (data != null && !"".equals(data)) {
                                    if ("int".equals(returnType)) {
                                        cell.setCellValue(Integer.parseInt(data));
                                    } else if ("long".equals(returnType)) {
                                        cell.setCellValue(Long.parseLong(data));
                                    } else if ("float".equals(returnType)) {
                                        cell.setCellValue(floatDecimalFormat.format(Float.parseFloat(data)));
                                    } else if ("double".equals(returnType)) {
                                        cell.setCellValue(doubleDecimalFormat.format(Double.parseDouble(data)));
                                    } else if ("java.util.Date".equals(returnType)) {
                                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        cell.setCellValue(sdf.format(data1));
                                        cell.setCellValue(data);
                                    } else {
                                        cell.setCellValue(data);
                                    }
                                }
                                cell.setCellStyle(dataStyle);
                            } else {   //字段为空 检查该列是否是公式
                                if (colFormula != null) {
                                    String sixBuf = colFormula[columnIndex].replace("@", (rowIndex + 1) + "");
                                    XSSFCell cell = dataRow.createCell(columnIndex);
                                    cell.setCellFormula(sixBuf.toString());
                                }
                            }
                        }
                    }
                    XSSFCell end1 = null;
                    for (int i = 0; i < endName.length; i++) {
                        //左标题
                        XSSFRow allEndNameRow = workbook.getSheet(sheetName).createRow(dataList.size() + 2 + i);

                        XSSFCellStyle leftTitleStyle = workbook.createCellStyle();
                        leftTitleStyle = (XSSFCellStyle) setFontAndBorder(leftTitleStyle, "宋体", (short) 18, true, String.valueOf((short) IndexedColors.LIGHT_TURQUOISE.getIndex()), FillPatternType.SOLID_FOREGROUND);

                        end1 = allEndNameRow.createCell(0);
                        XSSFCell end2 = allEndNameRow.createCell(1);
                        end1.setCellValue(endName[i].toString());
                        end1.setCellStyle(leftTitleStyle);
                        end2.setCellStyle(leftTitleStyle);
                        CellRangeAddress cra1 = new CellRangeAddress(dataList.size() + 2 + i, dataList.size() + 2 + i, 0, 1);
                        sheet.addMergedRegion(cra1);

                        //右内容
                        String end = endColumn[i].toString().trim();
                        String Uend = Character.toUpperCase(end.charAt(0)) + end.substring(1, end.length()); // 使其首字母大写;
                        String methodName = "get" + Uend;
                        Class aClass = object.getClass();
                        // 设置要执行的方法
                        Method method = aClass.getDeclaredMethod(methodName);

                        //获取返回类型
                        String returnType = method.getReturnType().getName();

                        XSSFCell cell = allEndNameRow.createCell(2);

                        cell.setCellStyle(dataStyle);

                        String data = method.invoke(object) == null ? "" : method.invoke(object).toString();

                        if (data != null && !"".equals(data)) {
                            if (("getQualifiedrate").equals(methodName)) {
                                data = data + "%";
                            }
                            if ("long".equals(returnType)) {
                                cell.setCellValue(Long.parseLong(data));
                            } else if ("double".equals(returnType)) {
                                cell.setCellValue(doubleDecimalFormat.format(Double.parseDouble(data)));
                            } else {
                                cell.setCellValue(data);
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
            // style1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);

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
     *              ForegroundColor  颜色
     *              Pattern          样式
     * @return
     */
    public XSSFCellStyle setFontAndBorder(XSSFCellStyle style, String fontName, short size, boolean bold, String ForegroundColor, FillPatternType Pattern) {
        XSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(size);
        font.setFontName(fontName);
        font.setBold(bold);
        style.setFont(font);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setFillForegroundColor(Short.parseShort(ForegroundColor));
        style.setFillPattern(Pattern);

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
