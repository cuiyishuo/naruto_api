package com.solplatform.util;


import com.solplatform.vo.ReportLog;
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

    // 空的构造方法
    public ReportLogUtil() {
    }

    /**
     * 构造时，初始化写入文件信息
     *
     * @param buildId
     */
    public ReportLogUtil(String buildId) {
        log.info ("初始化日志文件");
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
        log.info ("写入日志文件");
        StringBuffer stringBuffer = new StringBuffer ();
        stringBuffer.append ("【");
        stringBuffer.append (DateUtil.getCurrentDate ().toString ());
        stringBuffer.append ("】");
        stringBuffer.append (">>>> ");
        stringBuffer.append (msg);
        try {
            writer.write (stringBuffer.toString ()+"#End" + "\r\n");
            writer.flush ();
        } catch (IOException e) {
            log.error (e.getMessage ());
            log.error ("日志写入异常！");
        }
    }

    /**
     * 读取测试报告日志
     *
     * @param buildId      构建id，用于穿件log文件
     * @param lastFileSize 最后一次读取的指针位置
     * @return
     */
    public ReportLog readLog(String buildId, Long lastFileSize) {
        // 类似于InputStream的read()方法，以及类似于OutputStream的write()方法
        log.info ("读取日志文件");
        RandomAccessFile randomAccessFile = null;
        ReportLog reportLog = new ReportLog ();
        try {
            String filePath = buildId + ".log";
            // 指定只读模式
            randomAccessFile = new RandomAccessFile (filePath, "r");
            // 记录从哪里开始读取文件
            randomAccessFile.seek (lastFileSize);
            String temp = null;
            StringBuffer stringBuffer = new StringBuffer ();
            // 如果文件不为空，遍历文件
            while ((temp = randomAccessFile.readLine ()) != null) {
                // 将读取到的一行，通过 new String(byte[], decode)实际是使用decode指定的编码来将byte[]解析成字符串
                String text = new String (temp.getBytes ("ISO-8859-1"), "UTF-8");
                stringBuffer.append (text);
            }
            // 获取这次读取到的文件长度
            lastFileSize = randomAccessFile.getFilePointer ();
            reportLog.setLastTimeFileSize (lastFileSize);
            reportLog.setText (stringBuffer.toString ());
        } catch (FileNotFoundException e) {
            log.error (e.getMessage ());
            log.error ("文件未找到，请检查路径填写是否正确！");
            reportLog.setText ("日志打印异常");
        } catch (IOException e) {
            log.error (e.getMessage ());
            log.error ("日志读取异常！");
            reportLog.setText ("日志打印异常");
        } finally {
            return reportLog;
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
