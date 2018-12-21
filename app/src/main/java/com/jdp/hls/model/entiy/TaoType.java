package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/12/17 0017 下午 1:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */

public class TaoType implements Serializable {
    private int Id;
    private String PatternName;
    private String Area;
    private String Remark;
    private int Quantity;

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getPatternName() {
        return null == PatternName ? "" : PatternName;
    }

    public void setPatternName(String patternName) {
        PatternName = patternName;
    }

    public String getArea() {
        return null == Area ? "" : Area;
    }

    public void setArea(String area) {
        Area = area;
    }

    public String getRemark() {
        return null == Remark ? "" : Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
