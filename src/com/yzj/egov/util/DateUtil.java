package com.yzj.egov.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 返回指定格式日期
     * @param date 日期
     * @param format 指定格式
     * @return String 类型字符串
     */
    public static String format(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }

}
