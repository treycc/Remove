package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.AirPhotoBuilding;
import com.jdp.hls.model.entiy.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/24 0024 下午 5:25
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoPersonAdapter extends CommonAdapter<AirPhotoBuilding> {
    private List<AirPhotoBuilding> selectDatas = new ArrayList<>();

    public AirPhotoPersonAdapter(Context context, List<AirPhotoBuilding> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
        selectDatas = datas;
    }

    @Override
    public void convert(ViewHolder helper, AirPhotoBuilding item) {
        helper.setText(R.id.tv_airphoto_cusCode, item.getCusCode());
        helper.setText(R.id.tv_airphoto_realName, item.getRealName());
        helper.setText(R.id.tv_airphoto_mobilePhone, item.getMobilePhone());
        helper.setText(R.id.tv_airphoto_address, item.getAddress());
    }

    public void searchPerson(String keyword) {
        selectDatas = new ArrayList<>();
        for (AirPhotoBuilding airPhotoBuilding : mDatas) {
            if (airPhotoBuilding.getRealName().contains(keyword) || airPhotoBuilding.getMobilePhone().contains
                    (keyword) || airPhotoBuilding.getAddress().contains(keyword) || airPhotoBuilding.getCusCode()
                    .contains(keyword)) {
                selectDatas.add(airPhotoBuilding);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public AirPhotoBuilding getItem(int position) {
        return selectDatas.get(position);
    }

    @Override
    public int getCount() {
        return selectDatas.size();
    }

    @Override
    public void setData(List<AirPhotoBuilding> list) {
        selectDatas = list;
        mDatas = list;
        notifyDataSetChanged();
    }
}