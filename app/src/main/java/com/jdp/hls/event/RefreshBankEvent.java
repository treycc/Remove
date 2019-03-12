package com.jdp.hls.event;

/**
 * Description:TODO
 * Create Time:2019/3/12 0012 上午 9:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RefreshBankEvent {
    private boolean isAdd;

    public RefreshBankEvent(boolean isAdd) {
        this.isAdd = isAdd;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
