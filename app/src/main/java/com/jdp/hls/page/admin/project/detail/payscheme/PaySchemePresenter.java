package com.jdp.hls.page.admin.project.detail.payscheme;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.PaySchemeInfo;
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
public class PaySchemePresenter implements PaySchemeContract.Presenter {
    private UserApi mApi;
    private PaySchemeContract.View mView;

    @Inject
    public PaySchemePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPayScheme(String projectId) {
        mApi.getApiService().getPayScheme(projectId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<PaySchemeInfo>(mView) {
                    @Override
                    protected void onSuccess(PaySchemeInfo paySchemeInfo) {
                        mView.onGetPaySchemeSuccess(paySchemeInfo);
                    }
                });
    }

    @Override
    public void savePayScheme(RequestBody requestBody) {
        mApi.getApiService().savePayScheme(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onSavePaySchemeSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull PaySchemeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}