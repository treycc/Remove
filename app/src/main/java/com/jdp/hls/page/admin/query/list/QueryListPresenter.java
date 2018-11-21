package com.jdp.hls.page.admin.query.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.BusinessQuery;
import com.jdp.hls.model.entiy.LoadSirObserver;

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
public class QueryListPresenter implements QueryListContract.Presenter {
    private UserApi mApi;
    private QueryListContract.View mView;

    @Inject
    public QueryListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getQueryList(String projectId, int buildingType) {
        mApi.getApiService().getQueryList(projectId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<BusinessQuery>>(mView) {
                    @Override
                    protected void onSuccess(List<BusinessQuery> businessQueryList) {
                        mView.onQueryListSuccess(businessQueryList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull QueryListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}