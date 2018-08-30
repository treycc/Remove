package com.jdp.hls.page.rosterdetail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.RosterDetail;
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
public class RosterDetailPresenter implements RosterDetailContract.Presenter {
    private UserApi mApi;
    private RosterDetailContract.View mView;

    @Inject
    public RosterDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getRosterDetail(String houseId, int employeeId, int isEnterprise) {
        mApi.getApiService().getRosterDetail(houseId, employeeId, isEnterprise).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<RosterDetail>(mView) {
                    @Override
                    protected void onSuccess(RosterDetail rosterDetail) {
                        mView.onGetRosterDetailSuccess(rosterDetail);
                    }
                });
    }

    @Override
    public void modifyRoster(RequestBody requestBody) {
        mApi.getApiService().modifyRoster(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object obj) {
                        mView.onModifyRosterSuccess();
                    }

                });
    }


    @Override
    public void attachView(@NonNull RosterDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}