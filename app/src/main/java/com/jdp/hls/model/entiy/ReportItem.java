package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/29 0029 下午 3:27
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportItem {

    /**
     * FinishedTime : string,完成时间段（比如：2018-12:01 08:00:00到2018-12:01 12:00:00）
     * DisplayTime : string,时段（FinishedTime 的显示时间段）
     * Time : string,用时
     * Quantity : string,数量
     * TimeAvg : string,平均耗时
     * Speed : string,速度
     */

    private String FinishedDate;
    private String DisplayTime;
    private String Time;
    private String Quantity;
    private String TimeAvg;
    private String Speed;

    public String getFinishedDate() {
        return FinishedDate;
    }

    public void setFinishedDate(String FinishedDate) {
        this.FinishedDate = FinishedDate;
    }

    public String getDisplayTime() {
        return DisplayTime;
    }

    public void setDisplayTime(String DisplayTime) {
        this.DisplayTime = DisplayTime;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String Quantity) {
        this.Quantity = Quantity;
    }

    public String getTimeAvg() {
        return TimeAvg;
    }

    public void setTimeAvg(String TimeAvg) {
        this.TimeAvg = TimeAvg;
    }

    public String getSpeed() {
        return Speed;
    }

    public void setSpeed(String Speed) {
        this.Speed = Speed;
    }
}
