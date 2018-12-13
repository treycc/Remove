package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/12/7 0007 上午 9:47
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportDayResult {
    private String Name;
    private List<ReportTitle> Titles;
    private ReportListInfo ReportDataList;

    public static class ReportListInfo {
        private PageInfo BasePagerOutput;
        private List<ReportItem> ResultList;

        public PageInfo getBasePagerOutput() {
            return BasePagerOutput;
        }

        public void setBasePagerOutput(PageInfo basePagerOutput) {
            BasePagerOutput = basePagerOutput;
        }

        public List<ReportItem> getResultList() {
            return ResultList;
        }

        public void setResultList(List<ReportItem> resultList) {
            ResultList = resultList;
        }
    }

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

    public ReportListInfo getReportDataList() {
        return ReportDataList;
    }

    public void setReportDataList(ReportListInfo reportDataList) {
        ReportDataList = reportDataList;
    }
}
