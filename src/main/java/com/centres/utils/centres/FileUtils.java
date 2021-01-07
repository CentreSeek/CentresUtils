/**
 * Copyright (C), 2019, 义金(杭州)健康科技有限公司
 * FileName: FileUtils
 * Author:   CentreS
 * Date:     2019/7/1 15:48
 * Description: 文件上传工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.yjjk.monitor.utility;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * @author CentreS
 * @Description: 文件上传工具类
 * @create 2019/7/1
 */
public class FileUtils {
    /**
     * 上传文件
     *
     * @param file     文件
     * @param path     文件存放路径
     * @param fileName 源文件名
     * @return
     */
    public static boolean upload(MultipartFile file, String path, String fileName) {
        String realPath = path + "/" + fileName;
        File dest = new File(realPath);
        // 判断父级目录是否存在
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }

        try {
            // 保存文件
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取当前路径
     *
     * @return
     */
    public static String getRootPath() {
        return System.getProperty("user.dir");
    }

    /**
     * 删除文件
     *
     * @param file
     * @return
     */
    public static boolean delFile(File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        File[] files = file.listFiles();
        for (File f : files) {
            delFile(f);
        }
        return file.delete();
    }


    public static String readEcgFile(String path, String name) throws IOException {
        String pathname = path + "\\" + name; // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
        File filename = new File(pathname); // 要读取以上路径的input。txt文件
        InputStreamReader reader = new InputStreamReader(
                new FileInputStream(filename)); // 建立一个输入流对象reader
        BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
        StringBuilder line = new StringBuilder();
        line.append(br.readLine());
        while (!StringUtils.isNullorEmpty(br.readLine())) {
            line.append(br.readLine());
        }
        return line.toString();
    }

    public static ArrayList<String> getAllFileName(String path) {
        ArrayList<String> files = new ArrayList<String>();
        boolean flag = false;
        File file = new File(path);
        File[] tempList = file.listFiles();

        if (tempList == null || tempList.length == 0) {
            return new ArrayList<>();
        }
        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].getName().split("\\.")[0]);
            }
        }
        return files;
    }

    public static void main(String[] args) {
        System.out.println(getAllFileName("D:\\ExportData").toString());
    }

}
