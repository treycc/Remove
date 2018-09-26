package com.jdp.hls.util;

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
}
