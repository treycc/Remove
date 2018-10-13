package com.jdp.hls.page.familyrelation.detail;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.RequestBody;
import retrofit2.http.Body;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FamilyMememberDetailPresenter implements FamilyMememberDetailContract.Presenter {
    private UserApi mApi;
    private FamilyMememberDetailContract.View mView;

    @Inject
    public FamilyMememberDetailPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void saveFamilyRemember(@Body RequestBody requestBody) {
        mApi.getApiService().saveFamilyRemember(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onSaveFamilyRememberSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull FamilyMememberDetailContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}