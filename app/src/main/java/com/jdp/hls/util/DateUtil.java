package com.jdp.hls.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:TODO
 * Create Time:2018/9/18 0018 上午 10:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DateUtil {

    public static String getDateString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(d);
    }

    public static Date str2Date(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }

    public static String date2Str(Date date) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(date);
    }

    public static String getShortDate(String longDate) {
        return date2Str(str2Date(longDate));
    }
}
