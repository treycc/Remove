package com.jdp.hls.event;

/**
 * Description:TODO
 * Create Time:2019/3/11 0011 下午 4:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RefreshCertCountEvent {

    private int certType;
    private boolean isAdd;

    public RefreshCertCountEvent(int certType, boolean isAdd) {
        this.certType = certType;
        this.isAdd = isAdd;
    }

    public int getCertType() {
        return certType;
    }

    public void setCertType(int certType) {
        this.certType = certType;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
