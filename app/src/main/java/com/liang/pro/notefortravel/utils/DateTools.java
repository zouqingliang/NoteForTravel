package com.liang.pro.notefortravel.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/21.
 */
public class DateTools {

    public DateTools() {
    }

    public static String getWriteDate() {
        Date date = new Date();
        String writeDate = getFormatDateTime(date,"yyyy-MM-dd");
        return writeDate;
    }

    /**
     * 将Date类型转换成String
     * @param date
     * @param format
     * @return
     */
    public static String getFormatDateTime(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 将String类型转换成Date
     * @param s
     * @param format
     * @return
     */
    public static Date getDate(String s, String format) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
