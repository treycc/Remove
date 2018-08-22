package com.jdp.hls.page.modifyPassword;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.rx.ResultObserver;
import com.jdp.hls.util.EncryptUtil;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyPasswordPresenter implements ModifyPasswordContract.Presenter {
    private UserApi mApi;
    private ModifyPasswordContract.View mView;

    @Inject
    public ModifyPasswordPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void modifyPassword(int employeeId, String oldPassword, String newPassword) {
        mApi.getApiService().modifyPassword(employeeId, EncryptUtil.getDoubleMd5(oldPassword), EncryptUtil
                .getDoubleMd5(newPassword)).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object obj) {
                        mView.onModifyPasswordSuccess(newPassword);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ModifyPasswordContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}