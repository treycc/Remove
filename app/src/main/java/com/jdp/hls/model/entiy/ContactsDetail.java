package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2019/2/1 0001 上午 9:43
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsDetail {
    private String RealName;
    private boolean Gender;
    private boolean IsAllowEdit;
    private String MobilePhone;
    private String Idcard;
    private int PoliticalTitle;
    private String PoliticalTitleDescription;
    private String PersonId;

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public String getMobilePhone() {
        return null == MobilePhone ? "" : MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public String getPoliticalTitleDescription() {
        return null == PoliticalTitleDescription ? "" : PoliticalTitleDescription;
    }

    public void setPoliticalTitleDescription(String politicalTitleDescription) {
        PoliticalTitleDescription = politicalTitleDescription;
    }

    public String getPersonId() {
        return null == PersonId ? "" : PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public String getRealName() {
        return null == RealName ? "" : RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public boolean isGender() {
        return Gender;
    }

    public void setGender(boolean gender) {
        Gender = gender;
    }


    public String getIdcard() {
        return null == Idcard ? "" : Idcard;
    }

    public void setIdcard(String idcard) {
        Idcard = idcard;
    }

    public int getPoliticalTitle() {
        return PoliticalTitle;
    }

    public void setPoliticalTitle(int politicalTitle) {
        PoliticalTitle = politicalTitle;
    }
}
