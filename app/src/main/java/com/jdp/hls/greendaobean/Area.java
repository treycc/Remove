package com.jdp.hls.greendaobean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 7:24
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
@Entity
public class Area {
    @Id
    private Long RegionId;
    private String RegionName;
    private int ParentId;
    private int Level;
    @Generated(hash = 550826081)
    public Area(Long RegionId, String RegionName, int ParentId, int Level) {
        this.RegionId = RegionId;
        this.RegionName = RegionName;
        this.ParentId = ParentId;
        this.Level = Level;
    }
    @Generated(hash = 179626505)
    public Area() {
    }
    public Long getRegionId() {
        return this.RegionId;
    }
    public void setRegionId(Long RegionId) {
        this.RegionId = RegionId;
    }
    public String getRegionName() {
        return this.RegionName;
    }
    public void setRegionName(String RegionName) {
        this.RegionName = RegionName;
    }
    public int getParentId() {
        return this.ParentId;
    }
    public void setParentId(int ParentId) {
        this.ParentId = ParentId;
    }
    public int getLevel() {
        return this.Level;
    }
    public void setLevel(int Level) {
        this.Level = Level;
    }
    
}
