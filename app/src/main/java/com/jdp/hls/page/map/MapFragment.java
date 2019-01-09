package com.jdp.hls.page.map;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.jdp.hls.R;
import com.jdp.hls.activity.RosterListActivity;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.RefreshRostersEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.map.KMapInfoWindowAdapter;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.page.rosteradd.RosterAddActivity;
import com.jdp.hls.page.rosterdetail.RosterDetailActivity;
import com.jdp.hls.util.AppUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SoftKeyboardUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MapFragment extends BaseFragment implements LocationSource, AMapLocationListener, GetRosterContract
        .View, AMap.OnMarkerClickListener, AMap.OnInfoWindowClickListener, AMap.OnMapClickListener, TextView
        .OnEditorActionListener {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_roster_list)
    TextView tvRosterList;
    @BindView(R.id.tv_roster_add)
    TextView tvRosterAdd;
    @BindView(R.id.map)
    MapView mMapView;
    @BindView(R.id.iv_map_showall)
    ImageView ivMapShowall;
    @BindView(R.id.iv_map_refresh)
    ImageView ivMapRefresh;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    private AMap mAMap;
    public AMapLocationClient mLocationClient;
    private OnLocationChangedListener mListener;
    private AMapLocationClientOption mLocationOption;
    @Inject
    GetRosterPresenter getRosterPresenter;
    private List<Roster> rosters;
    private Marker currentMarker;

    @OnClick({R.id.tv_roster_list, R.id.tv_roster_add, R.id.iv_map_refresh, R.id.iv_map_showall, R.id.iv_search, R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_roster_list:
                if (rosters != null) {
                    RosterListActivity.goActivity(getActivity(), rosters);
                } else {
                    ToastUtil.showText("没有花名册信息");
                }
                break;
            case R.id.tv_roster_add:
                GoUtil.goActivity(getActivity(), RosterAddActivity.class);
                break;
            case R.id.iv_map_refresh:
                initNet();
                break;
            case R.id.iv_search:
                SoftKeyboardUtil.hideSoftKeyboard(getActivity());
                String keyword = etKeyword.getText().toString().trim();
                checkData(keyword);
                break;
            case R.id.iv_map_showall:
                if (rosters != null && rosters.size() > 0) {
                    showAllRostersOnMap(rosters);
                }
                break;
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapView.onCreate(savedInstanceState);
        if (mAMap == null) {
            mAMap = mMapView.getMap();
            mAMap.setMapType(AMap.MAP_TYPE_NORMAL);// 标准地图模式
//            mAMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
            mAMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Constants.MapSetting.Lat, Constants.MapSetting
                    .Lng)));
            mAMap.getUiSettings().setZoomControlsEnabled(false);
            mAMap.setLocationSource(this);// 设置定位监听
            mAMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
            mAMap.setOnInfoWindowClickListener(this);// 设置点击InfoWindow事件监听器
            mAMap.setOnMapClickListener(this);
            mAMap.setInfoWindowAdapter(new KMapInfoWindowAdapter(getActivity()));
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
        EventBus.getDefault().register(this);
        if (getArguments() != null) {
            String param = getArguments().getString("param");
        }
    }


    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        getRosterPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        etKeyword.setOnEditorActionListener(this);
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                ivClear.setVisibility(s.length()>0?View.VISIBLE:View.GONE);
            }
        });
        tvTitle.setText(SpSir.getInstance().getProjectName());
    }

    @Override
    public void initNet() {
        getRosterPresenter.getRosterList(SpSir.getInstance().getProjectId(), SpSir.getInstance().getEmployeeId());
    }

    @Override
    protected int getContentId() {
        return R.layout.activity_roster;
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
        LogUtil.e(TAG, "消灭onDestroy");
        LogUtil.e(TAG, "mMapView:" + (mMapView == null));
        EventBus.getDefault().unregister(this);
        if (mMapView != null) {
            mMapView.onDestroy();
        }

        if (mLocationClient != null) {
            mLocationClient.onDestroy();
        }
        super.onDestroy();
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

    @Override
    public void onGetRosterdSuccess(List<Roster> rosters) {
        this.rosters = rosters;
        refreshRostersOnMap(rosters);
    }

    private void refreshRostersOnMap(List<Roster> rosters) {
        drawRostersOnMap(rosters);
        showAllRostersOnMap(rosters);
    }

    private void showAllRostersOnMap(List<Roster> rosters) {
        LatLngBounds.Builder newbounds = new LatLngBounds.Builder();
        for (Roster roster : rosters) {
            if (roster.getLatitude() == 0 || roster.getLongitude() == 0) {
                continue;
            }
            newbounds.include(new LatLng(roster.getLatitude(), roster.getLongitude()));
        }
        mAMap.animateCamera(CameraUpdateFactory.newLatLngBounds(newbounds.build(),
                AppUtil.dp2px(24)));//第二个参数为四周留空宽度
    }


    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshRosters(RefreshRostersEvent refreshRostersEvent) {
        LogUtil.e(TAG, "刷新花名册");
        initNet();
    }

    /**
     * 绘制地图锚点
     *
     * @param rosters
     */
    private void drawRostersOnMap(List<Roster> rosters) {
        mAMap.clear();
        for (Roster roster : rosters) {
            if (roster.getLatitude() == 0 || roster.getLongitude() == 0) {
                continue;
            }
            setMarket(roster);
        }
        mMapView.invalidate();
    }

    private void setMarket(Roster roster) {
        MarkerOptions markOptions = new MarkerOptions();
        markOptions.draggable(true);//设置Marker可拖动
        markOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(), roster
                .isEnterprise() ? R.mipmap
                .ic_location_enterprise : R.mipmap.ic_location_personal))).anchor(0.5f, 0.5f);
        Marker marker = mAMap.addMarker(markOptions);
        marker.setTitle(roster.getRealName());
        marker.setSnippet(roster.getHouseAddress());
        marker.setObject(roster);
        marker.setPosition(new LatLng(roster.getLatitude(), roster.getLongitude()));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        currentMarker = marker;
        marker.showInfoWindow();
        return true;
    }

    @Override
    public void onMapClick(LatLng latLng) {
        currentMarker.hideInfoWindow();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Roster roster = (Roster) marker.getObject();
        RosterDetailActivity.goActivity(getActivity(), roster);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            String keyword = v.getText().toString().trim();
            checkData(keyword);
            return true;
        }
        return false;
    }

    private void checkData(String keyword) {
        doSearch(keyword);
    }

    private void doSearch(String keyword) {
        if (rosters == null && rosters.size() == 0) {
            ToastUtil.showText("暂无花名册信息");
            return;
        }
        List<Roster> selectRosters = new ArrayList<>();
        for (Roster roster : rosters) {
            if (roster.getRealName().contains(keyword) || roster.getHouseAddress().contains(keyword) || roster
                    .getMobilePhone().contains(keyword)) {
                selectRosters.add(roster);
            }
        }
        refreshRostersOnMap(selectRosters);
    }
}
