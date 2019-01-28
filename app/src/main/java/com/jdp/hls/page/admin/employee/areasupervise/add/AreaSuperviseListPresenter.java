package com.jdp.hls.page.admin.employee.areasupervise.add;

import android.support.annotation.NonNull;

import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.model.entiy.ResultObserver;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


/**
 * Description：TODO
 * Create Time：2016/10/10 16:05
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSuperviseListPresenter implements AreaSuperviseListContract.Presenter {
    private UserApi mApi;
    private AreaSuperviseListContract.View mView;

    @Inject
    public AreaSuperviseListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getAreaSuperviseList() {
        mApi.getApiService().getAreaSuperviseList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<AreaSupervise>>(mView) {
                    @Override
                    protected void onSuccess(List<AreaSupervise> areaList) {
                        mView.onGetAreaSuperviseList(areaList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull AreaSuperviseListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}