package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherArea implements Serializable{
    private int Id;
    private int PCId;
    private String Name;
    private double Price;
    private double Area;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getPCId() {
        return PCId;
    }

    public void setPCId(int PCId) {
        this.PCId = PCId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public double getArea() {
        return Area;
    }

    public void setArea(double area) {
        Area = area;
    }
}
