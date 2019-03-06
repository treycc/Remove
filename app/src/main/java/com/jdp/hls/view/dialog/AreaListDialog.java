package com.jdp.hls.view.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2019/1/17 0017 下午 3:28
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaListDialog extends BaseWheelListDialog<Area> {
    private Map<Integer, List<Integer>> sourceMap = new HashMap<>();
    private List<Area> areasList = new ArrayList<>();
    private int regionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected boolean clickable(Area area) {
        return !(area.getRegionId() == regionId);
    }

    public AreaListDialog(@NonNull Context context) {
        super(context);
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
        LogUtil.e(TAG, "sourceMap:" + sourceMap.size());
    }

    public boolean hasData(int parentId) {
        List<Integer> integerList = sourceMap.get(parentId);
        return integerList != null && integerList.size() > 0;
    }

    public void fillData(int areaLevel, int parentId, int regionId) {
        this.regionId = regionId;
        List<Integer> dataIndexList = sourceMap.get(parentId);
        List<Area> areaList = new ArrayList<>();
        for (Integer index : dataIndexList) {
            areaList.add(areasList.get(index));
        }
        //增加不限项
        Area noLimitArea = new Area();
        noLimitArea.setLevel(areaLevel);
        noLimitArea.setRegionId(Long.valueOf(0));
        noLimitArea.setRegionName("不限");
        areaList.add(0, noLimitArea);

        for (int i = 0; i < areaList.size(); i++) {
            if (areaList.get(i).getRegionIntId() == regionId) {
                setData(areaList, i);
                return;
            }
        }
        setData(areaList, 0);
    }

    public void fillData(List<Area> areaList, int regionId) {
        this.regionId = regionId;
        for (int i = 0; i < areaList.size(); i++) {
            //设置上次选中项
            if (areaList.get(i).getRegionIntId() == regionId) {
                setData(areaList, i);
                return;
            }
        }
        setData(areaList, 0);
    }


    private List<Area> getData(int parentId) {
        List<Integer> dataIndexList = sourceMap.get(parentId);
        List<Area> resultList = new ArrayList<>();
        for (Integer index : dataIndexList) {
            resultList.add(areasList.get(index));
        }
        return resultList;
    }

    @Override
    protected CharSequence getItemText(List<Area> list, int index) {
        return list.get(index).getRegionName();
    }

}
