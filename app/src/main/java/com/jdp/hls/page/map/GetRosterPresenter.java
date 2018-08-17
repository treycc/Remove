package com.jdp.hls.page.map;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.rx.ResultObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GetRosterPresenter implements GetRosterContract.Presenter {
    private UserApi mApi;
    private GetRosterContract.View mView;

    @Inject
    public GetRosterPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getRosterList(String projectId, int employeeId) {
        mApi.getApiService().getRosterList(projectId, employeeId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Roster>>(mView) {
                    @Override
                    protected void onSuccess(List<Roster> rosters) {
                        mView.onGetRosterdSuccess(rosters);
                    }
                });
    }


    @Override
    public void attachView(@NonNull GetRosterContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}