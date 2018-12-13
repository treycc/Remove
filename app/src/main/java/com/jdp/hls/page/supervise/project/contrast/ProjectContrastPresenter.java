package com.jdp.hls.page.supervise.project.contrast;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ProjectFacade;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectContrastPresenter implements ProjectContrastContract.Presenter {
    private UserApi mApi;
    private ProjectContrastContract.View mView;

    @Inject
    public ProjectContrastPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getProjectPhoto(String projectId) {
        mApi.getApiService().getProjectPhoto(projectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<ProjectFacade>(mView) {
                    @Override
                    protected void onSuccess(ProjectFacade projectFacade) {
                        mView.onGetProjectPhotoSuccess(projectFacade);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ProjectContrastContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}