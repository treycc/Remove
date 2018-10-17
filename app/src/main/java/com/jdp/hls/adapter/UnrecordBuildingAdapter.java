package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jdp.hls.R;
import com.jdp.hls.model.entiy.UnRecordBuilding;
import com.jdp.hls.util.Base64Util;
import com.jdp.hls.util.StringUtil;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class UnrecordBuildingAdapter extends BaseLvAdapter<UnRecordBuilding> {
    private OnItemOperListener onItemOperListener;
    private List<UnRecordBuilding> deletedUnrecordBuildingList = new ArrayList<>();

    public UnrecordBuildingAdapter(Context context, List<UnRecordBuilding> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_unrecordbuilding, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_unrecord_position.setString(list.get(position).getPosition());
        viewHolder.tv_unrecord_layer.setString(list.get(position).getLayer());
        viewHolder.tv_unrecord_area.setString(list.get(position).getArea());
        viewHolder.tv_unrecord_pic84.setString(list.get(position).getPic84());
        viewHolder.tv_unrecord_pic94.setString(list.get(position).getPic94());
        viewHolder.tv_unrecord_pic2000.setString(list.get(position).getPic2000());
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemDelete(String.valueOf(list.get(position).getId()), position);
            }

        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemClick(list.get(position));
            }
        });
        return convertView;
    }

    public void remove(int position) {
        UnRecordBuilding unRecordBuilding = list.get(position);
        if (unRecordBuilding.getId() != 0) {
            deletedUnrecordBuildingList.add(unRecordBuilding);
        }
        list.remove(position);
        notifyDataSetChanged();
    }

    public String getDeleteIds() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < deletedUnrecordBuildingList.size(); i++) {
            if (i != deletedUnrecordBuildingList.size() - 1) {
                sb.append(String.valueOf(deletedUnrecordBuildingList.get(i).getId()));
                sb.append("#");
            } else {
                sb.append(String.valueOf(deletedUnrecordBuildingList.get(i).getId()));
            }
        }
        return sb.toString();
    }

    public String getEditedBase64() {
        String result = "";
        List<UnRecordBuilding> editedList = new ArrayList<>();
        for (UnRecordBuilding unRecordBuilding : list) {
            if (unRecordBuilding.isEdited()) {
                editedList.add(unRecordBuilding);
            }
        }
        if (editedList.size() > 0) {
            result = new Gson().toJson(editedList);
        }
        return result;
    }

    public void addFirst(UnRecordBuilding unRecordBuilding) {
        list.add(0, unRecordBuilding);
        notifyDataSetChanged();
    }

    public void modify(UnRecordBuilding unRecordBuilding) {
        for (UnRecordBuilding area : list) {
            if (area.getId() == unRecordBuilding.getId()) {
                area.setPosition(unRecordBuilding.getPosition());
                area.setLayer(unRecordBuilding.getLayer());
                area.setArea(unRecordBuilding.getArea());
                area.setPic84(unRecordBuilding.getPic84());
                area.setPic94(unRecordBuilding.getPic94());
                area.setPic2000(unRecordBuilding.getPic2000());
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        StringTextView tv_unrecord_position;
        StringTextView tv_unrecord_layer;
        StringTextView tv_unrecord_area;
        StringTextView tv_unrecord_pic84;
        StringTextView tv_unrecord_pic94;
        StringTextView tv_unrecord_pic2000;
        public DrawHelperLayout drawHelperLayout;
        public TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv_unrecord_position = root.findViewById(R.id.tv_unrecord_position);
            tv_unrecord_layer = root.findViewById(R.id.tv_unrecord_layer);
            tv_unrecord_area = root.findViewById(R.id.tv_unrecord_area);
            tv_unrecord_pic84 = root.findViewById(R.id.tv_unrecord_pic84);
            tv_unrecord_pic94 = root.findViewById(R.id.tv_unrecord_pic94);
            tv_unrecord_pic2000 = root.findViewById(R.id.tv_unrecord_pic2000);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }

    public interface OnItemOperListener {
        void onItemDelete(String id, int position);

        void onItemClick(UnRecordBuilding unRecordBuilding);
    }

    public void setOnItemOperListener(OnItemOperListener onItemOperListener) {
        this.onItemOperListener = onItemOperListener;
    }
}
