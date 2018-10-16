package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.ReceivePerson;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/1/22 16:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public  class ReceiverSpinnerAdapter extends BaseLvAdapter<ReceivePerson> {
    public ReceiverSpinnerAdapter(Context context, List<ReceivePerson> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_nicespinner, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_selectItem.setText(list.get(position).getStatusDesc());
        return convertView;
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
