package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/12/7 0007 上午 9:47
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportHourResult {
    private String Name;
    private List<ReportTitle> Titles;
    private List<ReportItem> ReportDataList;

    public String getName() {
        return null == Name ? "" : Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<ReportTitle> getTitles() {
        return Titles;
    }

    public void setTitles(List<ReportTitle> titles) {
        Titles = titles;
    }

    public List<ReportItem> getReportDataList() {
        return ReportDataList;
    }

    public void setReportDataList(List<ReportItem> reportDataList) {
        ReportDataList = reportDataList;
    }
}
