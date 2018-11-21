package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/21 0021 下午 5:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GroupDetail {

    private String Remark;
    private String GroupName;
    private List<Member> LstMember;

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public List<Member> getLstMember() {
        return LstMember;
    }

    public void setLstMember(List<Member> LstMember) {
        this.LstMember = LstMember;
    }

}
