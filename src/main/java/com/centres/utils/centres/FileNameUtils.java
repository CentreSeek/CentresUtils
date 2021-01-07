/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: FileNameUtils
 * Author:   CentreS
 * Date:     2019/7/1 15:43
 * Description: 文件名生成工具
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.utility;

import javax.xml.bind.SchemaOutputResolver;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author CentreS
 * @Description: 文件名生成工具
 * @create 2019/7/1
 */
public class FileNameUtils {

    /**
     * 生成新的文件名
     *
     * @param caseNum
     * @return
     */
    public static String getReportId(String caseNum) {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMdd");
        StringBuffer buffer = new StringBuffer();
        buffer.append(ft.format(date)).append(caseNum);
        return buffer.toString();
    }

    /**
     * 历史记录导出文件名
     *
     * @return
     */
    public static String getHistoryFileName() {
        Date date = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmm");
        StringBuffer buffer = new StringBuffer();
        buffer.append("th").append(ft.format(date));
        return buffer.toString();
    }

    /**
     * 获取文件后缀
     *
     * @param fileName
     * @return
     */
    public static String getSuffix(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 获取文件前缀
     *
     * @param fileName
     * @return
     */
    public static String getPrefix(String fileName) {
        return fileName.split("\\.")[0];
    }

    /**
     * 生成新的文件名
     *
     * @param fileOriginName
     * @return
     */
    public static String getFileName(String fileOriginName) {
        return UUIDUtils.getUUID() + FileNameUtils.getSuffix(fileOriginName);
    }

    public static String getEcgExportFileName(String name, String caseNum, String createTime) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = f.parse(createTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat ft = new SimpleDateFormat("yyyyMMddHHmm");
        StringBuffer buffer = new StringBuffer();
        String fileName = name + "&" + caseNum + "(" + ft.format(date) + ")";
        return fileName;
    }

    public static void main(String[] args) {
        System.out.println(getEcgExportFileName("yu","123456","2020-09-25 17:45:29"));
    }
}
