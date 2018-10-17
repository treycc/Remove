package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.AirPhotoItem;
import com.jdp.hls.model.entiy.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/11 0011 下午 4:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoListAdapter extends CommonAdapter<AirPhotoItem> {
    private List<AirPhotoItem> selectDatas = new ArrayList<>();

    public AirPhotoListAdapter(Context context, List<AirPhotoItem> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
        selectDatas = datas;
    }
    @Override
    public AirPhotoItem getItem(int position) {
        return selectDatas.get(position);
    }

    @Override
    public int getCount() {
        return selectDatas.size();
    }

    @Override
    public void setData(List<AirPhotoItem> list) {
        selectDatas = list;
        mDatas = list;
        notifyDataSetChanged();
    }

    @Override
    public void convert(ViewHolder helper, AirPhotoItem item) {
        helper.setText(R.id.tv_airphoto_address, item.getAddress());
        helper.setText(R.id.tv_airphoto_use, item.getPropertyUseTypeName());
        helper.setText(R.id.tv_airphoto_structure, item.getBuildingStructureTypeName());
        helper.setText(R.id.tv_airphoto_name, item.getRealName());
        helper.setText(R.id.tv_airphoto_mobile, item.getMobilePhone());
        helper.setText(R.id.tv_airphoto_layer, item.getLayer() + "层");

    }

    public void addFirst(AirPhotoItem airPhotoItem) {
        mDatas.add(0, airPhotoItem);
        notifyDataSetChanged();
    }

    public void modify(AirPhotoItem airPhotoItem) {
        for (AirPhotoItem item : mDatas) {
            if (item.getAirCheckId() == airPhotoItem.getAirCheckId()) {
                item.setAddress(airPhotoItem.getAddress());
                item.setPropertyUseTypeName(airPhotoItem.getPropertyUseTypeName());
                item.setBuildingStructureTypeName(airPhotoItem.getBuildingStructureTypeName());
                item.setRealName(airPhotoItem.getRealName());
                item.setMobilePhone(airPhotoItem.getMobilePhone());
                item.setLayer(airPhotoItem.getLayer());
            }
        }
        notifyDataSetChanged();
    }

    public void search(String keyword) {
        selectDatas = new ArrayList<>();
        for (AirPhotoItem person : mDatas) {
            if (person.getRealName().contains(keyword) || person.getAddress().contains(keyword) || person
                    .getMobilePhone().contains(keyword) || person.getCusCode().contains(keyword)) {
                selectDatas.add(person);
            }
        }
        notifyDataSetChanged();
    }

}