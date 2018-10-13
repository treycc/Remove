package com.jdp.hls.event;

import com.jdp.hls.model.entiy.FamilyMember;

/**
 * Description:TODO
 * Create Time:2018/10/13 0013 上午 10:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddFamilyMememberEvent {
    private FamilyMember familyMember;

    public FamilyMember getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }

    public AddFamilyMememberEvent(FamilyMember familyMember) {
        this.familyMember = familyMember;
    }
}
