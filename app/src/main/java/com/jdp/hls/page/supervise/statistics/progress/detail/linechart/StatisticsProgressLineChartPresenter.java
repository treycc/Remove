package com.jdp.hls.page.supervise.statistics.progress.detail.linechart;

import android.support.annotation.NonNull;

import com.jdp.hls.model.api.UserApi;
import com.jdp.hls.model.entiy.LineChartItem;
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
public class StatisticsProgressLineChartPresenter implements StatisticsProgressLineChartContract.Presenter {
    private UserApi mApi;
    private StatisticsProgressLineChartContract.View mView;

    @Inject
    public StatisticsProgressLineChartPresenter(UserApi mApi) {
        this.mApi = mApi;
    }

    @Override
    public void getLineChart(int itemType, int dateType, String beginDate, String endDate) {
        mApi.getApiService().getLineChart(itemType, dateType, beginDate, endDate).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe
                (new LoadSirObserver<List<LineChartItem>>(mView) {
                    @Override
                    protected void onSuccess(List<LineChartItem> lineChartItemList) {
                        mView.onGetLineChartSuccess(lineChartItemList);
                    }
                });
    }


    @Override
    public void attachView(@NonNull StatisticsProgressLineChartContract.View view) {
        this.mView = view;
    }

    @Override
    public void detachView() {

    }

}