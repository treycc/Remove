package com.jdp.hls.page.deed.personal.property;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.model.entiy.DeedPersonalProperty;
import com.jdp.hls.model.entiy.LoadSirObserver;
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
public class DeedPersonalPropertyPresenter implements DeedPersonalPropertyContract.Presenter {
    private UserApi mApi;
    private DeedPersonalPropertyContract.View mView;

    @Inject
    public DeedPersonalPropertyPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedPersonalPropertyDetail(int certId) {
        mApi.getApiService().getDeedPersonalPropertyDetail(certId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedPersonalProperty>(mView) {
                    @Override
                    protected void onSuccess(DeedPersonalProperty deedPersonalProperty) {
                        mView.onGetDeedPersonalPropertySuccess(deedPersonalProperty);
                    }
                });
    }

    @Override
    public void saveDeedPersonalProperty(RequestBody rosterBody) {
        mApi.getApiService().saveDeedPropertyPersonal(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<DeedItem>(mView) {
                    @Override
                    protected void onSuccess(DeedItem deedItem) {
                        mView.onSaveDeedPersonalPropertySuccess(deedItem);
                    }
                });
    }



    @Override
    public void attachView(@NonNull DeedPersonalPropertyContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}