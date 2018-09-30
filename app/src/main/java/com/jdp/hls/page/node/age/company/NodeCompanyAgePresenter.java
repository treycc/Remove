package com.jdp.hls.page.node.age.company;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodeCompanyAge;
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
public class NodeCompanyAgePresenter implements NodeCompanyAgeContract.Presenter {
    private UserApi mApi;
    private NodeCompanyAgeContract.View mView;

    @Inject
    public NodeCompanyAgePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyAge(String houseId) {
        mApi.getApiService().getCompanyAge(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodeCompanyAge>(mView) {
                    @Override
                    protected void onSuccess(NodeCompanyAge nodeCompanyAge) {
                        mView.onGetCompanyAgeSuccess(nodeCompanyAge);
                    }
                });
    }


    @Override
    public void modifyCompanyAge(RequestBody rosterBody) {
        mApi.getApiService().modifyCompanyAge(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyCompanyAgeSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodeCompanyAgeContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}