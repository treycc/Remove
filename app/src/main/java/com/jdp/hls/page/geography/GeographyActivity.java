package com.jdp.hls.page.geography;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.UrlTileProvider;
import com.jdp.hls.R;
import com.jdp.hls.activity.RosterListActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.SwitchProjectEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.map.KMapInfoWindowAdapter;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.page.projects.ProjectListActivity;
import com.jdp.hls.page.rosterdetail.detail.company.RosterCompanyDetailActivity;
import com.jdp.hls.page.rosterdetail.detail.personal.RosterPersonalDetailActivity;
import com.jdp.hls.util.AppUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.LatLngUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2019/2/25 0025 下午 2:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GeographyActivity extends BaseTitleActivity implements AMap.OnMarkerClickListener, AMap
        .OnInfoWindowClickListener, AMap.OnMapClickListener, GetGeographyRosterListContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.map)
    MapView mapView;
    @BindView(R.id.iv_map_refresh)
    ImageView ivMapRefresh;
    @BindView(R.id.iv_map_tileoverlay)
    ImageView ivMapTileoverlay;
    @BindView(R.id.iv_map_showall)
    ImageView ivMapShowall;
    private AMap mAMap;
    final String url = "content/MapImg/tiles/%d/%d_%d.png";
    private TileOverlay tileOverlay;
    private boolean showTileOverlay;
    private TileOverlayOptions tileOverlayOptions;
    private Marker currentMarker;
    @Inject
    GetGeographyRosterListPresenter getGeographyRosterListPresenter;
    private List<Roster> rosters;
    //    final String url = "http://a.tile.openstreetmap.org/%d/%d/%d.png";

    @OnClick({R.id.iv_map_tileoverlay, R.id.iv_map_showall, R.id.iv_map_refresh, R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_map_tileoverlay:
                switchTileOverlay();
                break;
            case R.id.iv_map_showall:
                if (rosters != null && rosters.size() > 0) {
                    showAllRostersOnMap(rosters);
                }
                break;
            case R.id.iv_map_refresh:
                initNet();
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
        }
    }

    private void switchTileOverlay() {
        showTileOverlay = !showTileOverlay;
        ivMapTileoverlay.setBackgroundResource(showTileOverlay ? R.mipmap.ic_tileoverlay_sel : R.mipmap
                .ic_tileoverlay_nor);
        if (showTileOverlay) {
            tileOverlay = mAMap.addTileOverlay(tileOverlayOptions);
        } else {
            tileOverlay.remove();
        }
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_geography;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        getGeographyRosterListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return SpSir.getInstance().getProjectName();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("项目选择", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(GeographyActivity.this, ProjectListActivity.class);
            }
        });
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
                String keyword = etKeyword.getText().toString().trim();
                checkData(keyword);
            }
        });
    }
    private void checkData(String keyword) {
        doSearch(keyword);
    }

    private void doSearch(String keyword) {
        if (rosters == null || rosters.size() == 0) {
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
    @Override
    public void initNet() {
        getGeographyRosterListPresenter.getGeographyRosterList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        initMap();
    }

    private void initMap() {
        if (mAMap == null) {
            mAMap = mapView.getMap();
            mAMap.moveCamera(CameraUpdateFactory.zoomTo(4));
            mAMap.setMapTextZIndex(2);

            mAMap.setMapType(AMap.MAP_TYPE_NORMAL);// 标准地图模式
            mAMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(Constants.MapSetting.Lat, Constants.MapSetting
                    .Lng)));
            mAMap.getUiSettings().setZoomControlsEnabled(true);
            mAMap.setOnMarkerClickListener(this);// 设置点击marker事件监听器
            mAMap.setOnInfoWindowClickListener(this);// 设置点击InfoWindow事件监听器
            mAMap.setOnMapClickListener(this);
            mAMap.setInfoWindowAdapter(new KMapInfoWindowAdapter(this));

            initTileOverlay();
        }
    }

    private void initTileOverlay() {
        tileOverlayOptions = new TileOverlayOptions().tileProvider(new UrlTileProvider(256, 256) {
            @Override
            public URL getTileUrl(int x, int y, int zoom) {
                try {
                    Log.e("getTileUrl", "URL:" + new URL(String.format(SpSir.getInstance().getServerName() + url,
                            zoom, x, y)).toString());
                    return new URL(String.format(SpSir.getInstance().getServerName() + url, zoom, x, y));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
        tileOverlayOptions
                .diskCacheEnabled(true)
                .diskCacheDir("/storage/emulated/0/amap/cache")
                .diskCacheSize(100000)
                .memoryCacheEnabled(true)
                .memCacheSize(100000);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        if (mapView != null) {
            mapView.onDestroy();
        }
        super.onDestroy();
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        currentMarker = marker;
        marker.showInfoWindow();
        return true;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Roster roster = (Roster) marker.getObject();
        if (roster.isEnterprise()) {
            RosterCompanyDetailActivity.goActivity(GeographyActivity.this, roster.getHouseId());
        } else {
            RosterPersonalDetailActivity.goActivity(GeographyActivity.this, roster.getHouseId());
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        currentMarker.hideInfoWindow();
    }

    @Override
    public void onGetGeographyRosterListSuccess(List<Roster> rosterList) {
        this.rosters = rosterList;
        refreshRostersOnMap(rosterList);
    }

    private void refreshRostersOnMap(List<Roster> rosters) {
        drawRostersOnMap(rosters);
        showAllRostersOnMap(rosters);
    }

    private void showAllRostersOnMap(List<Roster> rosters) {
        LatLngBounds.Builder newbounds = new LatLngBounds.Builder();
        for (Roster roster : rosters) {
            if (LatLngUtil.isChinaLatLng(roster.getLatitude(), roster.getLongitude())) {
                newbounds.include(new LatLng(roster.getLatitude(), roster.getLongitude()));
            }
        }
        mAMap.animateCamera(CameraUpdateFactory.newLatLngBounds(newbounds.build(),
                AppUtil.dp2px(24)));//第二个参数为四周留空宽度
    }

    private void drawRostersOnMap(List<Roster> rosters) {
        mAMap.clear();
        for (Roster roster : rosters) {
            if (LatLngUtil.isChinaLatLng(roster.getLatitude(), roster.getLongitude())) {
                setMarket(roster);
            }
        }
        mapView.invalidate();
    }

    private void setMarket(Roster roster) {
        MarkerOptions markOptions = new MarkerOptions();
        markOptions.draggable(true);//设置Marker可拖动
        markOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),
                R.mipmap.ic_protocol_generation_cpy))).anchor(0.5f, 0.5f);

//        markOptions.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory.decodeResource(getResources(),
//                getRosterIconf(roster.isEnterprise(), roster.getStatusId())))).anchor(0.5f, 0.5f);
        Marker marker = mAMap.addMarker(markOptions);
        marker.setTitle(roster.getRealName());
        marker.setSnippet(roster.getHouseAddress());
        marker.setObject(roster);
        marker.setPosition(new LatLng(roster.getLatitude(), roster.getLongitude()));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void switchProject(SwitchProjectEvent event) {
        setContentTitle(SpSir.getInstance().getProjectName());
        initNet();
    }
}
