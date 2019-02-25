package com.jdp.hls.page.rosterdetail.detail.personal;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.RosterPersonalDetail;
import com.jdp.hls.model.entiy.resultdata.RosterResult;

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
public class RosterPersonalDetailPresenter implements RosterPersonalDetailContract.Presenter {
    private UserApi mApi;
    private RosterPersonalDetailContract.View mView;

    @Inject
    public RosterPersonalDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getRosterPersonalDetail(String buildingId) {
        mApi.getApiService().getRosterPersonalDetail(buildingId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<RosterPersonalDetail>(mView) {
                    @Override
                    protected void onSuccess(RosterPersonalDetail rosterPersonalDetail) {
                        mView.onGetRosterPersonalDetailSuccess(rosterPersonalDetail);
                    }
                });
    }

    @Override
    public void saveRosterHouse(RequestBody requestBody) {
        mApi.getApiService().saveRosterHouse(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<RosterResult>(mView) {
                    @Override
                    protected void onSuccess(RosterResult rosterResult) {
                        mView.onSaveRosterHouseSuccess(rosterResult);
                    }
                });
    }


    @Override
    public void attachView(@NonNull RosterPersonalDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}