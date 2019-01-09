package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/10/15 0015 下午 2:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessAction {
    /**
     * ActionId : 1
     * ActionName : 花名册
     * Sort : 0
     * IsAvailable : true
     */

    private int ActionId;
    private String ActionName;
    private String ActionPicUrl;
    private int Sort;
    private int ActionType;
    private int StatisId;
    private boolean IsAvailable;

    public String getActionPicUrl() {
        return null == ActionPicUrl ? "" : ActionPicUrl;
    }

    public void setActionPicUrl(String actionPicUrl) {
        ActionPicUrl = actionPicUrl;
    }

    public int getStatisId() {
        return StatisId;
    }

    public void setStatisId(int statisId) {
        StatisId = statisId;
    }

    public boolean isAvailable() {
        return IsAvailable;
    }

    public void setAvailable(boolean available) {
        IsAvailable = available;
    }

    public int getActionType() {
        return ActionType;
    }

    public void setActionType(int actionType) {
        ActionType = actionType;
    }

    public int getActionId() {
        return ActionId;
    }

    public void setActionId(int ActionId) {
        this.ActionId = ActionId;
    }

    public String getActionName() {
        return ActionName;
    }

    public void setActionName(String ActionName) {
        this.ActionName = ActionName;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int Sort) {
        this.Sort = Sort;
    }

    public boolean isIsAvailable() {
        return IsAvailable;
    }

    public void setIsAvailable(boolean IsAvailable) {
        this.IsAvailable = IsAvailable;
    }
}
