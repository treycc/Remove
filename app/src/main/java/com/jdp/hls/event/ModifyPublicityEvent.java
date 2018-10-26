package com.jdp.hls.event;

import com.jdp.hls.model.entiy.PublicityItem;

/**
 * Description:TODO
 * Create Time:2018/10/24 0024 下午 6:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyPublicityEvent {
    private PublicityItem publicityItem;

    public ModifyPublicityEvent(PublicityItem publicityItem) {
        this.publicityItem = publicityItem;
    }

    public PublicityItem getPublicityItem() {
        return publicityItem;
    }

    public void setPublicityItem(PublicityItem publicityItem) {
        this.publicityItem = publicityItem;
    }
}
