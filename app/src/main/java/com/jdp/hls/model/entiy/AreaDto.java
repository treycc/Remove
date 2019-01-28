package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2019/1/24 0024 上午 10:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaDto {
    private int Level;
    private int RegionId;

    public AreaDto(int level, int regionId) {
        Level = level;
        RegionId = regionId;
    }

    public int getLevel() {
        return Level;
    }

    public void setLevel(int level) {
        Level = level;
    }

    public int getRegionId() {
        return RegionId;
    }

    public void setRegionId(int regionId) {
        RegionId = regionId;
    }
}
