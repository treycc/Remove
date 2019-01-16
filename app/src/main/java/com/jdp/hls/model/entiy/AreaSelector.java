package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2019/1/16 0016 下午 4:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSelector {

    /**
     * AreaLevel : 3
     * IsSelect : true
     * AreaNumber : -1
     * AreaName :
     */

    private int AreaLevel;
    private boolean IsAvailable;
    private int AreaNumber;
    private String AreaName;

    public boolean isAvailable() {
        return IsAvailable;
    }

    public void setAvailable(boolean available) {
        IsAvailable = available;
    }

    public int getAreaLevel() {
        return AreaLevel;
    }

    public void setAreaLevel(int AreaLevel) {
        this.AreaLevel = AreaLevel;
    }

    public int getAreaNumber() {
        return AreaNumber;
    }

    public void setAreaNumber(int AreaNumber) {
        this.AreaNumber = AreaNumber;
    }

    public String getAreaName() {
        return AreaName;
    }

    public void setAreaName(String AreaName) {
        this.AreaName = AreaName;
    }
}
