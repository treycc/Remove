package com.jdp.hls.fragment;

import android.support.v7.widget.CardView;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.activity.LevyActivity;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.ToastUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/9/5 0005 上午 9:06
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class HomeFragment extends BaseFragment {
    @BindView(R.id.cardv_system_levy)
    CardView cardvSystemLevy;
    @BindView(R.id.cardv_system_monitor)
    CardView cardvSystemMonitor;
    @BindView(R.id.cardv_system_geography)
    CardView cardvSystemGeography;

    @OnClick({R.id.cardv_system_levy, R.id.cardv_system_monitor, R.id.cardv_system_geography})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.cardv_system_levy:
                GoUtil.goActivity(getActivity(), LevyActivity.class);
                break;
            case R.id.cardv_system_monitor:
                ToastUtil.showText("功能开发中");
                break;
            case R.id.cardv_system_geography:
                ToastUtil.showText("功能开发中");
                break;
        }
    }

    @Override
    protected void initVariable() {

    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_home;
    }

}
