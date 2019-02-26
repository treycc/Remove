package com.jdp.hls.page.geography;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.Roster;

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
public class GetGeographyRosterListPresenter implements GetGeographyRosterListContract.Presenter {
    private UserApi mApi;
    private GetGeographyRosterListContract.View mView;

    @Inject
    public GetGeographyRosterListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getGeographyRosterList() {
        mApi.getApiService().getGeographyRosterList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Roster>>(mView) {
                    @Override
                    protected void onSuccess(List<Roster> rosters) {
                        mView.onGetGeographyRosterListSuccess(rosters);
                    }
                });
    }


    @Override
    public void attachView(@NonNull GetGeographyRosterListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}