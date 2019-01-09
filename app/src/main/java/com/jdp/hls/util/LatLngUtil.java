package com.jdp.hls.util;

/**
 * Description:TODO
 * Create Time:2019/1/8 0008 上午 10:44
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LatLngUtil {

    public static boolean isChinaLatLng(double latitude, double longitude) {
        return latitude >= 10 && latitude <= 63 && longitude >= 73 && longitude <= 145;
    }
}
