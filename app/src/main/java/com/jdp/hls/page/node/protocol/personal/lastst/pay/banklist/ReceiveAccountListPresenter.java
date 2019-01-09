package com.jdp.hls.page.node.protocol.personal.lastst.pay.banklist;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.BankListInfo;
import com.jdp.hls.model.entiy.LoadSirObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReceiveAccountListPresenter implements ReceiveAccountListContract.Presenter {
    private UserApi mApi;
    private ReceiveAccountListContract.View mView;

    @Inject
    public ReceiveAccountListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getBrankList(String buildingId) {
        mApi.getApiService().getBrankList(buildingId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<BankListInfo>(mView) {
                    @Override
                    protected void onSuccess(BankListInfo brankListInfo) {
                        mView.onGetBrankListSuccess(brankListInfo);
                    }
                });
    }

    @Override
    public void attachView(@NonNull ReceiveAccountListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}