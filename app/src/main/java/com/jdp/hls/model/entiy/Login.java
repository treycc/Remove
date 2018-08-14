package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/2 0002 下午 3:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Login {

    /**
     * Token : e48fb5db1eb94d2c852c608c24568538
     * UserInfo : {"EmployeeId":1,"CompanyId":1,"CompanyName":"a","RealName":"","HeadUrl":"","MobilePhone":"",
     * "AccountName":0,"AccountAlias":""}
     * Projects : [{"ProjectId":"e10adc39-49ba-59ab-be56-e057f20f873a","ProjectName":"5","Year":34,"Address":"1"},
     * {"ProjectId":"e10adc39-49ba-59ab-be56-e057f20f883a","ProjectName":"4","Year":4,"Address":"1"},
     * {"ProjectId":"e10adc39-49ba-59ab-be56-e057f20f883e","ProjectName":"2","Year":3,"Address":"1"}]
     */

    private String Token;
    private UserInfo UserInfo;
    private List<Project> Projects;

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public UserInfo getUserInfo() {
        return UserInfo;
    }

    public void setUserInfo(UserInfo UserInfo) {
        this.UserInfo = UserInfo;
    }

    public List<Project> getProjects() {
        return Projects;
    }

    public void setProjects(List<Project> Projects) {
        this.Projects = Projects;
    }

}
