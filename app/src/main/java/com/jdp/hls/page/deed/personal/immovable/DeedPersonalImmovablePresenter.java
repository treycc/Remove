package com.jdp.hls.page.deed.personal.immovable;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.model.entiy.DeedPersonalImmovable;
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
public class DeedPersonalImmovablePresenter implements DeedPersonalImmovableContract.Presenter {
    private UserApi mApi;
    private DeedPersonalImmovableContract.View mView;

    @Inject
    public DeedPersonalImmovablePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedPersonalImmovableDetail(int certId) {
        mApi.getApiService().getDeedPersonalImmovableDetail(certId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedPersonalImmovable>(mView) {
                    @Override
                    protected void onSuccess(DeedPersonalImmovable deedPersonalImmovable) {
                        mView.onGetDeedPersonalImmovableSuccess(deedPersonalImmovable);
                    }
                });
    }

    @Override
    public void saveDeedPersonalImmovable(RequestBody rosterBody) {
        mApi.getApiService().saveDeedImmovablePersonal(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<DeedItem>(mView) {
                    @Override
                    protected void onSuccess(DeedItem deedItem) {
                        mView.onSaveDeedPersonalImmovableSuccess(deedItem);
                    }
                });
    }


    @Override
    public void attachView(@NonNull DeedPersonalImmovableContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}