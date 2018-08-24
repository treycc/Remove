package com.jdp.hls.page.rosteradd;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
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
public class RosterAddPresenter implements RosterAddContract.Presenter {
    private UserApi mApi;
    private RosterAddContract.View mView;

    @Inject
    public RosterAddPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void addRoster(RequestBody requestBody) {
        mApi.getApiService().addRoster(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object obj) {
                        mView.onAddRosterSuccess();
                    }

                });
    }


    @Override
    public void attachView(@NonNull RosterAddContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}