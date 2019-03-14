package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2019/3/14 0014 上午 10:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TitleValue {
    private String Title;
    private String Value;
    private String ItemId;

    public String getTitle() {
        return null == Title ? "" : Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getValue() {
        return null == Value ? "" : Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getItemId() {
        return null == ItemId ? "" : ItemId;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }
}
