package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/11/20 0020 下午 7:58
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Company implements Serializable {

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

    @Override
    public int hashCode() {
        return this.CompanyId;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Company)) {
            return false;
        }
        Company company = (Company) obj;
        return this.CompanyId == company.getCompanyId();
    }
}
