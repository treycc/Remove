package com.jdp.hls.page.supervise.project.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ProjectSuperviseDetail;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectDetailSupervisePresenter implements ProjectDetailSuperviseContract.Presenter {
    private UserApi mApi;
    private ProjectDetailSuperviseContract.View mView;

    @Inject
    public ProjectDetailSupervisePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getProjectSuperviseDetail(String projectId) {
        mApi.getApiService().getProjectSuperviseDetail(projectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<ProjectSuperviseDetail>(mView) {
                    @Override
                    protected void onSuccess(ProjectSuperviseDetail projectSuperviseDetail) {
                        mView.onGetProjectSuperviseDetailSuccess(projectSuperviseDetail);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ProjectDetailSuperviseContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}