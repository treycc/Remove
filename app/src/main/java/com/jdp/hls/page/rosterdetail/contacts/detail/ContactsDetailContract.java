package com.jdp.hls.page.rosterdetail.contacts.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.ContactsDetail;
import com.jdp.hls.model.entiy.RosterDetail;
import com.jdp.hls.model.entiy.resultdata.ContactsResult;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface ContactsDetailContract {
    interface View extends BaseView {
        void onGetContactsDetailSuccess(ContactsDetail contactsDetail);
        void onSaveContactsDetailSuccess(ContactsResult contactsResult);
    }

    interface Presenter extends BasePresenter<View> {
        void getContactsDetail(String personId);
        void saveContactsDetail(RequestBody requestBody);
    }
}
