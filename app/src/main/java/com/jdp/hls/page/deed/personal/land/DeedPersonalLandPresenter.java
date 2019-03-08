package com.jdp.hls.page.deed.personal.land;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.model.entiy.DeedPersonalLand;
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
public class DeedPersonalLandPresenter implements DeedPersonalLandContract.Presenter {
    private UserApi mApi;
    private DeedPersonalLandContract.View mView;

    @Inject
    public DeedPersonalLandPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedPersonalLandDetail(int certId) {
        mApi.getApiService().getDeedPersonalLandDetail(certId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedPersonalLand>(mView) {
                    @Override
                    protected void onSuccess(DeedPersonalLand deedPersonalLand) {
                        mView.onGetDeedPersonalLandSuccess(deedPersonalLand);
                    }
                });
    }

    @Override
    public void saveDeedPersonalLand(RequestBody rosterBody) {
        mApi.getApiService().saveDeedLandPersonal(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<DeedItem>(mView) {
                    @Override
                    protected void onSuccess(DeedItem deedItem) {
                        mView.onSaveDeedPersonalLandSuccess(deedItem);
                    }
                });
    }


    @Override
    public void attachView(@NonNull DeedPersonalLandContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}