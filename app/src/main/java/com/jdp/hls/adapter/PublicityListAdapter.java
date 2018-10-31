package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
import com.jdp.hls.constant.Status;
import com.jdp.hls.model.entiy.PublicityItem;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.SpSir;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/24 0024 下午 6:36
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityListAdapter extends CommonAdapter<PublicityItem> {
    private List<PublicityItem> selectDatas = new ArrayList<>();

    public PublicityListAdapter(Context context, List<PublicityItem> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
        selectDatas = datas;
    }

    @Override
    public void convert(ViewHolder helper, PublicityItem item) {
        helper.setText(R.id.tv_publicity_type, item.getPubTypeDesc());
        helper.setText(R.id.tv_publicity_number, item.getBatchName());
        helper.setText(R.id.tv_publicity_count, String.valueOf(item.getTotalQuantity()));
        helper.setText(R.id.tv_publicity_operater, SpSir.getInstance().getRealName());
        helper.setText(R.id.tv_publicity_startDate, DateUtil.getShortDate(item.getStartDate()));
        helper.setText(R.id.tv_publicity_endDate, DateUtil.getShortDate(item.getEndDate()));
        helper.setText(R.id.tv_publicity_des, item.getDescriptiton());
        helper.setVisibility(R.id.iv_publicity_hasImg, false);
        helper.setBackgroundResource(R.id.iv_publicity_buildingType, (item.getBuildingType() == Status
                .BuildingType.PERSONAL) ? R.mipmap.ic_buildingtype_personal : R.mipmap.ic_buildingtype_company);
    }

    public void addFirst(PublicityItem publicityItem) {
        mDatas.add(0, publicityItem);
        notifyDataSetChanged();
    }

    public void modify(PublicityItem publicityItem) {
        for (PublicityItem item : mDatas) {
            if (item.getPubId() == publicityItem.getPubId()) {
                item.setBatchName(publicityItem.getBatchName());
                item.setTotalQuantity(publicityItem.getTotalQuantity());
                item.setStartDate(publicityItem.getStartDate());
                item.setEndDate(publicityItem.getEndDate());
                item.setDescriptiton(publicityItem.getDescriptiton());
                item.setBuildingType(publicityItem.getBuildingType());
            }
        }
        notifyDataSetChanged();
    }

    public void searchPerson(String keyword) {
        selectDatas = new ArrayList<>();
        for (PublicityItem person : mDatas) {
            if (person.getBatchName().contains(keyword) || person.getPubTypeDesc().contains(keyword)) {
                selectDatas.add(person);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public PublicityItem getItem(int position) {
        return selectDatas.get(position);
    }

    @Override
    public int getCount() {
        return selectDatas.size();
    }

    @Override
    public void setData(List<PublicityItem> list) {
        selectDatas = list;
        mDatas = list;
        notifyDataSetChanged();
    }
}
