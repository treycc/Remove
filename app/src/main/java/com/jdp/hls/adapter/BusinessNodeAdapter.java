package com.jdp.hls.adapter;

import android.content.Context;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.BusinessNode;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessNodeAdapter extends CommonAdapter<BusinessNode> {
    private int roleCode;

    public BusinessNodeAdapter(Context context, List<BusinessNode> datas, int itemLayoutId, int roleCode) {
        super(context, datas, itemLayoutId);
        this.roleCode = roleCode;
    }

    @Override
    public void convert(ViewHolder helper, BusinessNode item) {
        helper.setText(R.id.tv_node_des, item.getNodeDes());
        helper.setVisibility(R.id.iv_arrow_right,roleCode>= item.getNodeCode());
    }
}
