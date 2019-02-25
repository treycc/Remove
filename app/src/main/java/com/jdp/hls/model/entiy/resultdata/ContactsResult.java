package com.jdp.hls.model.entiy.resultdata;

/**
 * Description:TODO
 * Create Time:2019/2/1 0001 下午 1:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsResult {
    private String PersonId;
    private int IsMainContact;

    public String getPersonId() {
        return null == PersonId ? "" : PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public int getIsMainContact() {
        return IsMainContact;
    }

    public void setIsMainContact(int isMainContact) {
        IsMainContact = isMainContact;
    }
}
