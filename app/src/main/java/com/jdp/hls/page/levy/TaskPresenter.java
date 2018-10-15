package com.jdp.hls.page.levy;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LevyInfo;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.Task;

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
public class TaskPresenter implements TaskContract.Presenter {
    private UserApi mApi;
    private TaskContract.View mView;

    @Inject
    public TaskPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getTask(String projectId, int buildingType) {
        mApi.getApiService().getTask(projectId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<LevyInfo>(mView) {
                    @Override
                    protected void onSuccess(LevyInfo levyInfo) {
                        mView.onGetTaskSuccess(levyInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull TaskContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}