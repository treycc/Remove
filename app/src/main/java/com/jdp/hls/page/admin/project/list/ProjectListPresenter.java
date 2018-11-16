package com.jdp.hls.page.admin.project.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ProjectListInfo;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectListPresenter implements ProjectListContract.Presenter {
    private UserApi mApi;
    private ProjectListContract.View mView;

    @Inject
    public ProjectListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getProjectList() {
        mApi.getApiService().getProjectList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<ProjectListInfo>(mView) {
                    @Override
                    protected void onSuccess(ProjectListInfo projectListInfo) {
                        mView.onGetProjectListSuccess(projectListInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ProjectListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}