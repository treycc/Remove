package com.jdp.hls.page.supervise.statistics.table;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LoadSirObserver;
import com.jdp.hls.model.entiy.Table;
import com.jdp.hls.page.table.list.TableListContract;

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
public class TableListSupervisePresenter implements TableListSuperviseContract.Presenter {
    private UserApi mApi;
    private TableListSuperviseContract.View mView;

    @Inject
    public TableListSupervisePresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getTablesSupervise(String projectId, int buildingType) {
        mApi.getApiService().getTablesSupervise(projectId, buildingType).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<Table>>(mView) {
                    @Override
                    protected void onSuccess(List<Table> tables) {
                        mView.onGetTablesSuperviseSuccess(tables);
                    }
                });
    }


    @Override
    public void attachView(@NonNull TableListSuperviseContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}