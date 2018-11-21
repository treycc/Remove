package com.jdp.hls.page.admin.query;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ProjectItem;

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
public class ProjectSelectPresenter implements ProjectSelectContract.Presenter {
    private UserApi mApi;
    private ProjectSelectContract.View mView;

    @Inject
    public ProjectSelectPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getQueryProjectList() {
        mApi.getApiService().getQueryProjectList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<ProjectItem>>(mView) {
                    @Override
                    protected void onSuccess(List<ProjectItem> projectItemList) {
                        mView.onGetProjectListSuccess(projectItemList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ProjectSelectContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}