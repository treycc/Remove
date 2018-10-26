package com.jdp.hls.event;

/**
 * Description:TODO
 * Create Time:2018/10/26 0026 下午 7:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RefreshCertNumEvent {
    private String certNum;
    private int certType;
    private int buildType;

    public int getBuildType() {
        return buildType;
    }

    public void setBuildType(int buildType) {
        this.buildType = buildType;
    }

    public RefreshCertNumEvent(String certNum, int certType, int buildType) {
        this.certNum = certNum;
        this.certType = certType;
        this.buildType = buildType;
    }

    public String getCertNum() {
        return certNum;
    }

    public void setCertNum(String certNum) {
        this.certNum = certNum;
    }

    public int getCertType() {
        return certType;
    }

    public void setCertType(int certType) {
        this.certType = certType;
    }
}
