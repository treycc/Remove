package com.jdp.hls.page.operate.back;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ReceivePerson;
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
public class BackNodePresenter implements BackNodeContract.Presenter {
    private UserApi mApi;
    private BackNodeContract.View mView;

    @Inject
    public BackNodePresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull BackNodeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void backNode(RequestBody requestBody) {
        mApi.getApiService().backNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onBackNodeSuccess();
                    }
                });
    }

    @Override
    public void getOperatePerson(String groupId) {
        mApi.getApiService().getOperatePerson(groupId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<ReceivePerson>(mView) {
                    @Override
                    protected void onSuccess(ReceivePerson receivePerson) {
                        mView.onGetOperatePersonSuccess(receivePerson);
                    }
                });
    }
}