package com.jdp.hls.page.airphoto.building;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.AirPhotoPersonAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AirPhotoBuilding;
import com.jdp.hls.page.airphoto.add.AirPhotoApplyActivity;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:选择复查对象
 * Create Time:2018/9/13 0013 下午 6:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoBuildingActivity extends BaseTitleActivity implements AirPhotoBuildingContract.View,
        SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.et_airphoto_keyword)
    EditText etAirphotoKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private List<AirPhotoBuilding> airPhotoBuildingList = new ArrayList<>();
    @Inject
    AirPhotoBuildingPresenter airPhotoBuildingPresenter;
    private AirPhotoPersonAdapter airPhotoPersonAdapter;

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etAirphotoKeyword.setText("");
                break;
        }
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        AirPhotoBuilding airPhotoBuilding = (AirPhotoBuilding) adapterView.getItemAtPosition(position);
        AirPhotoApplyActivity.goActivity(this, airPhotoBuilding);
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_airphoto_person;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "选择复查对象";
    }

    @Override
    protected void initView() {
        airPhotoBuildingPresenter.attachView(this);
        airPhotoPersonAdapter = new AirPhotoPersonAdapter(this, airPhotoBuildingList, R.layout
                .item_airphoto_person);
        plv.setAdapter(airPhotoPersonAdapter);
    }

    @Override
    protected void initData() {
        srl.setOnRefreshListener(this);
        etAirphotoKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                airPhotoPersonAdapter.searchPerson(s.toString());
            }
        });
    }

    @Override
    protected void initNet() {
        airPhotoBuildingPresenter.getAirPhotoBuildings("-1");
    }


    @Override
    public void showLoading() {
        srl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        srl.setRefreshing(false);
    }

    @Override
    public void onRefresh() {
        initNet();
    }

    @Override
    public void onGetAirPhotoBuildingsSuccess(List<AirPhotoBuilding> airPhotoBuildingList) {
        if (airPhotoBuildingList != null && airPhotoBuildingList.size() > 0) {
            airPhotoPersonAdapter.setData(airPhotoBuildingList);
        }else{
            showEmptyCallback();
        }
    }
}
