package com.jdp.hls.page.admin.contrast;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ProjectFacade;
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
public class ProjectContrastDetailPresenter implements ProjectContrastDetailContract.Presenter {
    private UserApi mApi;
    private ProjectContrastDetailContract.View mView;

    @Inject
    public ProjectContrastDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void saveVrInfo(RequestBody requestBody) {
        mApi.getApiService().saveVrInfo(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object obj) {
                        mView.onSaveVrInfoSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull ProjectContrastDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}