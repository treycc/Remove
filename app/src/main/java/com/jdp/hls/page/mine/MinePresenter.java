package com.jdp.hls.page.mine;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.page.modify.ModifyAndUploadContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MinePresenter implements MineContract.Presenter {
    private UserApi mApi;
    private MineContract.View mView;

    @Inject
    public MinePresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull MineContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void uploadHeadImg(MultipartBody.Part headImg) {
        mApi.getApiService().uploadHeadImg(headImg).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<String>(mView) {
                    @Override
                    protected void onSuccess(String url) {
                        mView.onUploadHeadImgSuccess(url);
                    }
                });
    }

}