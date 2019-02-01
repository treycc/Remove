package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2019/1/28 0028 下午 3:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsItem {
    private String RealName;
    private String PersonId;
    private String MobilePhone;
    private int IsMainContact;

    public String getRealName() {
        return null == RealName ? "" : RealName;
    }

    public void setRealName(String realName) {
        RealName = realName;
    }

    public String getPersonId() {
        return null == PersonId ? "" : PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public String getMobilePhone() {
        return null == MobilePhone ? "" : MobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        MobilePhone = mobilePhone;
    }

    public int getIsMainContact() {
        return IsMainContact;
    }

    public void setIsMainContact(int isMainContact) {
        IsMainContact = isMainContact;
    }
}
