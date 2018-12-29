package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/12/29 0029 上午 10:02
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BrankInfo {
    private String RealName;
    private String BrankAccount;

    public String getRealName() {
        return null == RealName ? "" : RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getBrankAccount() {
        return null == BrankAccount ? "" : BrankAccount;
    }

    public void setBrankAccount(String brankAccount) {
        BrankAccount = brankAccount;
    }
}
