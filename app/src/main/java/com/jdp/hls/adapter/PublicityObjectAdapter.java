package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
        viewHolder.tv_publicityNumber.setText(list.get(position).getSysCode());
        viewHolder.tv_publicity_realName.setText(list.get(position).getRealName());
        viewHolder.tv_publicity_mobile.setText(list.get(position).getMobilePhone());
        viewHolder.tv_publicity_idcard.setText(list.get(position).getIdcard());
        viewHolder.tv_publicity_address.setText(list.get(position).getAddress());
        return convertView;
    }

    public void setAllCheckedStatus(boolean allChecked) {
        for (PublicityObject publicityObject : list) {
            publicityObject.setChecked(allChecked);
        }
        notifyDataSetChanged();
    }

    public String getSelectedBuildingIds() {
        StringBuffer sb = new StringBuffer();
        List<PublicityObject> publicityObjectList=new ArrayList<>();
        for (PublicityObject publicityObject : list) {
            if (publicityObject.isChecked()) {
                publicityObjectList.add(publicityObject);
            }
        }
        for (int i = 0; i < publicityObjectList.size(); i++) {
            if (i != publicityObjectList.size() - 1) {
                sb.append(publicityObjectList.get(i).getBuildingId());
                sb.append("#");
            } else {
                sb.append(publicityObjectList.get(i).getBuildingId());
            }
        }
        return sb.toString();
    }

    public class ViewHolder {
        public final View root;
        CheckBox cb_check;
        TextView tv_publicityNumber;
        TextView tv_publicity_realName;
        TextView tv_publicity_mobile;
        TextView tv_publicity_idcard;
        TextView tv_publicity_address;

        public ViewHolder(View root) {
            this.root = root;
            cb_check = root.findViewById(R.id.cb_check);
            tv_publicityNumber = root.findViewById(R.id.tv_publicityNumber);
            tv_publicity_realName = root.findViewById(R.id.tv_publicity_realName);
            tv_publicity_mobile = root.findViewById(R.id.tv_publicity_mobile);
            tv_publicity_idcard = root.findViewById(R.id.tv_publicity_idcard);
            tv_publicity_address = root.findViewById(R.id.tv_publicity_address);
        }
    }
}
