package com.jdp.hls.page.deed.personal.property;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedPersonalProperty;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;

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
public class DeedPersonalPropertyPresenter implements DeedPersonalPropertyContract.Presenter {
    private UserApi mApi;
    private DeedPersonalPropertyContract.View mView;

    @Inject
    public DeedPersonalPropertyPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedPersonalProperty(String houseId) {
        mApi.getApiService().getDeedPersonalProperty(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedPersonalProperty>(mView) {
                    @Override
                    protected void onSuccess(DeedPersonalProperty deedPersonalProperty) {
                        mView.onGetDeedPersonalPropertySuccess(deedPersonalProperty);
                    }
                });
    }

    @Override
    public void addDeedPersonalProperty(RequestBody rosterBody) {
        mApi.getApiService().addDeedPersonalProperty(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onAddDeedPersonalPropertySuccess();
                    }
                });
    }

    @Override
    public void modifyDeedPersonalProperty(RequestBody rosterBody) {
        mApi.getApiService().modifyDeedPersonalProperty(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyDeedPersonalPropertySuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull DeedPersonalPropertyContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}