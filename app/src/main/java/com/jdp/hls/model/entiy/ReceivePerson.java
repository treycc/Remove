package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/10/16 0016 上午 9:14
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReceivePerson {

    /**
     * StatusId : 0
     * EmployeeId : 0
     * RealName :
     */

    private int StatusId;
    private int EmployeeId;
    private String RealName;
    private String StatusDesc;

    public String getStatusDesc() {
        return StatusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        StatusDesc = statusDesc;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int StatusId) {
        this.StatusId = StatusId;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }
}
