package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedAdapter extends BaseLvAdapter<DeedItem> {
    public DeedAdapter(Context context, List<DeedItem> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_deed, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DeedItem deedItem = list.get(position);

        viewHolder.tv_certNum.setString(deedItem.getCertNum());
        viewHolder.tv_address.setString(deedItem.getAddress());
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemDelete(deedItem, position);
            }
        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setDragable(editable);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemClick(deedItem);
            }
        });
        return convertView;
    }

    public void remove(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }
//
    @Override
    public void modifyItem(DeedItem item) {
        for (DeedItem bankInfo : list) {
            if (bankInfo.getId() == item.getId()) {
//                bankInfo.setBankAccountName(item.getBankAccountName());
//                bankInfo.setBankAccount(item.getBankAccount());
//                bankInfo.setBankName(item.getBankName());
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_certNum;
        public StringTextView tv_address;
        public DrawHelperLayout drawHelperLayout;
        public TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv_certNum = root.findViewById(R.id.tv_certNum);
            tv_address = root.findViewById(R.id.tv_address);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }
}
