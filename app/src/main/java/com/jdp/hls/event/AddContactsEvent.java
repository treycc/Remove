package com.jdp.hls.event;

import com.jdp.hls.model.entiy.ContactsItem;

/**
 * Description:TODO
 * Create Time:2019/2/1 0001 下午 1:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AddContactsEvent {
    private ContactsItem contactsItem;

    public AddContactsEvent(ContactsItem contactsItem) {
        this.contactsItem = contactsItem;
    }

    public ContactsItem getContactsItem() {
        return contactsItem;
    }

    public void setContactsItem(ContactsItem contactsItem) {
        this.contactsItem = contactsItem;
    }
}
