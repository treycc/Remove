package com.jdp.hls.page.node.measure.personal;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.NodePersonalMeasure;
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
public class NodePersonalMeasurePresenter implements NodePersonalMeasureContract.Presenter {
    private UserApi mApi;
    private NodePersonalMeasureContract.View mView;

    @Inject
    public NodePersonalMeasurePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getPersonalMeasure(String houseId) {
        mApi.getApiService().getPersonalMeasure(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<NodePersonalMeasure>(mView) {
                    @Override
                    protected void onSuccess(NodePersonalMeasure nodePersonalMeasure) {
                        mView.onGetPersonalMeasureSuccess(nodePersonalMeasure);
                    }
                });
    }


    @Override
    public void modifyPersonalMeasure(RequestBody rosterBody) {
        mApi.getApiService().modifyPersonalMeasure(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyPersonalMeasureSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodePersonalMeasureContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}