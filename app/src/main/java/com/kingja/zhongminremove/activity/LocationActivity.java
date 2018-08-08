package com.kingja.zhongminremove.activity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.fragment.LocationFragment;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.util.NoDoubleClickListener;

/**
 * Description:用户定位
 * Create Time:2018/8/7 0007 下午 2:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LocationActivity extends BaseTitleActivity {

    private double currentLng;
    private double currentLat;

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
                intent.putExtra("lng",currentLng);
                intent.putExtra("lat",currentLat);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });
        LocationFragment locationFragment = (LocationFragment) getSupportFragmentManager().findFragmentById(R.id
                .fragment_map);
        locationFragment.setOnLocationGetListener(new LocationFragment.OnLocationGetListener() {
            @Override
            public void onLoactionGet(double lng, double lat) {
                currentLng = lng;
                currentLat = lat;
            }
        });
    }

    @Override
    protected void initNet() {

    }
}
