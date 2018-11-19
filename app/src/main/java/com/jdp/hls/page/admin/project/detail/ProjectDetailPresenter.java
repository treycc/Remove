package com.jdp.hls.page.admin.project.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ProjectItem;
import com.jdp.hls.model.entiy.ResultObserver;

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
public class ProjectDetailPresenter implements ProjecDetailContract.Presenter {
    private UserApi mApi;
    private ProjecDetailContract.View mView;

    @Inject
    public ProjectDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getProjectDetail(String projectId) {
        mApi.getApiService().getProjectDetail(projectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<ProjectItem>(mView) {
                    @Override
                    protected void onSuccess(ProjectItem projectItem) {
                        mView.onGetProjectDetailSuccess(projectItem);
                    }
                });
    }

    @Override
    public void saveProject(RequestBody requestBody) {
        mApi.getApiService().saveProject(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<ProjectItem>(mView) {
                    @Override
                    protected void onSuccess(ProjectItem projectItem) {
                        mView.onSaveProjectSuccess(projectItem);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ProjecDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}