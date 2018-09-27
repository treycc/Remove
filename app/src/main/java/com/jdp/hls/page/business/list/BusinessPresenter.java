package com.jdp.hls.page.business.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.Business;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.TaskInfo;

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
public class BusinessPresenter implements BussinessContract.Presenter {
    private UserApi mApi;
    private BussinessContract.View mView;

    @Inject
    public BusinessPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getBusinessList(String projectId, int buildingType, int taskType) {
        mApi.getApiService().getTaskList(projectId, buildingType,taskType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<TaskInfo>(mView) {
                    @Override
                    protected void onSuccess(TaskInfo taskInfo) {
                        mView.onGetBusinessSuccess(taskInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull BussinessContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}