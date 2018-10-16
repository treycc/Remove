package com.jdp.hls.page.operate.send;

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
public class SendNodePresenter implements SendNodeContract.Presenter {
    private UserApi mApi;
    private SendNodeContract.View mView;

    @Inject
    public SendNodePresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull SendNodeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getNextNodePersonName(String buildingId, String buildingType) {
        mApi.getApiService().getNextNodePersonName(buildingId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<ReceivePerson>(mView) {
                    @Override
                    protected void onSuccess(ReceivePerson receivePerson) {
                        mView.onGetNextNodePersonNameSuccess(receivePerson);
                    }
                });
    }

    @Override
    public void sendNode(RequestBody requestBody) {
        mApi.getApiService().sendNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onSendNodeSuccess();
                    }
                });
    }
}