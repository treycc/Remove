package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.PublicityObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityObjectAdapter extends BaseLvAdapter<PublicityObject> {

    public PublicityObjectAdapter(Context context, List<PublicityObject> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_publicity_object, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.cb_check.setChecked(list.get(position).isChecked());
        viewHolder.cb_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!buttonView.isPressed())
                return;
            list.get(position).setChecked(isChecked);
            notifyDataSetChanged();

        });
        return convertView;
    }

    public void setAllCheckedStatus(boolean allChecked) {
        for (PublicityObject publicityObject : list) {
            publicityObject.setChecked(allChecked);
        }
        notifyDataSetChanged();
    }

    public List<PublicityObject> getSelectedObjects() {
        List<PublicityObject> checkedList = new ArrayList<>();
        for (PublicityObject publicityObject : list) {
            if (publicityObject.isChecked()) {
                checkedList.add(publicityObject);

            }
        }
        System.out.println("checked:"+checkedList.size());
        return checkedList;
    }

    public class ViewHolder {
        public final View root;
        CheckBox cb_check;

        public ViewHolder(View root) {
            this.root = root;
            cb_check = root.findViewById(R.id.cb_check);
        }
    }
}
