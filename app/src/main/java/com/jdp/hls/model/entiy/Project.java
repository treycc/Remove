package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 上午 10:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Project implements Serializable {

    private String ProjectId;
    private String ProjectName;
    private int Year;
    private String Address;
    private String RealName;
    private int TotalQuantity;
    private String PercentDesc;

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getPercentDesc() {
        return null == PercentDesc ? "" : PercentDesc;
    }

    public void setPercentDesc(String percentDesc) {
        PercentDesc = percentDesc;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void setProjectId(String ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int Year) {
        this.Year = Year;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    @Override
    public int hashCode() {
        return this.ProjectId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Project)) {
            return false;
        }
        Project project = (Project) obj;
        return this.ProjectId .equals(project.getProjectId()) ;
    }
}
