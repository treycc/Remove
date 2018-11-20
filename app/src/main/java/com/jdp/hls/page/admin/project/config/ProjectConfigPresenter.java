package com.jdp.hls.page.admin.project.config;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.ConfigCompany;
import com.jdp.hls.model.entiy.LoadSirObserver;
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
public class ProjectConfigPresenter implements ProjecConfigContract.Presenter {
    private UserApi mApi;
    private ProjecConfigContract.View mView;

    @Inject
    public ProjectConfigPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getProjectConfig(String projectId) {
        mApi.getApiService().getProjectConfig(projectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<ConfigCompany>>(mView) {
                    @Override
                    protected void onSuccess(List<ConfigCompany> companyList) {
                        mView.onGetProjectConfigSuccess(companyList);
                    }
                });
    }

    @Override
    public void modifyProjectConfig(RequestBody requestBody) {
        mApi.getApiService().modifyProjectConfig(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyProjectConfigSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull ProjecConfigContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}