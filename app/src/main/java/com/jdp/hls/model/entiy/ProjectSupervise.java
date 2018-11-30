package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/27 0027 下午 1:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectSupervise {

    private String ProjectId;
    private String ProjectName;
    private String ProjectSysCode;
    private String Address;
    private int TotalQuantity;
    private String PercentDesc;

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String projectId) {
        ProjectId = projectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String projectName) {
        ProjectName = projectName;
    }

    public String getProjectSysCode() {
        return ProjectSysCode;
    }

    public void setProjectSysCode(String projectSysCode) {
        ProjectSysCode = projectSysCode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getPercentDesc() {
        return PercentDesc;
    }

    public void setPercentDesc(String percentDesc) {
        PercentDesc = percentDesc;
    }
}
