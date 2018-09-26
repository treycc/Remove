package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.model.entiy.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/8/21 0021 上午 10:56
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TableAdapter extends CommonAdapter<Table> {

    public TableAdapter(Context context, List<Table> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
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

}
