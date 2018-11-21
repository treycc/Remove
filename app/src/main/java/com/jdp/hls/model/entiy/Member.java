package com.jdp.hls.model.entiy;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/11/21 0021 下午 6:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Member implements Serializable{

    /**
     * EmployeeId : 1
     * AccountType : 10
     * AccountTypeName : 经办人账号
     * RealName : 杰森2
     * MarkedWords : 请选择经办人账号
     * CompanyTypeId : 2
     * CompanyTypeName : 事务单位
     */

    private int EmployeeId;
    private int AccountType;
    private String AccountTypeName;
    private String RealName;
    private String MarkedWords;
    private int CompanyTypeId;
    private String CompanyTypeName;

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int EmployeeId) {
        this.EmployeeId = EmployeeId;
    }

    public int getAccountType() {
        return AccountType;
    }

    public void setAccountType(int AccountType) {
        this.AccountType = AccountType;
    }

    public String getAccountTypeName() {
        return AccountTypeName;
    }

    public void setAccountTypeName(String AccountTypeName) {
        this.AccountTypeName = AccountTypeName;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getMarkedWords() {
        return MarkedWords;
    }

    public void setMarkedWords(String MarkedWords) {
        this.MarkedWords = MarkedWords;
    }

    public int getCompanyTypeId() {
        return CompanyTypeId;
    }

    public void setCompanyTypeId(int CompanyTypeId) {
        this.CompanyTypeId = CompanyTypeId;
    }

    public String getCompanyTypeName() {
        return CompanyTypeName;
    }

    public void setCompanyTypeName(String CompanyTypeName) {
        this.CompanyTypeName = CompanyTypeName;
    }
}
