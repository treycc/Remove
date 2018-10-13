package com.jdp.hls.page.otherarea.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.OtherArea;
import com.jdp.hls.model.entiy.ResultObserver;

import java.util.List;

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
public class OtherAreaPresenter implements OhterAreaContract.Presenter {
    private UserApi mApi;
    private OhterAreaContract.View mView;

    @Inject
    public OtherAreaPresenter(UserApi mApi) {
        this.mApi = mApi;
    }


    @Override
    public void attachView(@NonNull OhterAreaContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

    @Override
    public void getOtherAreaList(String id, String buildingType) {
        mApi.getApiService().getOtherAreaList(id, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<OtherArea>>(mView) {
                    @Override
                    protected void onSuccess(List<OtherArea> otherAreaList) {
                        mView.onGetOtherAreaListSuccess(otherAreaList);
                    }
                });
    }

    @Override
    public void deleteOtherArea(RequestBody requestBody,int position) {
        mApi.getApiService().deleteOtherArea(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onDeleteOtherAreaSuccess(position);
                    }
                });
    }

}