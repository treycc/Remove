package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/30 0030 下午 2:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectSuperviseDetail {
    private String ProjectId;
    private String ProjectName;
    private String Address;
    private List<CompanySupervise> LstProjectCompany;

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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public List<CompanySupervise> getLstProjectCompany() {
        return LstProjectCompany;
    }

    public void setLstProjectCompany(List<CompanySupervise> lstProjectCompany) {
        LstProjectCompany = lstProjectCompany;
    }
}
