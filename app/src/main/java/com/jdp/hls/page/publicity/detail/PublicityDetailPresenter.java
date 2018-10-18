package com.jdp.hls.page.publicity.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.PublicityDetail;
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
public class PublicityDetailPresenter implements PublicityDetailContract.Presenter {
    private UserApi mApi;
    private PublicityDetailContract.View mView;

    @Inject
    public PublicityDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPublicityDetail(int pubId) {
        mApi.getApiService().getPublicityDetail(pubId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<PublicityDetail>(mView) {
                    @Override
                    protected void onSuccess(PublicityDetail publicityDetails) {
                        mView.onGetPublicityDetailSuccess(publicityDetails);
                    }
                });
    }

    @Override
    public void modifyPublicity(RequestBody rosterBody) {
        mApi.getApiService().modifyPublicity(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyPublicitySuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull PublicityDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}