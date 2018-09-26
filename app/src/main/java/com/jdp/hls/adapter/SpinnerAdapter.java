package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.greendaobean.TDict;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class SpinnerAdapter extends BaseLvAdapter<TDict> {
    public SpinnerAdapter(Context context, List<TDict> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_spiner, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_spiner.setText(list.get(position).getTypeName());

        return convertView;
    }


    public class ViewHolder {
        public final View root;
        public TextView tv_spiner;
        public ImageView iv_spiner_arrow;

        public ViewHolder(View root) {
            this.root = root;
            tv_spiner = root.findViewById(R.id.tv_spiner);
        }
    }
}
