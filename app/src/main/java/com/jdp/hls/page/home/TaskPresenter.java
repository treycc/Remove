package com.jdp.hls.page.home;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.Task;
import com.jdp.hls.page.rosterlist.GetRostersByTypeContract;

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
    public void getTask(String projectId) {
        mApi.getApiService().getTask(projectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Task>>(mView) {
                    @Override
                    protected void onSuccess(List<Task> tasks) {
                        mView.onGetTaskSuccess(tasks);
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