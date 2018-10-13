package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/13 0013 下午 4:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsDetail {

    /**
     * TotalQuantity : 264
     * ProjectName : 环一村
     * LstStatis : [{"StatisType":1,"StatisTypeName":"未丈量数","Quantity":0,"Percent":0,"PercentDesc":"0%"},
     * {"StatisType":1,"StatisTypeName":"已出图数","Quantity":0,"Percent":0,"PercentDesc":"0%"},{"StatisType":1,
     * "StatisTypeName":"已丈量未出图","Quantity":0,"Percent":0,"PercentDesc":"0%"}]
     */

    private int TotalQuantity;
    private String ProjectName;
    private List<StatisticsItem> LstStatis;

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int TotalQuantity) {
        this.TotalQuantity = TotalQuantity;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public List<StatisticsItem> getLstStatis() {
        return LstStatis;
    }

    public void setLstStatis(List<StatisticsItem> lstStatis) {
        LstStatis = lstStatis;
    }
}
