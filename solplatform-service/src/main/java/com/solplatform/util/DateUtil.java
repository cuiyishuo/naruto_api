package com.solplatform.util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

/**
 * 获得时间
 *
 * @author sol
 * @create 2020-04-30  3:04 下午
 */
public class DateUtil {

    public static final String YMD_HMS="yyyy-MM-dd HH:mm:ss";

    /**
     * 获得当前时间(时分秒）
     *
     * @return
     */
    public static LocalDateTime getCurrentDate() {
        LocalDateTime now = LocalDateTime.now();
        return now;
    }

    /**
     * 获取当前的时间,自定义时间格式
     *
     * **/
    public static String getCurrentDate(String pattern){
        Calendar calendar = Calendar.getInstance ();
        SimpleDateFormat dateFormat = new SimpleDateFormat (pattern);
        return dateFormat.format (calendar.getTime ());
    }


}
