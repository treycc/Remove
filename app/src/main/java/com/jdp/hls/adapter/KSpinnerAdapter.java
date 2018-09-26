package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.greendaobean.TDict;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 下午 1:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class KSpinnerAdapter extends BaseLvAdapter<TDict> {
    public KSpinnerAdapter(Context context, List<TDict> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_nicespinner, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_selectItem.setText(list.get(position).getConfigTypeDesc());
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position).getTypeName();
    }

    public class ViewHolder {
        public final View root;
        TextView tv_selectItem;

        public ViewHolder(View root) {
            this.root = root;
            tv_selectItem = root.findViewById(R.id.tv_selectItem);
        }
    }
}
