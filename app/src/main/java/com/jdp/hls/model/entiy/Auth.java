package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/9/27 0027 上午 10:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Auth {
    private boolean IsAllowSend;
    private boolean IsAllowBanned;
    private boolean IsAllowReview;
    private boolean IsAllowFlowBack;

    public boolean isAllowSend() {
        return IsAllowSend;
    }

    public void setAllowSend(boolean allowSend) {
        IsAllowSend = allowSend;
    }

    public boolean isAllowBanned() {
        return IsAllowBanned;
    }

    public void setAllowBanned(boolean allowBanned) {
        IsAllowBanned = allowBanned;
    }

    public boolean isAllowReview() {
        return IsAllowReview;
    }

    public void setAllowReview(boolean allowReview) {
        IsAllowReview = allowReview;
    }

    public boolean isAllowFlowBack() {
        return IsAllowFlowBack;
    }

    public void setAllowFlowBack(boolean allowFlowBack) {
        IsAllowFlowBack = allowFlowBack;
    }
}
