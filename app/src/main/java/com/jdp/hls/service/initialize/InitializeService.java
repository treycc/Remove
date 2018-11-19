package com.jdp.hls.service.initialize;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.jdp.hls.base.App;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.model.entiy.AreaResult;
import com.jdp.hls.model.entiy.Dict;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;

import java.util.List;

import javax.inject.Inject;

/**
 * Description:数据初始化
 * Create Time:2018/7/10 13:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class InitializeService extends IntentService implements InitializeContract.View {

    private static final String TAG = "InitializeService";
    @Inject
    InitializePresenter initializePresenter;

    public InitializeService(String name) {
        super(name);
    }

    public InitializeService() {
        super("InitializeService");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        DaggerBaseCompnent.builder()
                .appComponent(App.getContext().getAppComponent())
                .build()
                .inject(this);
        initializePresenter.attachView(this);
    }

    @Override
    public void onStart(@Nullable Intent intent, int startId) {
        super.onStart(intent, startId);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        initializePresenter.getDict();
//        initializePresenter.getAreaInfo(SpSir.getInstance().getUpdateTime());
        initializePresenter.getAreaData(SpSir.getInstance().getUpdateTime());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LogUtil.e(TAG, "【数据初始化结束】");
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onGetDictsSuccess(List<Dict> dicts) {
        if (dicts != null && dicts.size() > 0) {
            DBManager.getInstance().deleteAllDicts();
            for (Dict dict : dicts) {
                DBManager.getInstance().addDict(new TDict(null, dict.getRowNum(), dict.getConfigType
                        (), dict.getTypeId(), dict.getTypeName(), dict.getConfigTypeDesc(), dict.getParentId(), dict
                        .getClassValue()));
            }
        }
        LogUtil.e(TAG, "【数据初始化】数量：" + dicts.size());

    }

    @Override
    public void onGetAreaDataSuccess(AreaResult areaResult) {
        String updateTime = areaResult.getUpdateTime();
        List<Area> areas = areaResult.getArea();
        if (areas != null && areas.size() > 0) {
            LogUtil.e(TAG, "【区域数据】数量：" + areas.size());
            for (Area area : areas) {
                DBManager.getInstance().addArea(area);
            }
            SpSir.getInstance().setUpdateTime(updateTime);
        }

    }
}
