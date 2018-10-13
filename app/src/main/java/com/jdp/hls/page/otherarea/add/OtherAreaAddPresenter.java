package com.jdp.hls.page.otherarea.add;

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
public class OtherAreaAddPresenter implements OhterAreaAddContract.Presenter {
    private UserApi mApi;
    private OhterAreaAddContract.View mView;

    @Inject
    public OtherAreaAddPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull OhterAreaAddContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void addOtherArea(RequestBody requestBody) {
        mApi.getApiService().addOtherArea(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Integer>(mView) {
                    @Override
                    protected void onSuccess(Integer id) {
                        mView.onAddOtherAreaSuccess(id);
                    }
                });
    }
}