package com.jdp.hls.fragment;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.jdp.hls.R;
import com.jdp.hls.activity.LocationActivity;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.rosteradd.RosterAddActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.ToastUtil;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/8/8 0008 上午 11:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LngLatFragment extends BaseFragment implements AMap.OnMapClickListener {
    @BindView(R.id.map_fragment)
    TextureMapView mMapView;
    private AMap aMap;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.e(TAG, "onViewCreated: ");
        mMapView.onCreate(savedInstanceState);
        if (aMap == null) {
            aMap = mMapView.getMap();
            aMap.setOnMapClickListener(this);
            aMap.moveCamera(CameraUpdateFactory.zoomBy(Constants.MapSetting.Zoom));
            aMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Constants.MapSetting.Lat, Constants.MapSetting
                    .Lng)));
            //激活定位并显示定位锚点
//            aMap.setMyLocationEnabled(true);
            UiSettings mUiSettings = aMap.getUiSettings();
            //不显示缩放
            mUiSettings.setZoomControlsEnabled(false);
            //不支持手势
            mUiSettings.setAllGesturesEnabled(false);
            //不显示指南针
            mUiSettings.setCompassEnabled(false);
        }
    }

    private Marker mGPSMarker;

    private void drawMarkers(double lng, double lat) {
        //关闭定位图层
        aMap.setMyLocationEnabled(false);
        if (mGPSMarker != null) {
            mGPSMarker.remove();
        }
        MarkerOptions markOptions = new MarkerOptions();
        markOptions.draggable(true);//设置Marker可拖动
        markOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap
                .gps_point))).anchor(0.5f, 0.5f);
        //设置一个角标
        mGPSMarker = aMap.addMarker(markOptions);
        //设置marker在屏幕的像素坐标
        mGPSMarker.setPosition(new LatLng(lat, lng));
        mMapView.invalidate();
    }

    public void setLnglat(double lng, double lat) {
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(new LatLng(lat, lng));//这是地理位置，就是经纬度。
        aMap.moveCamera(cameraUpdate);//定位的方法
        drawMarkers(lng, lat);
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
        return R.layout.fragment_lnglat;
    }

    /**
     * 必须重写以下方法
     */
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 必须重写以下方法
     */
    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    /**
     * 必须重写以下方法
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 必须重写以下方法
     */
    @Override
    public void onDestroy() {
        LogUtil.e(TAG,"消灭onDestroy");
        LogUtil.e(TAG,"mMapView:"+(mMapView==null));
        super.onDestroy();
        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView=null;
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        GoUtil.goActivityForResult(getActivity(), LocationActivity.class, Constants.RequestCode.REQUEST_CODE_LOCATION);
    }
}
