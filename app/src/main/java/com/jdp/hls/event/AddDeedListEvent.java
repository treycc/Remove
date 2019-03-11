package com.jdp.hls.event;

import com.jdp.hls.model.entiy.DeedItem;

/**
 * Description:TODO
 * Create Time:2019/3/11 0011 下午 2:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddDeedListEvent {
    private DeedItem deedItem;

    public AddDeedListEvent(DeedItem deedItem) {
        this.deedItem = deedItem;
    }

    public DeedItem getDeedItem() {
        return deedItem;
    }

    public void setDeedItem(DeedItem deedItem) {
        this.deedItem = deedItem;
    }
}
