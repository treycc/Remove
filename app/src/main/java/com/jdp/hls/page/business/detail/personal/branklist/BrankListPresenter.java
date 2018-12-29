package com.jdp.hls.page.business.detail.personal.branklist;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.BrankListInfo;
import com.jdp.hls.model.entiy.DetailPersonal;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.page.business.detail.personal.DetailPersonalContract;

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
public class BrankListPresenter implements BrankListContract.Presenter {
    private UserApi mApi;
    private BrankListContract.View mView;

    @Inject
    public BrankListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getBrankList(String buildingId) {
        mApi.getApiService().getBrankList(buildingId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<BrankListInfo>(mView) {
                    @Override
                    protected void onSuccess(BrankListInfo brankListInfo) {
                        mView.onGetBrankListSuccess(brankListInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull BrankListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}