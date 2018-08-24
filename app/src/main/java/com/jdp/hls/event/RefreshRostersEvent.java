package com.jdp.hls.event;

/**
 * Description:TODO
 * Create Time:2018/8/13 0013 上午 11:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RefreshRostersEvent {
    private boolean isEnterprisep;

    public RefreshRostersEvent() {
    }

    public RefreshRostersEvent(boolean isEnterprisep) {
        this.isEnterprisep = isEnterprisep;
    }

    public boolean getIsEnterprisep() {
        return isEnterprisep;
    }

    public void setIsEnterprisep(boolean isEnterprisep) {
        this.isEnterprisep = isEnterprisep;
    }
}
