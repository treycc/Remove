package com.jdp.hls.page.supervise.project.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ProjectSuperviseInfo;
import com.jdp.hls.model.entiy.ResultObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectListSupervisePresenter implements ProjectListSuperviseContract.Presenter {
    private UserApi mApi;
    private ProjectListSuperviseContract.View mView;

    @Inject
    public ProjectListSupervisePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getProjectSuperviseList(int pageSize, int pageIndex, int orderBy) {
        mApi.getApiService().getProjectSuperviseList(pageSize, pageIndex, orderBy).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<ProjectSuperviseInfo>(mView) {
                    @Override
                    protected void onSuccess(ProjectSuperviseInfo projectSuperviseInfo) {
                        mView.onGetProjectSuperviseListSuccess(projectSuperviseInfo);
                    }
                });
    }


    @Override
    public void attachView(@NonNull ProjectListSuperviseContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}