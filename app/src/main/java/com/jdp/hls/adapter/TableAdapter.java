package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
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
    private int buildingType = -1;
    private int statusId = -1;
    private long startDate = 0;
    private long endDate = Long.MAX_VALUE;
    private String keyword="";

    public TableAdapter(Context context, List<Table> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
        selectDatas = datas;
    }

    @Override
    public void convert(ViewHolder helper, Table item) {
        helper.setText(R.id.tv_table_number, item.getSysCode());
        helper.setText(R.id.tv_table_cusCode, item.getCusCode());
        helper.setText(R.id.tv_table_name, item.getRealName());
        helper.setText(R.id.tv_table_mobile, item.getMobilePhone());
        helper.setText(R.id.tv_table_address, item.getAddress());
        helper.setText(R.id.tv_table_node, item.getStatusDesc());
        helper.setText(R.id.tv_table_area, item.getPropertyArea());
        helper.setText(R.id.tv_table_certNumber, item.getCertNum());
        helper.setText(R.id.tv_table_payType, item.getPayTypeName());
        helper.setVisibility(R.id.set_status_back, item.isFlowBack());
        helper.setVisibility(R.id.set_status_banned, item.isBanned());
    }

    public void searchPerson(String keyword) {
        this.keyword = keyword;
        filterTalbe();
    }

    public void filterTalbe() {
        selectDatas = new ArrayList<>();
        for (Table table : mDatas) {
            if (checkKeyword(table)
                    && checkBuildingType(table.getBuildingType())
                    && checkStatusId(table.getStatusId())
                    && checkStartDate(DateUtil.getMillSeconds(table.getCreateDatetime()))
                    && checkEndDate(DateUtil.getMillSeconds(table.getCreateDatetime()))) {
                selectDatas.add(table);
            }
        }
        notifyDataSetChanged();
    }

    private boolean checkKeyword(Table table) {
        return table.getRealName().contains(keyword) || table.getAddress().contains(keyword) || table.getMobilePhone
                ().contains(keyword) || table.getCusCode().contains(keyword);
    }

    private boolean checkBuildingType(int searchBuildingType) {
        return buildingType == -1 || buildingType == searchBuildingType;
    }

    private boolean checkStatusId(int searchStatusId) {
        return statusId == -1 || statusId == searchStatusId;
    }

    private boolean checkStartDate(long searchStartDate) {
        return startDate == 0 || searchStartDate >= startDate;
    }

    private boolean checkEndDate(long searchEndDate) {
        return endDate == Long.MAX_VALUE || searchEndDate <= endDate;
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
        this.buildingType = buildingType;
        filterTalbe();
    }

    public void filterStatusId(int statusId) {
        this.statusId = statusId;
        filterTalbe();
    }

    public void filterDate(long startDate, long endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        selectDatas = new ArrayList<>();
        if (startDate == -1) {
            this.startDate = 0;
        }
        if (endDate == -1) {
            this.endDate = Long.MAX_VALUE;
        }
        filterTalbe();
    }


}
