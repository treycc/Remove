package com.jdp.hls.page.innerdecoration.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.DecorationItem;
import com.jdp.hls.model.entiy.LoadSirObserver;
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
public class DecorationListPresenter implements DecorationListContract.Presenter {
    private UserApi mApi;
    private DecorationListContract.View mView;

    @Inject
    public DecorationListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getDecorationList(String evalId, String buildingType,String itemType) {
        mApi.getApiService().getDecorationList(evalId, buildingType, itemType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<DecorationItem>>(mView) {
                    @Override
                    protected void onSuccess(List<DecorationItem> decorationItemList) {
                        mView.onGetDecorationListSuccess(decorationItemList);
                    }
                });
    }

    @Override
    public void deleteDecoration(RequestBody requestBody,int position) {
        mApi.getApiService().deleteDecoration(requestBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onDeleteDecorationSuccess(position);
                    }
                });
    }


    @Override
    public void attachView(@NonNull DecorationListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}