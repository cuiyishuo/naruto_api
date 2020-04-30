package com.solplatform.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 获得时间
 *
 * @author sol
 * @create 2020-04-30  3:04 下午
 */
public class DateUtil {

    /**
     * 获得当前时间
     *
     * @return
     */
    public static String getCurrentDate() {
        Calendar calendar = Calendar.getInstance ();
        SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy-MM-dd_hh:mm:ss");
        return dateFormat.format (calendar.getTime ());
    }


}
