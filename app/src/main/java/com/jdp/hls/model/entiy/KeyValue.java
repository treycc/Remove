package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/30 0030 下午 4:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class KeyValue {
    private String Key;
    private String Value;
    private int Type;

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }
}
