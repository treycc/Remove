package com.kingja.zhongminremove.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.activity.RosterListActivity;
import com.kingja.zhongminremove.base.BaseFragment;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.util.GoUtil;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MapFragment extends BaseFragment implements LocationSource, AMapLocationListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right_oper)
    TextView tvRightOper;
    @BindView(R.id.map)
    MapView mMapView;
    private AMap mAMap;
    public AMapLocationClient mLocationClient;
    private OnLocationChangedListener mListener;
    private AMapLocationClientOption mLocationOption;

    @OnClick({R.id.tv_right_oper})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_right_oper:
                GoUtil.goActivity(getActivity(), RosterListActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        AMap aMap = mMapView.getMap();
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.getUiSettings().setRotateGesturesEnabled(false);
            mAMap.moveCamera(CameraUpdateFactory.zoomBy(6));
            mAMap.setLocationSource(this);// 设置定位监听
            //设置地图拖动监听
//            mAMap.setOnCameraChangeListener(this);
            mAMap.setMyLocationEnabled(true);//true激活定位
            mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        }

    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
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
    protected void initDate() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_map;
    }

    /**
     * 必须重写以下方法
     */
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        if (mLocationClient != null) {
            mLocationClient.onDestroy();
        }
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

    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getActivity());
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

    @Override
    public void onLocationChanged(AMapLocation amapLocation) {
        Log.e(TAG, "aMapLocation: " + amapLocation.getAddress());
        LatLng la = new LatLng(amapLocation.getLatitude(), amapLocation.getLongitude());
//        setMarket(la, amapLocation.getCity(), amapLocation.getAddress());
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
}
