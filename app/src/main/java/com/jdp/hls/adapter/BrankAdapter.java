package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.BrankInfo;
import com.jdp.hls.model.entiy.OtherArea;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BrankAdapter extends BaseLvAdapter<BrankInfo> {
    public BrankAdapter(Context context, List<BrankInfo> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_brank, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        BrankInfo brankInfo = list.get(position);

        viewHolder.tv_realName.setString(brankInfo.getRealName());
        viewHolder.tv_bankAccount.setString(brankInfo.getBrankAccount());
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemDelete(brankInfo, position);
            }

        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setDragable(editable);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemClick(brankInfo);
            }
        });

        return convertView;
    }

    public void remove(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }




//    public void modify(OtherArea otherArea) {
//        for (OtherArea area : list) {
//            if (area.getId() == otherArea.getId()) {
//                area.setArea(otherArea.getArea());
//                area.setName(otherArea.getName());
//                area.setPrice(otherArea.getPrice());
//            }
//        }
//        notifyDataSetChanged();
//    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_bankAccount;
        public StringTextView tv_realName;
        public DrawHelperLayout drawHelperLayout;
        public TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv_bankAccount = root.findViewById(R.id.tv_bankAccount);
            tv_realName = root.findViewById(R.id.tv_realName);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }
}
