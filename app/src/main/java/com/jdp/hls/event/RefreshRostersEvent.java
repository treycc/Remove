package com.jdp.hls.event;

/**
 * Description:TODO
 * Create Time:2018/8/13 0013 上午 11:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RefreshRostersEvent {
    private int isEnterprisep;

    public RefreshRostersEvent() {
    }

    public RefreshRostersEvent(int isEnterprisep) {
        this.isEnterprisep = isEnterprisep;
    }

    public int getIsEnterprisep() {
        return isEnterprisep;
    }

    public void setIsEnterprisep(int isEnterprisep) {
        this.isEnterprisep = isEnterprisep;
    }
}
