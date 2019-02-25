package com.jdp.hls.page.rosterdetail.contacts.list;


import android.provider.ContactsContract;

import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ContactsItem;
import com.jdp.hls.model.entiy.ContactsListDetail;
import com.jdp.hls.model.entiy.resultdata.ContactsResult;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ContactsListContract {
    interface View extends BaseView {
        void onGetMainPersonListSuccess(ContactsListDetail contactsDetail);

        void onDeleteContactsSuccess(int position);

        void onSetMainContactsSuccess(int position);

        void onImportMainContactsSuccess(ContactsResult contactsResult, ContactsItem contactsItem);
    }

    interface Presenter extends BasePresenter<View> {
        void getMainPersonList(String houseId, int buildingType);

        void deleteContacts(String houseId, String personId, int buildingType, int position);

        void setMainContacts(String houseId, String personId, int buildingType, int position);

        void importMainContacts(String houseId, String personId, int buildingType, ContactsItem contactsItem);
    }
}
