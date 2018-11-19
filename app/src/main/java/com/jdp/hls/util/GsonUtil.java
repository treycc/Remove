package com.jdp.hls.util;

import com.google.gson.Gson;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 6:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GsonUtil {
    public static <T> T json2obj(String json, Class<T> clazz) {
        Gson gson = new Gson();
        return gson.fromJson(json, clazz);
    }
}
