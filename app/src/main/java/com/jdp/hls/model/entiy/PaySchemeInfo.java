package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2019/1/4 0004 上午 9:17
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PaySchemeInfo {

    /**
     * ProjectId : string,项目编号
     * InPolicyBuyMoreAmount : 1.0
     * NewHouseValuationPrice : 1.0
     * CollectBuyBackPrice : 1.0
     */

    private String ProjectId;
    private double InPolicyBuyMoreAmount;
    private double NewHouseValuationPrice;
    private double CollectBuyBackPrice;

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }

    public double getInPolicyBuyMoreAmount() {
        return InPolicyBuyMoreAmount;
    }

    public void setInPolicyBuyMoreAmount(double InPolicyBuyMoreAmount) {
        this.InPolicyBuyMoreAmount = InPolicyBuyMoreAmount;
    }

    public double getNewHouseValuationPrice() {
        return NewHouseValuationPrice;
    }

    public void setNewHouseValuationPrice(double NewHouseValuationPice) {
        this.NewHouseValuationPrice = NewHouseValuationPice;
    }

    public double getCollectBuyBackPrice() {
        return CollectBuyBackPrice;
    }

    public void setCollectBuyBackPrice(double CollectBuyBackPrice) {
        this.CollectBuyBackPrice = CollectBuyBackPrice;
    }
}
