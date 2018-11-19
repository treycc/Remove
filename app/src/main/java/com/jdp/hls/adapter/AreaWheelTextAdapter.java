package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.greendaobean.Area;
import com.kingja.wheelview.AbstractWheelTextAdapter;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/19 0019 下午 8:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaWheelTextAdapter extends AbstractWheelTextAdapter {
    private final List<Area> areaList;

    protected AreaWheelTextAdapter(Context context, List<Area>areaList) {
        super(context);
        this.areaList = areaList;
    }

    @Override
    protected CharSequence getItemText(int index) {
        return areaList.get(index).getRegionName();
    }

    @Override
    public int getItemsCount() {
        return areaList.size();
    }
}
