package com.jdp.hls.util;

/**
 * Description:TODO
 * Create Time:2019/3/11 0011 下午 4:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherUtil {

    public static int getNewCount(int oldCount, boolean isAdd) {
        int newCount = oldCount;
        if (isAdd) {
            newCount++;
        } else {
            newCount--;
        }
        return newCount;
    }
}
