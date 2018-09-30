package com.jdp.hls.page.node.measure.company;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodeCompanyMeasure;
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
public class NodeCompanyMeasurePresenter implements NodeCompanyMeasureContract.Presenter {
    private UserApi mApi;
    private NodeCompanyMeasureContract.View mView;

    @Inject
    public NodeCompanyMeasurePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyMeasure(String houseId) {
        mApi.getApiService().getCompanyMeasure(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodeCompanyMeasure>(mView) {
                    @Override
                    protected void onSuccess(NodeCompanyMeasure nodeCompanyMeasure) {
                        mView.onGetCompanyMeasureSuccess(nodeCompanyMeasure);
                    }
                });
    }


    @Override
    public void modifyCompanyMeasure(RequestBody rosterBody) {
        mApi.getApiService().modifyCompanyMeasure(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyCompanyMeasureSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodeCompanyMeasureContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}