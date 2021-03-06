package com.jdp.hls.page.rosterlist;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.page.operate.delete.DeleteNodeContract;

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
public class DeleteRosterPresenter implements DeleteRosterContract.Presenter {
    private UserApi mApi;
    private DeleteRosterContract.View mView;

    @Inject
    public DeleteRosterPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull DeleteRosterContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }


    @Override
    public void deleteRoster(RequestBody requestBody, Roster roster, int position) {
        mApi.getApiService().deleteNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onDeleteRosterSuccess(roster,position);
                    }
                });
    }
}