package com.jdp.hls.fragment;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.jdp.hls.R;
import com.jdp.hls.base.App;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.RegeocodeTask;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LocationFragment extends BaseFragment implements LocationSource, AMapLocationListener, AMap
        .OnCameraChangeListener, RegeocodeTask.OnLocationGetListener {
    @BindView(R.id.map_fragment)
    MapView mMapView;
    private AMap mAMap;
    public AMapLocationClient mLocationClient;
    private OnLocationChangedListener mOnLocationChangedListener;
    private RegeocodeTask mRegeocodeTask;
    private double currentLng = -1;//经度
    private double currentLat = -1;//纬度
    private OnLocationGetListener onLocationGetListener;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        //地理位置逆编码
        mRegeocodeTask = new RegeocodeTask(App.getContext());
        mRegeocodeTask.setOnLocationGetListener(this);
        initMap();
    }

    private void initMap() {
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Constants.MapSetting.Lat, Constants.MapSetting
                    .Lng)));
            mAMap.moveCamera(CameraUpdateFactory.zoomBy(Constants.MapSetting.Zoom));
            //设置定位监听，监听开始定位和结束定位
            mAMap.setLocationSource(this);
            //设置地图拖动监听
            mAMap.setOnCameraChangeListener(this);
            //激活定位
            mAMap.setMyLocationEnabled(true);
            //设置默认定位按钮是否显示
            mAMap.getUiSettings().setMyLocationButtonEnabled(true);
            //设置缩放按钮是否显示
            mAMap.getUiSettings().setZoomControlsEnabled(false);
        }
    }

    public static LocationFragment newInstance() {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putString("param", null);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            String param = getArguments().getString("param");
        }
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
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_location;
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
        if (mMapView != null) {
            mMapView.onDestroy();
            mMapView = null;
        }
        if (mLocationClient != null) {
            mLocationClient.onDestroy();
        }
        if (mOnLocationChangedListener != null) {
            mOnLocationChangedListener = null;
        }
        super.onDestroy();
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        Log.e(TAG, "激活定位: ");
        mOnLocationChangedListener = listener;
        if (mLocationClient == null) {
            initLocation();
        }
    }

    /**
     * 停止定位
     */
    @Override
    public void deactivate() {
        Log.e(TAG, "停止定位: ");
        mOnLocationChangedListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(App.getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        AMapLocationClientOption mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    /**
     * 定位改变
     */
    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        Log.e(TAG, "开始定位: " + amapLocation.getAddress());
        LatLng la = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
        currentLat = la.latitude;
        currentLng = la.longitude;
        if (onLocationGetListener != null) {
            onLocationGetListener.onLoactionGet(currentLng, currentLat);
        }
        setMarket(la, amapLocation.getCity(), amapLocation.getAddress());
        if (mOnLocationChangedListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                mOnLocationChangedListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    /**
     * 拖动屏幕
     */
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    /**
     * 拖动屏幕结束
     */
    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        LatLng latLng = cameraPosition.target;
        mRegeocodeTask.search(latLng.latitude, latLng.longitude);
    }

    private Marker mGPSMarker;             //定位位置显示

    private void setMarket(LatLng latLng, String city, String address) {
        if (mGPSMarker != null) {
            mGPSMarker.remove();
        }
        int width = mMapView.getMeasuredWidth() / 2;
        int height = mMapView.getMeasuredHeight() / 2;
        MarkerOptions markOptions = new MarkerOptions();
        markOptions.draggable(true);//设置Marker可拖动
        markOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap
                .gps_point))).anchor(0.5f, 0.5f);
        //设置一个角标
        mGPSMarker = mAMap.addMarker(markOptions);
        mGPSMarker.setTitle(city);
        mGPSMarker.setSnippet(address);
        //设置marker在屏幕的像素坐标
        mGPSMarker.setPositionByPixels(width, height);
        if (!TextUtils.isEmpty(address)) {
            mGPSMarker.showInfoWindow();
        }
        mMapView.invalidate();
    }

    @Override
    public void onLocationGet(RegeocodeTask.PositionEntity entity) {
        Log.e(TAG, "onLocationGet: " + entity.address);
    }

    @Override
    public void onRegecodeGet(RegeocodeTask.PositionEntity entity) {
        Log.e(TAG, "onRegecodeGet: " + entity.address);
        Log.e(TAG, " 经度: " + entity.longitude + " 纬度: " + entity.latitue);
        setMarket(new LatLng(entity.latitue, entity.longitude), entity.city, entity.address);
        currentLat = entity.latitue;
        currentLng = entity.longitude;
        if (onLocationGetListener != null) {
            onLocationGetListener.onLoactionGet(currentLng, currentLat);
        }
    }

    public interface OnLocationGetListener {
        void onLoactionGet(double lng, double lat);
    }

    public void setOnLocationGetListener(OnLocationGetListener onLocationGetListener) {
        this.onLocationGetListener = onLocationGetListener;
    }

}
