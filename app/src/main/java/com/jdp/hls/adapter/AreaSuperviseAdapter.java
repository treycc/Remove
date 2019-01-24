package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jdp.hls.R;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSuperviseAdapter extends BaseLvAdapter<AreaSupervise> {
    public AreaSuperviseAdapter(Context context, List<AreaSupervise> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_common_tv_remove, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AreaSupervise areaSupervise = list.get(position);
        viewHolder.tv.setString(areaSupervise.getParentName());
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemDelete(areaSupervise, position);
            }

        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemClick(areaSupervise);
            }
        });

        return convertView;
    }

    protected String getListJson() {
        if (list != null && list.size() > 0) {
            return new Gson().toJson(list);
        }
        return "[]";
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv;
        public DrawHelperLayout drawHelperLayout;
        public TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv = root.findViewById(R.id.tv);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }
}
