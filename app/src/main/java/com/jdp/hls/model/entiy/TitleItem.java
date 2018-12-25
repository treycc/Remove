package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/12/25 0025 下午 1:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TitleItem {
    private String Title;
    private List<KeyValue> Items;

    public String getTitle() {
        return null == Title ? "" : Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public List<KeyValue> getItems() {
        return Items;
    }

    public void setItems(List<KeyValue> items) {
        Items = items;
    }
}
