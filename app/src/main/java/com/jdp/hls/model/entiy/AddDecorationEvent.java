package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/10/12 0012 下午 5:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddDecorationEvent {
    private DecorationItem decorationItem;

    public AddDecorationEvent(DecorationItem decorationItem) {
        this.decorationItem = decorationItem;
    }

    public DecorationItem getDecorationItem() {
        return decorationItem;
    }

    public void setDecorationItem(DecorationItem decorationItem) {
        this.decorationItem = decorationItem;
    }
}
