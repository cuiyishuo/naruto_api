package com.solplatform.util;


import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * 测试报告日志
 *
 * @author sol
 * @create 2020-06-19  6:25 下午
 */
@Slf4j
public class ReportLogUtil {
    private OutputStreamWriter writer;
    private FileOutputStream fileOutputStream;

    /**
     * 构造时，初始化写入文件信息
     *
     * @param buildId
     */
    public ReportLogUtil(String buildId) {
        try {
            File file = new File (buildId + ".log");
            System.out.println ("文件路径：" + file.getAbsolutePath ());
            fileOutputStream = new FileOutputStream (file);
            writer = new OutputStreamWriter (fileOutputStream, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error (e.getMessage ());
            log.error ("写入时编码格式异常！");
        } catch (FileNotFoundException e) {
            log.error (e.getMessage ());
            log.error ("日志文件未找到！");
        }
    }

    /**
     * 将日志写入指定文件
     *
     * @param msg 日志信息
     */
    public void writeLog(String msg) {
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ("【");
        stringBuffer.append (DateUtil.getCurrentDate ().toString ());
        stringBuffer.append ("】");
        stringBuffer.append (">>>> ");
        stringBuffer.append (msg);
        try {
            writer.write (stringBuffer.toString () + "\r\n");
            writer.flush ();
        } catch (IOException e) {
            log.error (e.getMessage ());
            log.error ("日志写入异常！");
        }
    }

    /**
     * 红色日志
     *
     * @param msg
     */
    public void writeRedLog(String msg) {
        writeLog (msg + "&red");
    }

    /**
     * 绿色日志
     *
     * @param msg
     */
    public void writeGreenLog(String msg) {
        writeLog (msg + "&green");
    }

    /**
     * 灰色日志
     *
     * @param msg
     */
    public void writeGreyLog(String msg) {
        writeLog (msg + "&grey");
    }


    /**
     * 关闭输出流
     */
    public void closeWriter() {
        try {
            fileOutputStream.close ();
            writer.close ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
