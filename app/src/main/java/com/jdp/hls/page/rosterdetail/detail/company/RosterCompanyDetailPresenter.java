package com.jdp.hls.page.rosterdetail.detail.company;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.RosterCompanyDetail;
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
public class RosterCompanyDetailPresenter implements RosterCompanyDetailContract.Presenter {
    private UserApi mApi;
    private RosterCompanyDetailContract.View mView;

    @Inject
    public RosterCompanyDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getRosterCompanyDetail(String entId) {
        mApi.getApiService().getRosterCompanyDetail(entId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<RosterCompanyDetail>(mView) {
                    @Override
                    protected void onSuccess(RosterCompanyDetail rosterPersonalDetail) {
                        mView.onGetRosterCompanyDetailSuccess(rosterPersonalDetail);
                    }
                });
    }

    @Override
    public void saveRosterCompany(RequestBody requestBody) {
        mApi.getApiService().saveRosterCompany(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<RosterResult>(mView) {
                    @Override
                    protected void onSuccess(RosterResult rosterResult) {
                        mView.onSaveRosterCompanySuccess(rosterResult);
                    }
                });
    }


    @Override
    public void attachView(@NonNull RosterCompanyDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}