package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/12/7 0007 上午 10:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportTitle {
    private String FinishedDate;
    private String DisplayName;

    public String getFinishedDate() {
        return null == FinishedDate ? "" : FinishedDate;
    }

    public void setFinishedDate(String finishedDate) {
        FinishedDate = finishedDate;
    }

    public String getDisplayName() {
        return null == DisplayName ? "" : DisplayName;
    }

    public void setDisplayName(String displayName) {
        DisplayName = displayName;
    }
}
