package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/12/19 0019 上午 9:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddPayEvent {
    private PayItem payItem;

    public AddPayEvent(PayItem payItem) {
        this.payItem = payItem;
    }

    public PayItem getPayItem() {
        return payItem;
    }

    public void setPayItem(PayItem payItem) {
        this.payItem = payItem;
    }
}
