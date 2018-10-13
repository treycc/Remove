package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/11 0011 下午 1:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FamilyRelation {

    private boolean IsAllowEdit;
    private List<FamilyMember> LstPerons;
    private List<ImgInfo> Files;

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public List<FamilyMember> getLstPerons() {
        return LstPerons;
    }

    public void setLstPerons(List<FamilyMember> lstPerons) {
        LstPerons = lstPerons;
    }

    public List<ImgInfo> getFiles() {
        return Files;
    }

    public void setFiles(List<ImgInfo> files) {
        Files = files;
    }
}
