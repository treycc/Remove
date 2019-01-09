package com.jdp.hls.page.operate;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
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
public class OperateNodePresenter implements OperateNodeContract.Presenter {
    private UserApi mApi;
    private OperateNodeContract.View mView;

    @Inject
    public OperateNodePresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull OperateNodeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }


    @Override
    public void deleteNode(RequestBody requestBody, String buildingIds) {
        mApi.getApiService().deleteNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onDeleteNodeSuccess(buildingIds);
                    }
                });
    }

    @Override
    public void sendNode(RequestBody requestBody, String buildingIds) {
        mApi.getApiService().sendNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onSendNodeSuccess(buildingIds);
                    }
                });
    }

    @Override
    public void reviewNode(RequestBody requestBody, String buildingIds) {
        mApi.getApiService().reviewNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onReviewNodeSuccess(buildingIds);
                    }
                });
    }

    @Override
    public void backNode(RequestBody requestBody, String buildingIds) {
        mApi.getApiService().backNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onBackNodeSuccess(buildingIds);
                    }
                });
    }

    @Override
    public void recoverNode(RequestBody requestBody, String buildingIds) {
        mApi.getApiService().recoverNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onRecoverNodeSuccess(buildingIds);
                    }
                });
    }

    @Override
    public void reminderNode(RequestBody requestBody, String buildingIds) {
        mApi.getApiService().reminderNode(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onReminderNodeSuccess(buildingIds);
                    }
                });
    }

}