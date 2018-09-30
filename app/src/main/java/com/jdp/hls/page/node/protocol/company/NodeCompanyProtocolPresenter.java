package com.jdp.hls.page.node.protocol.company;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.NodeCompanyProtocol;
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
public class NodeCompanyProtocolPresenter implements NodeCompanyProtocolContract.Presenter {
    private UserApi mApi;
    private NodeCompanyProtocolContract.View mView;

    @Inject
    public NodeCompanyProtocolPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getCompanyProtocol(String houseId) {
        mApi.getApiService().getCompanyProtocol(houseId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<NodeCompanyProtocol>(mView) {
                    @Override
                    protected void onSuccess(NodeCompanyProtocol nodeCompanyProtocol) {
                        mView.onGetCompanyProtocolSuccess(nodeCompanyProtocol);
                    }
                });
    }


    @Override
    public void modifyCompanyProtocol(RequestBody rosterBody) {
        mApi.getApiService().modifyCompanyProtocol(rosterBody).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<Object>(mView) {
                    @Override
                    protected void onSuccess(Object object) {
                        mView.onModifyCompanyProtocolSuccess();
                    }
                });
    }


    @Override
    public void attachView(@NonNull NodeCompanyProtocolContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}