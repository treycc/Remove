package com.jdp.hls.page.airphoto.person;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.activity.AirPhotoDetailActivity;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AirPhotoPerson;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:选择复查对象
 * Create Time:2018/9/13 0013 下午 6:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoPersonActivity extends BaseTitleActivity implements AirPhotoPersonContract.View,
        SwipeRefreshLayout.OnRefreshListener {
    @BindView(R.id.et_airphoto_keyword)
    EditText etAirphotoKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private List<AirPhotoPerson> airPhotoPersonList = new ArrayList<>();
    @Inject
    AirPhotoPersonPresenter airPhotoPersonPresenter;
    private AirPhotoPersonAdapter airPhotoPersonAdapter;

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etAirphotoKeyword.setText("");
                break;
        }
    }

    @Override
    public void initVariable() {
        for (int i = 0; i < 10; i++) {
            airPhotoPersonList.add(new AirPhotoPerson());
        }

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
        airPhotoPersonPresenter.attachView(this);
        airPhotoPersonAdapter = new AirPhotoPersonAdapter(this, airPhotoPersonList, R.layout
                .item_airphoto_person);
        plv.setAdapter(airPhotoPersonAdapter);
    }

    @Override
    protected void initData() {
        srl.setOnRefreshListener(this);
        etAirphotoKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        setRightClick("确定", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(AirPhotoPersonActivity.this, AirPhotoDetailActivity.class);
            }
        });
    }

    @Override
    protected void initNet() {
//        airPhotoPersonPresenter.getAirPhotoPersons("");
    }

    @Override
    public void onGetAirPhotoPersonsSuccess(List<AirPhotoPerson> airPhotoPersonList) {
        if (airPhotoPersonList != null && airPhotoPersonList.size() > 0) {
            airPhotoPersonAdapter.setData(airPhotoPersonList);
        }
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

    class AirPhotoPersonAdapter extends CommonAdapter<AirPhotoPerson> {
        public AirPhotoPersonAdapter(Context context, List<AirPhotoPerson> datas, int itemLayoutId) {
            super(context, datas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, AirPhotoPerson item) {
//            helper.setText(R.id.tv_business_address, item.getHouseAddress());
//            helper.setText(R.id.tv_business_number, item.getSysCode());
//            helper.setText(R.id.tv_business_name, item.getRealName());
//            helper.setText(R.id.tv_business_mobile, item.getMobilePhone());
//            helper.setBackgroundResource(R.id.iv_business_hasLocation, (item.isHasLongitudeAndLatitude() ? R.mipmap
//                    .ic_location_sel : R.mipmap.ic_location_nor));
//            helper.setVisibility(R.id.iv_business_isBack, item.isFlowBack());

        }
    }

}
