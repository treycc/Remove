package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/30 0030 下午 2:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectSuperviseInfo {
    private PageInfo BasePagerOutput;
    private List<ProjectSupervise> ResultList;

    public PageInfo getBasePagerOutput() {
        return BasePagerOutput;
    }

    public void setBasePagerOutput(PageInfo basePagerOutput) {
        BasePagerOutput = basePagerOutput;
    }

    public List<ProjectSupervise> getResultList() {
        return ResultList;
    }

    public void setResultList(List<ProjectSupervise> resultList) {
        ResultList = resultList;
    }
}
