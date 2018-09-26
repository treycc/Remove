package com.jdp.hls.service.initialize;

import android.support.annotation.NonNull;

import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.Dict;
import com.jdp.hls.model.entiy.ResultObserver;

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
public class InitializePresenter implements InitializeContract.Presenter {
    private UserApi mApi;
    private InitializeContract.View mView;

    @Inject
    public InitializePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDict() {
        mApi.getApiService().getDicts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Dict>>(mView) {
                    @Override
                    protected void onSuccess(List<Dict> dicts) {
                        mView.onGetDictsSuccess(dicts);
                    }
                });
    }


    @Override
    public void attachView(@NonNull InitializeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}