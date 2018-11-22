package com.jdp.hls.view.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.injector.component.AppComponent;
import com.kingja.wheelview.AbstractWheelTextAdapter;
import com.kingja.wheelview.OnSimleWheelScrollListener;
import com.kingja.wheelview.WheelView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/11/20 0020 下午 5:49
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StreetDialog extends CommonDialog {
    @BindView(R.id.wv_street)
    WheelView wvStreet;
    @BindView(R.id.tv_cancle)
    TextView tvCancle;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    private int areaId;
    private int streetId;
    private List<Area> streets = new ArrayList<>();
    private static final int VisibleItems = 5;
    private int streetIndex;
    private OnAreaSelectedListener onAreaSelectedListener;
    private AreaWheelTextAdapter streetAdapter;

    public StreetDialog(@NonNull Context context, int areaId, int streetId) {
        super(context);
        this.areaId = areaId;
        this.streetId = streetId;
    }

    public void nodifySetData(int areaId, int streetId) {
        this.areaId = areaId;
        this.streetId = streetId;
        streets = DBManager.getInstance().getStreets(areaId);
        streetIndex = getCurrentIndex(streetId);
        if (wvStreet != null) {
            streetAdapter.setData(streets);
            wvStreet.invalidateWheel(true);
            wvStreet.setCurrentItem(streetIndex);
        }
    }

    @OnClick({R.id.tv_confirm, R.id.tv_cancle})
    public void onViewClicked(View view) {
        dismiss();
        switch (view.getId()) {
            case R.id.tv_confirm:
                if (onAreaSelectedListener != null) {
                    onAreaSelectedListener.onAreaSelected(streets.get(streetIndex));
                }
                break;
            case R.id.tv_cancle:

                break;
            default:
                break;
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_street;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    public void initVariable() {
        streets = DBManager.getInstance().getStreets(areaId);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        streetIndex = getCurrentIndex(streetId);
        streetAdapter = new AreaWheelTextAdapter(getContext(), streets);
        wvStreet.setVisibleItems(VisibleItems);
        wvStreet.setViewAdapter(streetAdapter);
        wvStreet.setCurrentItem(streetIndex);
        wvStreet.addScrollingListener(new OnSimleWheelScrollListener() {
            @Override
            public void onScrollingFinished(WheelView wheel) {
                streetIndex = wheel.getCurrentItem();
            }
        });
    }


    private int getCurrentIndex(int streetId) {
        for (int i = 0; i < streets.size(); i++) {
            if (streets.get(i).getRegionId() == streetId) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void initNet() {

    }

    private static final int TEXT_SIZE = 15;

    class AreaWheelTextAdapter extends AbstractWheelTextAdapter {
        private List<Area> streetList;

        protected AreaWheelTextAdapter(Context context, List<Area> streetList) {
            super(context, R.layout.item_birth, NO_RESOURCE, 0, TEXT_SIZE, TEXT_SIZE);
            this.streetList = (streetList == null ? new ArrayList<>() : streetList);
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public int getItemsCount() {
            return streetList.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return streetList.get(index).getRegionName();
        }

        public void setData(List<Area> streetList) {
            this.streetList = streetList;
        }
    }

    public interface OnAreaSelectedListener {
        void onAreaSelected(Area street);
    }

    public void setOnAreaSelectedListener(OnAreaSelectedListener onAreaSelectedListener) {
        this.onAreaSelectedListener = onAreaSelectedListener;
    }
}
