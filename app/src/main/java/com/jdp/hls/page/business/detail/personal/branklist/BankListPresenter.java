package com.jdp.hls.page.business.detail.personal.branklist;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.BankListInfo;
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
public class BankListPresenter implements BankListContract.Presenter {
    private UserApi mApi;
    private BankListContract.View mView;

    @Inject
    public BankListPresenter(UserApi mApi) {
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
    public void deleteBankInfo(String id, int position) {
        mApi.getApiService().deleteBankInfo(id).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onDeleteBankInfo(position);
                    }
                });
    }


    @Override
    public void attachView(@NonNull BankListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}