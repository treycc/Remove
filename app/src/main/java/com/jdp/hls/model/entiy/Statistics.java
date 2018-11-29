package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/29 0029 下午 4:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Statistics {
    private String Name;
    private int Quantity;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public Statistics(String name, int quantity) {
        Name = name;
        Quantity = quantity;
    }
}
