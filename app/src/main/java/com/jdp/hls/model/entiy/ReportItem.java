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
        return null == FinishedDate ? "" : FinishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        FinishedDate = finishedDate;
    }

    public String getDisplayTime() {
        return null == DisplayTime ? "" : DisplayTime;
    }

    public void setDisplayTime(String displayTime) {
        DisplayTime = displayTime;
    }

    public String getTime() {
        return null == Time ? "" : Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getQuantity() {
        return null == Quantity ? "" : Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getTimeAvg() {
        return null == TimeAvg ? "" : TimeAvg;
    }

    public void setTimeAvg(String timeAvg) {
        TimeAvg = timeAvg;
    }

    public String getSpeed() {
        return null == Speed ? "" : Speed;
    }

    public void setSpeed(String speed) {
        Speed = speed;
    }
}
