package com.jdp.hls.util;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2019/3/7 0007 下午 1:48
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DataHolder {
    private static final DataHolder holder = new DataHolder();
    Map<String, WeakReference<Object>> data = new HashMap<String, WeakReference<Object>>();

    public static DataHolder getInstance() {
        return holder;
    }



    public void save(String id, Object object) {
        data.put(id, new WeakReference<Object>(object));
    }

    public Object retrieve(String id) {
        WeakReference<Object> objectWeakReference = data.get(id);
        return objectWeakReference.get();
    }
}
