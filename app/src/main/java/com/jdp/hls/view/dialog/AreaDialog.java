package com.jdp.hls.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.injector.component.AppComponent;
import com.kingja.wheelview.WheelView;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/11/19 0019 下午 7:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaDialog extends CommonDialog {
    @BindView(R.id.wv_province)
    WheelView wvProvince;
    @BindView(R.id.wv_city)
    WheelView wvCity;
    @BindView(R.id.wv_area)
    WheelView wvArea;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;

    public AreaDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_area;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    public void initVariable() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void initNet() {

    }
}
