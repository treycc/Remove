package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.ReportItem;
import com.jdp.hls.view.FixedListView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ReportAdapter extends BaseLvAdapter<ReportItem> {
    public ReportAdapter(Context context, List<ReportItem> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_report, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        return convertView;
    }


    public class ViewHolder {
        public final View root;
        public TextView tv_node_des;
        public ImageView iv_arrow;
        public FixedListView lv_node_sub;
        public View v_div;

        public ViewHolder(View root) {
            this.root = root;
            tv_node_des = root.findViewById(R.id.tv_node_des);
            iv_arrow = root.findViewById(R.id.iv_arrow);
            lv_node_sub = root.findViewById(R.id.lv_node_sub);
            v_div = root.findViewById(R.id.v_div);
        }
    }

    @Override
    public int getCount() {
        return 10;
    }
}
