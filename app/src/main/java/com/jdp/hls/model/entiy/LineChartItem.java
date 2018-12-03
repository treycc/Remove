package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/12/3 0003 上午 9:46
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LineChartItem {
    private String Time;
    private int Timestamp;
    private int Quantity;

    public String getTime() {
        return null == Time ? "" : Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(int timestamp) {
        Timestamp = timestamp;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }
}
