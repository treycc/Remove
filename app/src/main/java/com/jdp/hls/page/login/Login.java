package com.jdp.hls.page.login;

/**
 * Description:TODO
 * Create Time:2018/8/2 0002 下午 3:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Login {

    private String Token;
    private String RefreshToken;
    private String CompanyName;
    private String RealName;
    private String HeadUrl;
    private String MobilePhone;
    private String AccountAlias;
    private int CompanyId;
    private int AccountName;

    public String getRefreshToken() {
        return RefreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.RefreshToken = refreshToken;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String token) {
        this.Token = token;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
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

    public String getAccountAlias() {
        return AccountAlias;
    }

    public void setAccountAlias(String accountAlias) {
        AccountAlias = accountAlias;
    }

    public int getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(int companyId) {
        CompanyId = companyId;
    }

    public int getAccountName() {
        return AccountName;
    }

    public void setAccountName(int accountName) {
        AccountName = accountName;
    }
}
