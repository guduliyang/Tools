package com.crazy.test.tools.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ADMIN on 2016/12/27.
 */

public class DateFormat {
    private static SimpleDateFormat dateFormater ;

    /**
     * 【格式化日期】
     * @param Date long 长整形日期
     * @return  String ： "yyyy-MM-dd" 格式显示日期
     */
    public static String format(long Date){
        dateFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(Date);
        return dateFormater.format(date);
    }
}
