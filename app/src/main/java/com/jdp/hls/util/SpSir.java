package com.jdp.hls.util;

import android.content.SharedPreferences;

import com.jdp.hls.base.App;


/**
 * Description：TODO
 * Create Time：2016/8/15 13:51
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SpSir {
    private String Token = "Token";
    private String CompanyName = "CompanyName";
    private String CompanyId = "CompanyId";
    private String HeadUrl = "HeadUrl";
    private String EmployeeId = "EmployeeId";
    private String MobilePhone = "MobilePhone";
    private String AccountName = "AccountName";
    private String AccountAlias = "AccountAlias";
    private String RealName = "RealName";
    private String Projects = "Projects";
    private String ProjectId = "ProjectId";
    private String ProjectName = "ProjectName";
    private String IfRememberBaby = "IfRememberBaby";
    private String ComeOnBaby = "ComeOnBaby";
    private String ServerName = "ServerName";
    private String ProtocolUrl = "ProtocolUrl";
    private String UserName = "UserName";
    private static final String EMPTY_STRING = "";
    private static final int ZERO_INT = -1;
    private static SpSir mSpSir;
    private SharedPreferences mSp;

    private SpSir() {
        mSp = App.getSp();
    }

    public static SpSir getInstance() {
        if (mSpSir == null) {
            synchronized (SpSir.class) {
                if (mSpSir == null) {
                    mSpSir = new SpSir();
                }
            }
        }
        return mSpSir;
    }

    public String getToken() {
        return getString(Token);
    }

    public void setToken(String token) {
        putString(Token, token);
    }

    public String getCompanyName() {
        return getString(CompanyName);
    }

    public void setCompanyName(String companyName) {
        putString(CompanyName, companyName);
    }

    public int getCompanyId() {
        return getInt(CompanyId);
    }

    public void setCompanyId(int companyId) {
        putInt(CompanyId, companyId);
    }

    public String getHeadUrl() {
        return getString(HeadUrl);
    }

    public void setHeadUrl(String headUrl) {
        putString(HeadUrl, headUrl);
    }

    public int getEmployeeId() {
        return getInt(EmployeeId);
    }

    public void setEmployeeId(int employeeId) {
        putInt(EmployeeId, employeeId);
    }

    public String getMobilePhone() {
        return getString(MobilePhone);
    }

    public void setMobilePhone(String mobilePhone) {
        putString(MobilePhone, mobilePhone);
    }

    public int getAccountName() {
        return getInt(AccountName);
    }

    public void setAccountName(int accountName) {
        putInt(AccountName, accountName);
    }

    public String getAccountAlias() {
        return getString(AccountAlias);
    }

    public void setAccountAlias(String accountAlias) {
        putString(AccountAlias, accountAlias);
    }

    public String getRealName() {
        return getString(RealName);
    }

    public void setRealName(String realName) {
        putString(RealName, realName);
    }

    public String getProjects() {
        return getString(Projects);
    }

    public void setProjects(String projects) {
        putString(Projects, projects);
    }

    public String getProjectId() {
        return getString(ProjectId);
    }

    public void setProjectId(String projectId) {
        putString(ProjectId, projectId);
    }

    public String getProjectName() {
        return getString(ProjectName);
    }

    public void setProjectName(String projectName) {
        putString(ProjectName, projectName);
    }


    public String getComeOnBaby() {
        return getString(ComeOnBaby);
    }

    public void setComeOnBaby(String comeOnBaby) {
        putString(ComeOnBaby, comeOnBaby);
    }

    public String getServerName() {
        return getString(ServerName);
    }

    public void setServerName(String serverName) {
        putString(ServerName, serverName);
    }

    public String getProtocolUrl() {
        return getString(ProtocolUrl);
    }

    public void setProtocolUrl(String protocolUrl) {
        putString(ProtocolUrl, protocolUrl);
    }  public String getUserName() {
        return getString(UserName);
    }

    public void setUserName(String userName) {
        putString(UserName, userName);
    }

    public boolean getIfRememberBaby() {
        return getBoolean(IfRememberBaby, false);
    }

    public void setIfRememberBaby(boolean ifRememberBaby) {
        putboolean(IfRememberBaby, ifRememberBaby);
    }

    private String getString(String key, String defaultValue) {
        return mSp.getString(key, defaultValue);
    }

    private String getString(String key) {
        return mSp.getString(key, EMPTY_STRING);
    }

    private void putString(String key, String value) {
        if (value != null) {
            mSp.edit().putString(key, value).apply();
        }
    }

    private int getInt(String key) {
        return mSp.getInt(key, ZERO_INT);
    }

    private int getInt(String key, int defaultValue) {
        return mSp.getInt(key, defaultValue);
    }

    private void putInt(String key, int value) {
        mSp.edit().putInt(key, value).apply();
    }

    private boolean getBoolean(String key, boolean defaultValue) {
        return mSp.getBoolean(key, defaultValue);
    }

    private void putboolean(String key, boolean value) {
        mSp.edit().putBoolean(key, value).apply();
    }

    public void clearData() {
    }
}
