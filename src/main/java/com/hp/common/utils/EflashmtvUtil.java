package com.hp.common.utils;

import com.hp.core.config.IConfig;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class EflashmtvUtil {
    public static void downloadway(HttpServletResponse response, String fileName) {
        response.setContentType("application/msexcel");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("gbk"), "iso8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setCharacterEncoding("GBK");
        String path = IConfig.get("templatepath");
        try {
            InputStream inputStream = new FileInputStream(new File(path + File.separator + fileName));
            OutputStream os = response.getOutputStream();
            int len = 0;
            while ((len = inputStream.read()) != -1) {
                os.write(len);
                os.flush();
            }
            os.close();
            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
