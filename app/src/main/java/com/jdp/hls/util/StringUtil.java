package com.jdp.hls.util;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/11 0011 上午 10:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StringUtil {
    public static String getIds(List list) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (i != list.size() - 1) {
                sb.append(list.get(i));
                sb.append("#");
            } else {
                sb.append(list.get(i));
            }
        }
        return sb.toString();
    }
}
