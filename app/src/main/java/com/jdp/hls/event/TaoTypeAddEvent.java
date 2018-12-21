package com.jdp.hls.event;

import com.jdp.hls.model.entiy.TaoType;

/**
 * Description:TODO
 * Create Time:2018/12/17 0017 下午 3:47
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaoTypeAddEvent {
    public TaoTypeAddEvent(TaoType taoType) {
        this.taoType = taoType;
    }

    private TaoType taoType;

    public TaoType getTaoType() {
        return taoType;
    }

    public void setTaoType(TaoType taoType) {
        this.taoType = taoType;
    }
}
