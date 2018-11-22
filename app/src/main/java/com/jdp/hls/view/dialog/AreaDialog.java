package com.jdp.hls.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.EncryptUtil;
import com.jdp.hls.util.LogUtil;
import com.kingja.wheelview.AbstractWheelTextAdapter;
import com.kingja.wheelview.OnWheelChangedListener;
import com.kingja.wheelview.OnWheelScrollListener;
import com.kingja.wheelview.WheelView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/11/19 0019 下午 7:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaDialog extends CommonDialog implements OnWheelScrollListener, OnWheelChangedListener {

    @BindView(R.id.wv_province)
    WheelView wvProvince;
    @BindView(R.id.wv_city)
    WheelView wvCity;
    @BindView(R.id.wv_area)
    WheelView wvArea;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private Map<Integer, List<Integer>> sourceMap = new HashMap<>();
    private List<Area> areasList = new ArrayList<>();
    private int provinceId;
    private int cityId;
    private int areaId;
    private int streetId;
    private List<Integer> provinceIndexs = new ArrayList<>();
    private List<Integer> cityIndexs = new ArrayList<>();
    private List<Integer> areaIndexs = new ArrayList<>();
    private Integer cityIndex = -1;
    private Integer provinceIndex = -1;
    private Integer areaIndex = -1;
    private AreaWheelTextAdapter provinceAdapter;
    private AreaWheelTextAdapter cityAdapter;
    private AreaWheelTextAdapter areaAdapter;
    private OnAreaSelectedListener onAreaSelectedListener;
    private static final int VisibleItems = 5;

    @OnClick({R.id.tv_confirm, R.id.tv_cancle})
    public void onViewClicked(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.tv_confirm:
                if (onAreaSelectedListener != null) {
                    onAreaSelectedListener.onAreaSelected(getAreaByIndex(provinceIndex), getAreaByIndex(cityIndex),
                            getAreaByIndex(areaIndex));
                }
                break;
            case R.id.tv_cancle:

                break;
            default:
                break;
        }
    }

    public AreaDialog(@NonNull Context context, int provinceId, int cityId, int areaId) {
        super(context);
        LogUtil.e(TAG, "AreaDialog");
        this.provinceId = provinceId;
        this.cityId = cityId;
        this.areaId = areaId;
    }

    public Area getAreaByIndex(int areaIndex) {
        Area noArea = new Area();
        noArea.setRegionName("");
        noArea.setRegionId(0L);
        return areaIndex == -1 ? noArea : areasList.get(areaIndex);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_area;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    public void initVariable() {
        areasList = DBManager.getInstance().getAreas();
        for (int i = 0; i < areasList.size(); i++) {
            Area area = areasList.get(i);
            if (sourceMap.get(area.getParentId()) == null) {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                sourceMap.put(area.getParentId(), indexList);
            } else {
                List<Integer> indexList = sourceMap.get(area.getParentId());
                indexList.add(i);
                sourceMap.put(area.getParentId(), indexList);
            }
        }
        LogUtil.e(TAG, "provinceId:" + provinceId);
        LogUtil.e(TAG, "cityId:" + cityId);
        LogUtil.e(TAG, "areaId:" + areaId);
        if (provinceId == 0) {
            provinceIndexs = sourceMap.get(1);
            cityIndexs = sourceMap.get(areasList.get(provinceIndexs.get(0)).getRegionIntId());
            areaIndexs = sourceMap.get(areasList.get(cityIndexs.get(0)).getRegionIntId());
        } else {
            provinceIndexs = sourceMap.get(1);
            cityIndexs = sourceMap.get(provinceId)==null?new ArrayList<>():sourceMap.get(provinceId);
            areaIndexs = sourceMap.get(cityId)==null?new ArrayList<>():sourceMap.get(cityId);

            LogUtil.e(TAG, "cityIndexs:" + cityIndexs.size());
            LogUtil.e(TAG, "areaIndexs:" + areaIndexs.size());
        }

    }

    @Override
    public void initView() {

    }


    @Override
    public void initData() {
        provinceIndex = provinceIndexs.get(getProvinceIndex(provinceId));
        cityIndex = cityIndexs.get(getCityIndex(cityId));
        areaIndex = areaIndexs.get(getAresIndex(areaId));

        provinceIndex = provinceIndexs.get(getProvinceIndex(provinceId));
        provinceAdapter = new AreaWheelTextAdapter(getContext(), provinceIndexs);
        wvProvince.setVisibleItems(VisibleItems);
        wvProvince.setViewAdapter(provinceAdapter);
        wvProvince.setCurrentItem(getProvinceIndex(provinceId));

        cityAdapter = new AreaWheelTextAdapter(getContext(), cityIndexs);
        wvCity.setVisibleItems(VisibleItems);
        wvCity.setViewAdapter(cityAdapter);
        wvCity.setCurrentItem(getCityIndex(cityId));

        areaAdapter = new AreaWheelTextAdapter(getContext(), areaIndexs);
        wvArea.setVisibleItems(VisibleItems);
        wvArea.setViewAdapter(areaAdapter);
        wvArea.setCurrentItem(getAresIndex(areaId));

        wvProvince.addChangingListener(this);
        wvCity.addChangingListener(this);
        wvArea.addChangingListener(this);

        wvProvince.addScrollingListener(this);
        wvCity.addScrollingListener(this);
        wvArea.addScrollingListener(this);
    }

    public void setTextviewSize(String curriteItemText, AbstractWheelTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextColor(ContextCompat.getColor(getContext(), R.color.main));
            } else {
                textvew.setTextColor(ContextCompat.getColor(getContext(), R.color.c_6));
            }
        }
    }

    private int getCurrentIndex(List<Integer> indexList, int id) {
        for (int i = 0; i < indexList.size(); i++) {
            if (areasList.get(indexList.get(i)).getRegionId() == id) {
                return i;
            }
        }
        return 0;
    }

    private int getProvinceIndex(int areaId) {
        return getCurrentIndex(provinceIndexs, areaId);
    }

    private int getCityIndex(int areaId) {
        return getCurrentIndex(cityIndexs, areaId);
    }

    private int getAresIndex(int areaId) {
        return getCurrentIndex(areaIndexs, areaId);
    }

    @Override
    public void initNet() {

    }

    @Override
    public void onScrollingStarted(WheelView wheel) {

    }

    @Override
    public void onScrollingFinished(WheelView wheel) {
        int currentIndex = wheel.getCurrentItem();
        switch (wheel.getId()) {
            case R.id.wv_province:
                provinceIndex = provinceIndexs.get(currentIndex);
                LogUtil.e(TAG, "当前省:" + areasList.get(provinceIndex).getRegionName());
                cityIndexs = sourceMap.get(areasList.get(provinceIndex).getRegionIntId());
                if (cityIndexs != null && cityIndexs.size() > 0) {
                    areaIndexs = sourceMap.get(areasList.get(cityIndexs.get(0)).getRegionIntId());
                } else {
                    cityIndexs = new ArrayList<>();
                    areaIndexs = new ArrayList<>();
                    cityIndex = -1;
                    areaIndex = -1;
                }
                updateCitys();
                updateAreas();
                break;
            case R.id.wv_city:
                cityIndex = cityIndexs.get(currentIndex);
                LogUtil.e(TAG, "当前市:" + areasList.get(cityIndex).getRegionName());
                areaIndexs = sourceMap.get(areasList.get(cityIndex).getRegionIntId());
                updateAreas();
                break;
            case R.id.wv_area:
                areaIndex = areaIndexs.get(currentIndex);
                LogUtil.e(TAG, "当前区:" + areasList.get(areaIndex).getRegionName());
                break;
        }
    }

    private void updateAreas() {
        areaAdapter.setData(areaIndexs);
        wvArea.invalidateWheel(true);
        if (areaIndexs != null && areaIndexs.size() > 0) {
            wvArea.setCurrentItem(0, false);
        } else {
            areaIndex = -1;
        }
    }

    private void updateCitys() {
        cityAdapter.setData(cityIndexs);
        wvCity.invalidateWheel(true);
        if (cityIndexs != null && cityIndexs.size() > 0) {
            wvCity.setCurrentItem(0, false);
        }
    }

    /**
     * 滚动
     */
    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        switch (wheel.getId()) {
//            case R.id.wv_province:
//                setTextviewSize((String) provinceAdapter.getItemText(wheel.getCurrentItem()), provinceAdapter);
//                break;
//            case R.id.wv_city:
//                setTextviewSize((String) cityAdapter.getItemText(wheel.getCurrentItem()), cityAdapter);
//                break;
//            case R.id.wv_area:
//                setTextviewSize((String) areaAdapter.getItemText(wheel.getCurrentItem()), areaAdapter);
//                break;
        }

    }

    private static final int TEXT_SIZE = 15;

    class AreaWheelTextAdapter extends AbstractWheelTextAdapter {
        private List<Integer> indexList;

        protected AreaWheelTextAdapter(Context context, List<Integer> indexList) {
            super(context, R.layout.item_birth, NO_RESOURCE, 0, TEXT_SIZE, TEXT_SIZE);
            this.indexList = (indexList == null ? new ArrayList<>() : indexList);
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            return super.getItem(index, cachedView, parent);
        }

        @Override
        public int getItemsCount() {
            return indexList.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return areasList.get(indexList.get(index)).getRegionName();
        }

        public void setData(List<Integer> indexList) {
            this.indexList = (indexList == null ? new ArrayList<>() : indexList);
        }
    }

    public interface OnAreaSelectedListener {
        void onAreaSelected(Area province, Area city, Area area);
    }

    public void setOnAreaSelectedListener(OnAreaSelectedListener onAreaSelectedListener) {
        this.onAreaSelectedListener = onAreaSelectedListener;
    }
}
