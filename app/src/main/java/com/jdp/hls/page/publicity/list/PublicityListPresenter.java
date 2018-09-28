package com.jdp.hls.page.publicity.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.PublicityItem;

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
public class PublicityListPresenter implements PublicityListContract.Presenter {
    private UserApi mApi;
    private PublicityListContract.View mView;

    @Inject
    public PublicityListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPublicityList(String projectId, int publicityType) {
        mApi.getApiService().getPublicityList(projectId, publicityType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<PublicityItem>>(mView) {
                    @Override
                    protected void onSuccess(List<PublicityItem> publicityItems) {
                        mView.onGetPublicityListSuccess(publicityItems);
                    }
                });
    }


    @Override
    public void attachView(@NonNull PublicityListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}