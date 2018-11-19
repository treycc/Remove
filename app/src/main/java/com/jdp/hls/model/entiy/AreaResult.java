package com.jdp.hls.model.entiy;

import com.jdp.hls.greendaobean.Area;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 7:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaResult {
    private String UpdateTime;
    private List<Area> Area;

    public String getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(String updateTime) {
        UpdateTime = updateTime;
    }

    public List<Area> getArea() {
        return Area;
    }

    public void setArea(List<Area> area) {
        this.Area = area;
    }
}
