package com.jdp.hls.model.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 2:12
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Employee implements Serializable {
    private int EmployeeId;
    private int CompanyId;
    private String RealName;
    private String HeadUrl;
    private String MobilePhone;
    private long AccountName;
    private String AccountAlias;
    private String LoginName;
    private String CompanyName;
    private boolean IsCompanyAccount;
    private boolean selected;
    private boolean IsStop;
    private boolean IsManageAllProjects;
    private String ProjectIDs;
    private boolean AreaVisible;
    private List<AreaSupervise>AreaList;

    public List<AreaSupervise> getAreaList() {
        return AreaList;
    }

    public void setAreaList(List<AreaSupervise> areaList) {
        AreaList = areaList;
    }


    public boolean isAreaVisible() {
        return AreaVisible;
    }

    public void setAreaVisible(boolean areaVisible) {
        AreaVisible = areaVisible;
    }

    public boolean isStop() {
        return IsStop;
    }

    public void setStop(boolean stop) {
        IsStop = stop;
    }

    public boolean isManageAllProjects() {
        return IsManageAllProjects;
    }

    public void setManageAllProjects(boolean manageAllProjects) {
        IsManageAllProjects = manageAllProjects;
    }

    public String getProjectIDs() {
        return null == ProjectIDs ? "" : ProjectIDs;
    }

    public void setProjectIDs(String projectIDs) {
        ProjectIDs = projectIDs;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getHeadUrl() {
        return HeadUrl;
    }

    public void setHeadUrl(String headUrl) {
        HeadUrl = headUrl;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public long getAccountName() {
        return AccountName;
    }

    public void setAccountName(long accountName) {
        AccountName = accountName;
    }

    public String getAccountAlias() {
        return AccountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        AccountAlias = accountAlias;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public boolean isCompanyAccount() {
        return IsCompanyAccount;
    }

    public void setCompanyAccount(boolean companyAccount) {
        IsCompanyAccount = companyAccount;
    }


    @Override
    public int hashCode() {
        return this.EmployeeId;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee employee = (Employee) obj;
        return this.EmployeeId == employee.getEmployeeId();
    }
}
