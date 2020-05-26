package com.solplatform.util;

import org.springframework.stereotype.Component;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 计算工具
 *
 * @author sol
 * @create 2020-05-26  3:01 下午
 */
@Component
public class Calculat {
    /**
     * 计算百分比
     *
     * @param num   分子
     * @param total 分母
     * @param scale 小数精确到第几位
     * @return
     */
    public static double percentage(double num, double total, int scale) {
        DecimalFormat df = (DecimalFormat) NumberFormat.getInstance ();
        //可以设置精确几位小数
        df.setMaximumFractionDigits (scale);
        //模式 例如四舍五入
        df.setRoundingMode (RoundingMode.HALF_UP);
        double accuracy_num = num / total;
        System.out.println ("转化为百分比为：" + df.format (accuracy_num * 100) + "%");
        return accuracy_num;
    }
}
