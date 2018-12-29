package com.jdp.hls.event;

import com.jdp.hls.model.entiy.BrankInfo;

/**
 * Description:TODO
 * Create Time:2018/12/29 0029 上午 10:21
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyBrankInfoEvent {
    private BrankInfo brankInfo;

    public ModifyBrankInfoEvent(BrankInfo brankInfo) {
        this.brankInfo = brankInfo;
    }

    public BrankInfo getBrankInfo() {
        return brankInfo;
    }

    public void setBrankInfo(BrankInfo brankInfo) {
        this.brankInfo = brankInfo;
    }
}
