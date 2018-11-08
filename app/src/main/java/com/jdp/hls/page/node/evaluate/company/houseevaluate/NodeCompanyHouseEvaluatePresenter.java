package com.jdp.hls.page.node.evaluate.company.houseevaluate;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodeCompanyHouseEvaluate;
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
public class NodeCompanyHouseEvaluatePresenter implements NodeCompanyMoneyHouseContract.Presenter {
    private UserApi mApi;
    private NodeCompanyMoneyHouseContract.View mView;

    @Inject
    public NodeCompanyHouseEvaluatePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyHouseEvaluate(String houseId) {
        mApi.getApiService().getCompanyHouseEvaluate(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodeCompanyHouseEvaluate>(mView) {
                    @Override
                    protected void onSuccess(NodeCompanyHouseEvaluate nodeCompanyHouseEvaluate) {
                        mView.onGetCompanyHouseEvaluateSuccess(nodeCompanyHouseEvaluate);
                    }
                });
    }


    @Override
    public void modifyCompanyHouseEvaluate(RequestBody rosterBody) {
        mApi.getApiService().modifyCompanyHouseEvaluate(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyCompanyHouseEvaluateSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodeCompanyMoneyHouseContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}