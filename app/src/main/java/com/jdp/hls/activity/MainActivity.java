package com.jdp.hls.activity;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;

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
import com.amap.api.maps.model.MyLocationStyle;
import com.jdp.hls.R;
import com.jdp.hls.util.RegeocodeTask;

public class MainActivity extends AppCompatActivity implements LocationSource, AMapLocationListener, AMap
        .OnMapClickListener, AMap.OnCameraChangeListener, RegeocodeTask.OnLocationGetListener {
    private static final String TAG = "MainActivity";
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption;
    private MapView mMapView;
    private AMap mAMap;
    private OnLocationChangedListener mListener;
    private MarkerOptions markerOption;
    private MarkerOptions markOptions;
    private RegeocodeTask mRegeocodeTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRegeocodeTask = new RegeocodeTask(getApplicationContext());
        mRegeocodeTask.setOnLocationGetListener(this);
        mMapView = findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        markerOption = new MarkerOptions().draggable(true);

        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.getUiSettings().setRotateGesturesEnabled(false);
            mAMap.moveCamera(CameraUpdateFactory.zoomBy(6));
            setUpMap();
        }
    }

    private void setUpMap() {
        mAMap.setOnMapClickListener(this);
        mAMap.setLocationSource(this);// 设置定位监听
        //设置地图拖动监听
        mAMap.setOnCameraChangeListener(this);
        mAMap.setMyLocationEnabled(true);//true激活定位
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
//        showLoactionStyle();
    }

    /**
     * 显示定位图标
     */
    private void showLoactionStyle() {
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(
                BitmapDescriptorFactory.fromResource(R.mipmap.gps_point));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(0);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        // 将自定义的 myLocationStyle 对象添加到地图上
        mAMap.setMyLocationStyle(myLocationStyle);
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setOnceLocation(true);
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();
    }

    /**
     * 激活定位
     */
    @Override
    public void activate(OnLocationChangedListener listener) {
        Log.e(TAG, "激活定位: ");
        mListener = listener;
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
        mListener = null;
        if (mLocationClient != null) {
            mLocationClient.stopLocation();
            mLocationClient.onDestroy();
        }
        mLocationClient = null;
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
        deactivate();
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        Log.e(TAG, "aMapLocation: " + amapLocation.getAddress());
        LatLng la = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
        setMarket(la, amapLocation.getCity(), amapLocation.getAddress());
        if (mListener != null && amapLocation != null) {
            if (amapLocation != null && amapLocation.getErrorCode() == 0) {
                mListener.onLocationChanged(amapLocation);// 显示系统小蓝点
            } else {
                String errText = "定位失败," + amapLocation.getErrorCode() + ": "
                        + amapLocation.getErrorInfo();
                Log.e("AmapErr", errText);
            }
        }
    }

    private Marker mGPSMarker;             //定位位置显示

    private void setMarket(LatLng latLng, String city, String address) {
        if (mGPSMarker != null) {
            mGPSMarker.remove();
        }
        //获取屏幕宽高
        WindowManager wm = this.getWindowManager();
        int width = (wm.getDefaultDisplay().getWidth()) / 2;
        int height = ((wm.getDefaultDisplay().getHeight()) / 2) - 80;
        markOptions = new MarkerOptions();
        markOptions.draggable(true);//设置Marker可拖动
        markOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap
                .gps_point))).anchor(0.5f, 0.7f);
        //设置一个角标
        mGPSMarker = mAMap.addMarker(markOptions);
        //设置marker在屏幕的像素坐标
        mGPSMarker.setPosition(latLng);
        mGPSMarker.setTitle(city);
        mGPSMarker.setSnippet(address);
        //设置像素坐标
        mGPSMarker.setPositionByPixels(width, height);
        if (!TextUtils.isEmpty(address)) {
            mGPSMarker.showInfoWindow();
        }
        mMapView.invalidate();
    }

    /*地图点击*/
    @Override
    public void onMapClick(LatLng latLng) {
        Log.e(TAG, "地图点击: " + latLng.latitude + " : " + latLng.longitude);
    }

    /**
     * 拖动地图
     *
     * @param cameraPosition
     */
    @Override
    public void onCameraChange(CameraPosition cameraPosition) {

    }

    @Override
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        LatLng latLng = cameraPosition.target;
        mRegeocodeTask.search(latLng.latitude, latLng.longitude);
    }

    @Override
    public void onLocationGet(RegeocodeTask.PositionEntity entity) {
        Log.e(TAG, "onLocationGet: " + entity.address);
    }

    @Override
    public void onRegecodeGet(RegeocodeTask.PositionEntity entity) {
        Log.e(TAG, "onRegecodeGet: " + entity.address);
        setMarket(new LatLng(entity.latitue, entity.longitude), entity.city, entity.address);
    }
}
