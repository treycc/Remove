package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.FlowNode;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SubNodeAdapter extends CommonAdapter<FlowNode> {

    public SubNodeAdapter(Context context, List<FlowNode> datas, int itemLayoutId) {
        super(context, datas, itemLayoutId);
    }

    @Override
    public void convert(ViewHolder helper, FlowNode item) {
        helper.setText(R.id.tv_node_des, item.getNodeName());
        helper.setVisibility(R.id.iv_arrow, item.isAvailable());
        helper.setVisibility(R.id.v_div, helper.getPosition() != getCount() - 1);
    }
}
