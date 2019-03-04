package com.jdp.hls.page.projects;

import android.support.annotation.NonNull;

import com.jdp.hls.model.ProjectAreaInfo;
import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.AreaSelectorItem;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.model.entiy.ResultObserver;

import java.util.List;

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
public class ProjectsAreaPresenter implements ProjectsAreaContract.Presenter {
    private UserApi mApi;
    private ProjectsAreaContract.View mView;

    @Inject
    public ProjectsAreaPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull ProjectsAreaContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getProjects(int userId) {
        mApi.getApiService().getAreaProjects(userId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<ProjectAreaInfo>(mView) {
                    @Override
                    protected void onSuccess(ProjectAreaInfo projectAreaInfo) {
                        mView.onGetProjectsSuccess(projectAreaInfo);
                    }
                });
    }

    @Override
    public void getAuthAreaList(int parentId, int level, AreaSelectorItem areaSelectorItem) {
        mApi.getApiService().getAuthAreaList(parentId, level).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<AreaSelectorItem>>(mView) {
                    @Override
                    protected void onSuccess(List<AreaSelectorItem> areaSelectorItemList) {
                        mView.onGetAuthAreaListSuccess(areaSelectorItemList, areaSelectorItem);
                    }
                });
    }

    @Override
    public void switchProject(RequestBody requestBody, Project project) {
        mApi.getApiService().switchProject(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onSwitchProjectSuccess(project);
                    }
                });
    }

}