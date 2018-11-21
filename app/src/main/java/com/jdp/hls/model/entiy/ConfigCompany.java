package com.jdp.hls.model.entiy;

import android.text.TextUtils;

import java.io.Serializable;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 1:37
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ConfigCompany implements Serializable {

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
    private String CompanyTypeIDS;
    private int CompanyTypeID;
    private String CompanyTypeName;
    private String MarkedWords;
    private int Sort;

    public String getCompanyTypeIDS() {
        if (TextUtils.isEmpty(CompanyId)) {
            return String.valueOf(CompanyTypeID);
        } else {
            StringBuilder sb = new StringBuilder();
            String[] companyIdsAttr = CompanyId.split("#");
            for (int i = 0; i < companyIdsAttr.length; i++) {
                if (i != companyIdsAttr.length - 1) {
                    sb.append(CompanyTypeID);
                    sb.append("#");
                } else {
                    sb.append(CompanyTypeID);
                }
            }
            return sb.toString();

        }
    }

    public void setCompanyTypeIDS(String companyTypeIDS) {
        CompanyTypeIDS = companyTypeIDS;
    }

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
