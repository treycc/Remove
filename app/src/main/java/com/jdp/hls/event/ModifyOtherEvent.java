package com.jdp.hls.event;

import com.jdp.hls.model.entiy.OtherArea;

/**
 * Description:TODO
 * Create Time:2018/10/9 0009 下午 2:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyOtherEvent {
    private OtherArea otherArea;

    public OtherArea getOtherArea() {
        return otherArea;
    }

    public void setOtherArea(OtherArea otherArea) {
        this.otherArea = otherArea;
    }

    public ModifyOtherEvent(OtherArea otherArea) {
        this.otherArea = otherArea;
    }
}
