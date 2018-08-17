package com.jdp.hls.page.projects;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.rx.ResultObserver;

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
public class ProjectsPresenter implements ProjectsContract.Presenter {
    private UserApi mApi;
    private ProjectsContract.View mView;

    @Inject
    public ProjectsPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull ProjectsContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getProjects(int userId) {
        mApi.getApiService().getProjects(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Project>>(mView) {
                    @Override
                    protected void onSuccess(List<Project> projects) {
                        mView.onGetProjectsSuccess(projects);
                    }
                });
    }

}