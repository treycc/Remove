package com.jdp.hls.page.modify;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
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
public class ModifyAndUploadPresenter implements ModifyAndUploadContract.Presenter {
    private UserApi mApi;
    private ModifyAndUploadContract.View mView;

    @Inject
    public ModifyAndUploadPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull ModifyAndUploadContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void modifyAlias(int employeeId, String aliasName) {
        mApi.getApiService().modifyAlias(employeeId, aliasName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object obj) {
                        mView.onModifyAliasSuccess(aliasName);
                    }
                });
    }

    @Override
    public void modifyMobile(int employeeId, String mobile) {
        mApi.getApiService().modifyMobile(employeeId, mobile).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object obj) {
                        mView.onModifyMobileSuccess(mobile);
                    }
                });
    }
}