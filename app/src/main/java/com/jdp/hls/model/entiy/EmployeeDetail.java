package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 2:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EmployeeDetail {

    private PageInfo BasePagerOutput;
    private List<Employee> ResultList;

    public PageInfo getBasePagerOutput() {
        return BasePagerOutput;
    }

    public void setBasePagerOutput(PageInfo BasePagerOutput) {
        this.BasePagerOutput = BasePagerOutput;
    }

    public List<Employee> getResultList() {
        return ResultList;
    }

    public void setResultList(List<Employee> ResultList) {
        this.ResultList = ResultList;
    }
}
