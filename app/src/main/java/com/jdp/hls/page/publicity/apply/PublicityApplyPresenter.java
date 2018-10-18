package com.jdp.hls.page.publicity.apply;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.PublicityItem;
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
public class PublicityApplyPresenter implements PublicityApplyContract.Presenter {
    private UserApi mApi;
    private PublicityApplyContract.View mView;

    @Inject
    public PublicityApplyPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void applyPublicity(RequestBody rosterBody) {
        mApi.getApiService().applyPublicity(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<PublicityItem>(mView) {
                    @Override
                    protected void onSuccess(PublicityItem publicityItem) {
                        mView.onApplyPublicitySuccess(publicityItem);
                    }
                });
    }


    @Override
    public void attachView(@NonNull PublicityApplyContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}