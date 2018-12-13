package com.jdp.hls.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.fragment.LocationFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;

/**
 * Description:用户定位
 * Create Time:2018/8/7 0007 下午 2:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LocationActivity extends BaseTitleActivity {

    @BindView(R.id.fl_map)
    FrameLayout flMap;
    private double currentLng;
    private double currentLat;
    private LocationFragment locationFragment;

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_location;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "用户定位";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("确认", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("lng", currentLng);
                intent.putExtra("lat", currentLat);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
//        locationFragment = (LocationFragment) getSupportFragmentManager().findFragmentById(R.id
//                .fragment_map);
        if (locationFragment == null) {
            locationFragment = new LocationFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.fl_map, locationFragment).commit();
        }
        locationFragment.setOnLocationGetListener(new LocationFragment.OnLocationGetListener() {
            @Override
            public void onLoactionGet(double lng, double lat) {
                currentLng = lng;
                currentLat = lat;
            }
        });
    }

    @Override
    protected void onDestroy() {
        getSupportFragmentManager().beginTransaction().remove(locationFragment).commitAllowingStateLoss();
        super.onDestroy();
    }

    @Override
    public void initNet() {

    }
}
