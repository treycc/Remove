package com.jdp.hls.page.rosterdetail.contacts.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ContactsItem;
import com.jdp.hls.model.entiy.ContactsListDetail;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsListPresenter implements ContactsListContract.Presenter {
    private UserApi mApi;
    private ContactsListContract.View mView;

    @Inject
    public ContactsListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getMainPersonList(String houseId, int buildingType) {
        mApi.getApiService().getMainPersonList(houseId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<ContactsListDetail>(mView) {
                    @Override
                    protected void onSuccess(ContactsListDetail contactsDetail) {
                        mView.onGetMainPersonListSuccess(contactsDetail);
                    }
                });
    }

    @Override
    public void deleteContacts(String houseId, String personId, int buildingType, int position) {
        mApi.getApiService().deleteContacts(houseId, personId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onDeleteContactsSuccess(position);
                    }
                });
    }

    @Override
    public void setMainContacts(String houseId, String personId, int buildingType, int position) {
        mApi.getApiService().setMainContacts(houseId, personId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onSetMainContactsSuccess(position);
                    }
                });
    }

    @Override
    public void importMainContacts(String houseId, String personId, int buildingType, ContactsItem contactsItem) {
        mApi.getApiService().importMainContacts(houseId, personId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onImportMainContactsSuccess(contactsItem);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ContactsListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}