package com.jdp.hls.page.business.detail.personal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jdp.hls.R;
import com.jdp.hls.activity.DeedImmovableActivity;
import com.jdp.hls.activity.DeedLandActivity;
import com.jdp.hls.activity.DeedPropertyActivity;
import com.jdp.hls.activity.FamilyRelationActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Description:个人业务详情
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessDetailPersonalActivity extends BaseTitleActivity {
    @BindView(R.id.rl_familyRelation)
    RelativeLayout rlFamilyRelation;
    @BindView(R.id.ll_propertyDeed)
    LinearLayout llPropertyDeed;
    @BindView(R.id.ll_landDeed)
    LinearLayout llLandDeed;
    @BindView(R.id.ll_immovableDeed)
    LinearLayout llImmovableDeed;

    @OnClick({R.id.rl_familyRelation, R.id.ll_propertyDeed, R.id.ll_landDeed, R.id.ll_immovableDeed})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_familyRelation:
                GoUtil.goActivity(this, FamilyRelationActivity.class);
                break;
            case R.id.ll_propertyDeed:
                GoUtil.goActivity(this, DeedPropertyActivity.class);
                break;
            case R.id.ll_landDeed:
                GoUtil.goActivity(this, DeedLandActivity.class);
                break;
            case R.id.ll_immovableDeed:
                GoUtil.goActivity(this, DeedImmovableActivity.class);
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_business_personal_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "高二路";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ToastUtil.showText("保存");
            }
        });
    }

    @Override
    protected void initNet() {

    }
}
