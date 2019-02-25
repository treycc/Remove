package com.jdp.hls.page.rosterdetail.contacts.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ContactsDetail;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.RosterDetail;
import com.jdp.hls.model.entiy.resultdata.ContactsResult;
import com.jdp.hls.page.rosterdetail.RosterDetailContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsDetailPresenter implements ContactsDetailContract.Presenter {
    private UserApi mApi;
    private ContactsDetailContract.View mView;

    @Inject
    public ContactsDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getContactsDetail(String personId) {
        mApi.getApiService().getContactsDetail( personId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<ContactsDetail>(mView) {
                    @Override
                    protected void onSuccess(ContactsDetail contactsDetail) {
                        mView.onGetContactsDetailSuccess(contactsDetail);
                    }
                });
    }

    @Override
    public void saveContactsDetail(RequestBody requestBody) {
        mApi.getApiService().saveContactsDetail( requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<ContactsResult>(mView) {
                    @Override
                    protected void onSuccess(ContactsResult  contactsResult) {
                        mView.onSaveContactsDetailSuccess(contactsResult);
                    }

                });
    }


    @Override
    public void attachView(@NonNull ContactsDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}