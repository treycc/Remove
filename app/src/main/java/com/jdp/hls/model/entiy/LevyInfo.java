package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 2:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LevyInfo {
    private List<Task> LstTaskTypeCount;
    private List<BusinessAction> LstAppAction;
    private List<BusinessAction> LstStatisAction;

    public List<Task> getLstTaskTypeCount() {
        return LstTaskTypeCount;
    }

    public void setLstTaskTypeCount(List<Task> lstTaskTypeCount) {
        LstTaskTypeCount = lstTaskTypeCount;
    }

    public List<BusinessAction> getLstAppAction() {
        return LstAppAction;
    }

    public void setLstAppAction(List<BusinessAction> lstAppAction) {
        LstAppAction = lstAppAction;
    }

    public List<BusinessAction> getLstStatisAction() {
        return LstStatisAction;
    }

    public void setLstStatisAction(List<BusinessAction> lstStatisAction) {
        LstStatisAction = lstStatisAction;
    }
}
