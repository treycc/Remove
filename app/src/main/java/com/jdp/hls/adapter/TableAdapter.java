package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.model.entiy.Table;
import com.jdp.hls.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/21 0021 上午 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TableAdapter extends CommonAdapter<Table> {
    private List<Table> selectDatas = new ArrayList<>();

    public TableAdapter(Context context, List<Table> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
        selectDatas = datas;
    }

    @Override
    public void convert(ViewHolder helper, Table item) {
        helper.setText(R.id.tv_table_number, item.getSysCode());
        helper.setText(R.id.tv_table_name, item.getRealName());
        helper.setText(R.id.tv_table_mobile, item.getMobilePhone());
        helper.setText(R.id.tv_table_address, item.getAddress());
        helper.setText(R.id.tv_table_node, item.getStatusDesc());
        helper.setText(R.id.tv_table_area, item.getPropertyArea());
        helper.setText(R.id.tv_table_certNumber, item.getSysCode());
        helper.setText(R.id.tv_table_payType, item.getPayTypeName());
        helper.setVisibility(R.id.set_status_back, item.isFlowBack());
        helper.setVisibility(R.id.set_status_banned, item.isBanned());
    }

    public void searchPerson(String keyword) {
        selectDatas = new ArrayList<>();
        for (Table table : mDatas) {
            if (table.getRealName().contains(keyword) || table.getAddress().contains(keyword) || table.getMobilePhone
                    ().contains(keyword) || table.getSysCode().contains(keyword)) {
                selectDatas.add(table);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public Table getItem(int position) {
        return selectDatas.get(position);
    }

    @Override
    public int getCount() {
        return selectDatas.size();
    }

    @Override
    public void setData(List<Table> list) {
        selectDatas = list;
        mDatas = list;
        notifyDataSetChanged();
    }

    public void filterBuildingType(int buildingType) {
        if (buildingType == -1) {
            selectDatas = mDatas;
            return;
        }
        selectDatas = new ArrayList<>();
        for (Table table : mDatas) {
            if (table.getBuildingType() == buildingType) {
                selectDatas.add(table);
            }
        }
        notifyDataSetChanged();
    }

    public void filterStatusId(int statusId) {
        if (statusId == -1) {
            selectDatas = mDatas;
            return;
        }
        selectDatas = new ArrayList<>();
        for (Table table : mDatas) {
            if (table.getStatusId() == statusId) {
                selectDatas.add(table);
            }
        }
        notifyDataSetChanged();
    }

    public void filterDate(long startDate, long endDate) {
        selectDatas = new ArrayList<>();
        if (startDate == -1) {
            startDate = 0;
        }
        if (endDate == -1) {
            endDate = Long.MAX_VALUE;
        }

        for (Table table : mDatas) {
            if (DateUtil.getMillSeconds(table.getCreateDatetime()) >= startDate && DateUtil.getMillSeconds(table
                    .getCreateDatetime()) <= endDate) {
                selectDatas.add(table);
            }
        }
        notifyDataSetChanged();
    }


}
