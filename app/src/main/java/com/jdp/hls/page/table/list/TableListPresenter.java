package com.jdp.hls.page.table.list;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.ResultObserver;
import com.jdp.hls.model.entiy.Table;

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
public class TableListPresenter implements TableListContract.Presenter {
    private UserApi mApi;
    private TableListContract.View mView;

    @Inject
    public TableListPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getTables(String projectId, int buildingType, String statisId) {
        mApi.getApiService().getTables(projectId, buildingType, statisId).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new ResultObserver<List<Table>>(mView) {
                    @Override
                    protected void onSuccess(List<Table> tables) {
                        mView.onGetTablesSuccess(tables);
                    }
                });
    }


    @Override
    public void attachView(@NonNull TableListContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}