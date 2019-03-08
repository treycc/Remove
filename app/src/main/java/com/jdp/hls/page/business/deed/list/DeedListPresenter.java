package com.jdp.hls.page.business.deed.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.BankListInfo;
import com.jdp.hls.model.entiy.DeedListInfo;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.page.business.detail.personal.branklist.BankListContract;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedListPresenter implements DeedListContract.Presenter {
    private UserApi mApi;
    private DeedListContract.View mView;

    @Inject
    public DeedListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDeedList(String buildingId, int certType) {
        mApi.getApiService().getDeedList(buildingId, certType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<DeedListInfo>(mView) {
                    @Override
                    protected void onSuccess(DeedListInfo deedListInfo) {
                        mView.onGetDeedListSuccess(deedListInfo);
                    }
                });
    }

    @Override
    public void deleteDeed(String buildingId, int certId, int certType, int position) {
        mApi.getApiService().deleteDeed( buildingId,  certId,  certType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onDeleteDeed(position);
                    }
                });
    }


    @Override
    public void attachView(@NonNull DeedListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}