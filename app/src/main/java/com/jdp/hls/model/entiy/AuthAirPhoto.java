package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/10/17 0017 下午 3:39
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AuthAirPhoto {

    /**
     * IsAllowSend : true
     * IsAllowCheck : true
     * IsAllowCloseFinished : false
     * IsAllowEdit : true
     */

    private boolean IsAllowSend;
    private boolean IsAllowCheck;
    private boolean IsAllowCloseFinished;
    private boolean IsAllowEditAppraise;
    private boolean IsAllowEdit;

    public boolean isAllowSend() {
        return IsAllowSend;
    }

    public void setAllowSend(boolean allowSend) {
        IsAllowSend = allowSend;
    }

    public boolean isAllowCheck() {
        return IsAllowCheck;
    }

    public void setAllowCheck(boolean allowCheck) {
        IsAllowCheck = allowCheck;
    }

    public boolean isAllowCloseFinished() {
        return IsAllowCloseFinished;
    }

    public void setAllowCloseFinished(boolean allowCloseFinished) {
        IsAllowCloseFinished = allowCloseFinished;
    }

    public boolean isAllowEditAppraise() {
        return IsAllowEditAppraise;
    }

    public void setAllowEditAppraise(boolean allowEditAppraise) {
        IsAllowEditAppraise = allowEditAppraise;
    }

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }
}
