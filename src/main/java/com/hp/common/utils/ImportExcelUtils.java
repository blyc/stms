package com.hp.common.utils;

import com.hp.student.bo.Studentbo;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ImportExcelUtils {
    private FileInputStream fs;
    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private XSSFRow row;

    /**
     * 读取Excel表格表头的内容
     *
     * @param
     * @return String 表头内容的数组
     */
    public String[] readExcelTitle(String is) {
        try {
            fs = new FileInputStream(is);
            wb = new XSSFWorkbook(fs);
            //  wb = new XSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        row = sheet.getRow(1);
        // 标题总列数
        int colNum = row.getPhysicalNumberOfCells();
        System.out.println("colNum:" + colNum);
        String[] title = new String[colNum];
        for (int i = 1; i < colNum; i++) {
            title[i] = getStringCellValue(row.getCell((short) i));
            title[i] = getCellFormatValue(row.getCell((short) i));
        }
        return title;
    }

    /**
     * 读取Excel数据内容
     *
     * @param
     * @return Map 包含单元格数据内容的Map对象
     */
    public List<Object> readExcelContent(String is) {
        List<Object> objectList = new ArrayList<Object>();
        try {
            fs = new FileInputStream(is);
            wb = new XSSFWorkbook(fs);


        } catch (IOException e) {
            e.printStackTrace();
        }

        sheet = wb.getSheetAt(0);
        // 得到总行数
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(2);
        int colNum = row.getPhysicalNumberOfCells();
        String[] title = readExcelTitle(is);

        // 正文内容应该从第二行开始,第一行为表头的标题
        Studentbo studentbo = null;
        for (int i = 2; i <= rowNum; i++) {
            studentbo = new Studentbo();
            row = sheet.getRow(i);
            int j = 1;
            while (j < colNum) {
                Class<?> clazz = Studentbo.class;
                String methodName = "set" + title[j];
                try {
                    Method method = clazz.getMethod(methodName, String.class);
                 method.invoke(studentbo, getStringCellValue(row.getCell((short) j)));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
                j++;
            }
            objectList.add(studentbo);
        }
        return objectList;
    }

    /**
     * 获取单元格数据内容为字符串类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getStringCellValue(XSSFCell cell) {
        String strCell = "";
        switch (cell.getCellType()) {
            case XSSFCell.CELL_TYPE_STRING:
                strCell = cell.getStringCellValue();
                break;
            case XSSFCell.CELL_TYPE_NUMERIC:
                strCell = String.valueOf(cell.getNumericCellValue());
                break;
            case XSSFCell.CELL_TYPE_BOOLEAN:
                strCell = String.valueOf(cell.getBooleanCellValue());
                break;
            case XSSFCell.CELL_TYPE_BLANK:
                strCell = "";
                break;
            default:
                strCell = "";
                break;
        }
        if (strCell.equals("") || strCell == null) {
            return "";
        }
        if (cell == null) {
            return "";
        }
        return strCell;
    }

    /**
     * 获取单元格数据内容为日期类型的数据
     *
     * @param cell Excel单元格
     * @return String 单元格数据内容
     */
    private String getDateCellValue(XSSFCell cell) {
        String result = "";
        try {
            int cellType = cell.getCellType();
            if (cellType == XSSFCell.CELL_TYPE_NUMERIC) {
                Date date = cell.getDateCellValue();
                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
                        + "-" + date.getDate();
            } else if (cellType == XSSFCell.CELL_TYPE_STRING) {
                String date = getStringCellValue(cell);
                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
            } else if (cellType == XSSFCell.CELL_TYPE_BLANK) {
                result = "";
            }
        } catch (Exception e) {
            System.out.println("日期格式不正确!");
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据HSSFCell类型设置数据
     *
     * @param cell
     * @return
     */
    private String getCellFormatValue(XSSFCell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case XSSFCell.CELL_TYPE_NUMERIC:
                case XSSFCell.CELL_TYPE_FORMULA: {
                    // 判断当前的cell是否为Date
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        // 如果是Date类型则，转化为Data格式

                        //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
                        cellvalue = cell.getDateCellValue().toLocaleString();
                        System.out.println("============="+cellvalue);
                        //方法2：这样子的data格式是不带带时分秒的：2011-10-12
                       /* Date date = cell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");*/
                        //cellvalue = sdf.format(date);

                    }
                    // 如果是纯数字
                    else {
                        // 取得当前Cell的数值
                        cellvalue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case XSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getRichStringCellValue().getString();
                    break;
                // 默认的Cell值
                default:
                    cellvalue = " ";
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;

    }

}