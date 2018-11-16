package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 1:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Company {

    /**
     * CompanyId : 1
     * CompanyTypeID : 2
     * CompanyTypeName : 事务单位
     * CompanyName : 温州民生拆迁
     * CompanyCode : aaaaaa
     * AccountCode : 1
     */

    private int CompanyId;
    private int CompanyTypeID;
    private String CompanyTypeName;
    private String CompanyName;
    private String CompanyCode;
    private long AccountCode;

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int CompanyId) {
        this.CompanyId = CompanyId;
    }

    public int getCompanyTypeID() {
        return CompanyTypeID;
    }

    public void setCompanyTypeID(int CompanyTypeID) {
        this.CompanyTypeID = CompanyTypeID;
    }

    public String getCompanyTypeName() {
        return CompanyTypeName;
    }

    public void setCompanyTypeName(String CompanyTypeName) {
        this.CompanyTypeName = CompanyTypeName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
    }

    public String getCompanyCode() {
        return CompanyCode;
    }

    public void setCompanyCode(String CompanyCode) {
        this.CompanyCode = CompanyCode;
    }

    public long getAccountCode() {
        return AccountCode;
    }

    public void setAccountCode(long AccountCode) {
        this.AccountCode = AccountCode;
    }
}
