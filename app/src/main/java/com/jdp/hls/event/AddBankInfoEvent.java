package com.jdp.hls.event;

import com.jdp.hls.model.entiy.BankInfo;

/**
 * Description:TODO
 * Create Time:2019/1/4 0004 下午 2:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddBankInfoEvent {
    private BankInfo bankInfo;

    public AddBankInfoEvent(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }

    public BankInfo getBankInfo() {
        return bankInfo;
    }

    public void setBankInfo(BankInfo bankInfo) {
        this.bankInfo = bankInfo;
    }
}
