package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 1:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ConfigCompany {

    /**
     * CompanyId :
     * CompanyName :
     * CompanyTypeID : 8
     * CompanyTypeName : 房地产评估机构
     * MarkedWords : 请选择房地产评估机构
     * Sort : 40
     */

    private String CompanyId;
    private String CompanyName;
    private int CompanyTypeID;
    private String CompanyTypeName;
    private String MarkedWords;
    private int Sort;

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String CompanyId) {
        this.CompanyId = CompanyId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String CompanyName) {
        this.CompanyName = CompanyName;
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

    public String getMarkedWords() {
        return MarkedWords;
    }

    public void setMarkedWords(String MarkedWords) {
        this.MarkedWords = MarkedWords;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int Sort) {
        this.Sort = Sort;
    }
}
