package com.jdp.hls.page.photo;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.ResultObserver;

import java.util.List;

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
public class PhotoPresenter implements PhotoContract.Presenter {
    private UserApi mApi;
    private PhotoContract.View mView;

    @Inject
    public PhotoPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull PhotoContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void modifyPhotos(RequestBody requestBody) {
        mApi.getApiService().modifyPhotos( requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<ImgInfo>>(mView) {
                    @Override
                    protected void onSuccess(List<ImgInfo> imgInfoList) {
                        mView.onModifyPhotosSuccess(imgInfoList);
                    }
                });
    }

}