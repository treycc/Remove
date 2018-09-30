package com.jdp.hls.page.node.mapping.company;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodeCompanyMapping;
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
public class NodeCompanyMappingPresenter implements NodeCompanyMappingContract.Presenter {
    private UserApi mApi;
    private NodeCompanyMappingContract.View mView;

    @Inject
    public NodeCompanyMappingPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyMapping(String houseId) {
        mApi.getApiService().getCompanyMapping(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodeCompanyMapping>(mView) {
                    @Override
                    protected void onSuccess(NodeCompanyMapping nodeCompanyMapping) {
                        mView.onGetCompanyMappingSuccess(nodeCompanyMapping);
                    }
                });
    }


    @Override
    public void modifyCompanyMapping(RequestBody rosterBody) {
        mApi.getApiService().modifyCompanyMapping(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyCompanyMappingSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodeCompanyMappingContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}