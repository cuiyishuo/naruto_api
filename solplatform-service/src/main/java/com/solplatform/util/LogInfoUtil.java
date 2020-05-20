package com.solplatform.util;

/**
 * 日志获取类/方法信息工具
 *
 * @author sol
 * @create 2020-05-19  11:31 下午
 */
public class LogInfoUtil {

    public static String getCurrentMethod() {
        // 返回当前方法上一层方法的名称，也就是调用本方法的方法的名称
        return Thread.currentThread ().getStackTrace ()[2].getMethodName ();
    }
}
