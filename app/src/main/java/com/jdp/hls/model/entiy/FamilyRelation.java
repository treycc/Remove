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
    private int BookletId;
    private String BookletNum;
    private String HouseId;

    public int getBookletId() {
        return BookletId;
    }

    public void setBookletId(int bookletId) {
        BookletId = bookletId;
    }

    public String getBookletNum() {
        return BookletNum;
    }

    public void setBookletNum(String bookletNum) {
        BookletNum = bookletNum;
    }

    public String getHouseId() {
        return HouseId;
    }

    public void setHouseId(String houseId) {
        HouseId = houseId;
    }

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
