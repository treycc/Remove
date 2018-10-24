package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.FamilyMember;
import com.jdp.hls.model.entiy.OtherArea;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class OtherAreaAdapter extends BaseLvAdapter<OtherArea> {
    private OnDeleteOtherAreaListener onDeleteOtherAreaListener;
    private boolean editable;
    public OtherAreaAdapter(Context context, List<OtherArea> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_otherarea, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_otherArea_area.setString(list.get(position).getArea());
        viewHolder.tv_otherArea_price.setString(list.get(position).getPrice());
        viewHolder.tv_otherArea_name.setString(list.get(position).getName());
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onDeleteOtherAreaListener != null) {
                onDeleteOtherAreaListener.onDeleteOtherArea(String.valueOf(list.get(position).getId()), position);
            }

        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setDragable(editable);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onDeleteOtherAreaListener != null) {
                onDeleteOtherAreaListener.onOtherAreaClick(list.get(position));
            }
        });

        return convertView;
    }

    public void setEditableData(List<OtherArea> familyMemberList, boolean editable) {
        this.editable = editable;
        setData(familyMemberList);
    }

    public void remove(int position) {
        list.remove(position);
        notifyDataSetChanged();
    }

    public void addFirst(OtherArea otherArea) {
        list.add(0, otherArea);
        notifyDataSetChanged();
    }

    public void modify(OtherArea otherArea) {
        for (OtherArea area : list) {
            if (area.getId() == otherArea.getId()) {
                area.setArea(otherArea.getArea());
                area.setName(otherArea.getName());
                area.setPrice(otherArea.getPrice());
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_otherArea_area;
        public StringTextView tv_otherArea_price;
        public StringTextView tv_otherArea_name;
        public DrawHelperLayout drawHelperLayout;
        public TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv_otherArea_area = root.findViewById(R.id.tv_otherArea_area);
            tv_otherArea_price = root.findViewById(R.id.tv_otherArea_price);
            tv_otherArea_name = root.findViewById(R.id.tv_otherArea_name);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }

    public interface OnDeleteOtherAreaListener {
        void onDeleteOtherArea(String id, int position);

        void onOtherAreaClick(OtherArea otherArea);
    }

    public void setOnDeleteOtherAreaListener(OnDeleteOtherAreaListener onDeleteOtherAreaListener) {
        this.onDeleteOtherAreaListener = onDeleteOtherAreaListener;
    }
}
