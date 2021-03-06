package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/8/9 0009 下午 4:18
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UserInfo {

    private int EmployeeId;
    private int CompanyId;
    private String CompanyName;
    private String RealName;
    private String HeadUrl;
    private String MobilePhone;
    private long AccountName;
    private String AccountType;
    private String AccountAlias;
    private boolean IsOperatorAccount;
    private boolean IsAllowDistributeProjects;

    public boolean isAllowDistributeProjects() {
        return IsAllowDistributeProjects;
    }

    public void setAllowDistributeProjects(boolean allowDistributeProjects) {
        IsAllowDistributeProjects = allowDistributeProjects;
    }

    public boolean isOperatorAccount() {
        return IsOperatorAccount;
    }

    public void setOperatorAccount(boolean operatorAccount) {
        IsOperatorAccount = operatorAccount;
    }

    public String getAccountType() {
        return AccountType;
    }

    public void setAccountType(String accountType) {
        AccountType = accountType;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getHeadUrl() {
        return HeadUrl;
    }

    public void setHeadUrl(String HeadUrl) {
        this.HeadUrl = HeadUrl;
    }

    public String getMobilePhone() {
        return MobilePhone;
    }

    public void setMobilePhone(String MobilePhone) {
        this.MobilePhone = MobilePhone;
    }

    public long getAccountName() {
        return AccountName;
    }

    public void setAccountName(long AccountName) {
        this.AccountName = AccountName;
    }

    public String getAccountAlias() {
        return AccountAlias;
    }

    public void setAccountAlias(String AccountAlias) {
        this.AccountAlias = AccountAlias;
    }
}
