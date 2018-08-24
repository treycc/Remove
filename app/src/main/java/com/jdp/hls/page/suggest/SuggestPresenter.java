package com.jdp.hls.page.suggest;

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
public class SuggestPresenter implements SuggestContract.Presenter {
    private UserApi mApi;
    private SuggestContract.View mView;

    @Inject
    public SuggestPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void suggest(RequestBody rosterBody) {
        mApi.getApiService().suggest( rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object obj) {
                        mView.onSuggestSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull SuggestContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}