package com.jdp.hls.page.geography;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.UrlTileProvider;
import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.business.detail.personal.DetailPersonalActivity;
import com.jdp.hls.page.supervise.project.contrast.VRDetailActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;

import java.net.URL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2019/2/25 0025 下午 2:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GeographyActivity extends BaseTitleActivity {
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
    private AMap aMap;
    final String url = "content/MapImg/tiles/%d/%d_%d.png";

    //    final String url = "http://a.tile.openstreetmap.org/%d/%d/%d.png";

    @OnClick({R.id.iv_map_tileoverlay, R.id.iv_map_showall, R.id.iv_map_selectProjects, R.id.iv_map_refresh})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_map_tileoverlay:
                ToastUtil.showText("覆盖物");
                break;
            case R.id.iv_map_showall:
                ToastUtil.showText("显示全部");
                break;
            case R.id.iv_map_selectProjects:
                ToastUtil.showText("选择项目");
                break;
            case R.id.iv_map_refresh:
                ToastUtil.showText("刷新");
                break;
        }
    }

    @Override
    public void initVariable() {

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
    }

    @Override
    protected String getContentTitle() {
        return "地理信息";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("列表", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ToastUtil.showText("列表");
            }
        });
    }

    @Override
    public void initNet() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapView.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        if (aMap == null) {
            aMap = mapView.getMap();
            setUpMap();
        }
    }

    private void setUpMap() {
        aMap.moveCamera(CameraUpdateFactory.zoomTo(4));
        aMap.setMapTextZIndex(2);
        aMap.addTileOverlay(new TileOverlayOptions().tileProvider(new UrlTileProvider(256, 256) {
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
        })
                .diskCacheEnabled(true)
                .diskCacheDir("/storage/emulated/0/amap/cache")
                .diskCacheSize(100000)
                .memoryCacheEnabled(true)
                .memCacheSize(100000));
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
}
