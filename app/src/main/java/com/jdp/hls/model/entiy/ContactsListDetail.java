package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/1/30 0030 下午 2:53
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsListDetail {
    private boolean IsAllowEdit;
    private List<ContactsItem> PersonList;

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public List<ContactsItem> getPersonList() {
        return PersonList;
    }

    public void setPersonList(List<ContactsItem> personList) {
        PersonList = personList;
    }
}
