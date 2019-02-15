package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.ModifyBusinessEvent;
import com.jdp.hls.i.OnBusinessItemSelectedListener;
import com.jdp.hls.model.entiy.Business;
import com.jdp.hls.model.entiy.Roster;
import com.kingja.supershapeview.view.SuperShapeTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessAdapter extends BaseLvAdapter<Business> {
    private boolean checkable;
    private OnBusinessItemSelectedListener onBusinessSelectedListener;

    public BusinessAdapter(Context context, List<Business> list, boolean checkable) {
        super(context, list);
        this.checkable = checkable;
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_business, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.cb_business.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!buttonView.isPressed()) {
                return;
            }
            list.get(position).setSelected(isChecked);
            if (isChecked) {
                onBusinessSelectedListener.onBusinessAdd(list.get(position));
            } else {
                onBusinessSelectedListener.onBusinessRemove(list.get(position));
            }
        });
        viewHolder.cb_business.setVisibility(checkable ? View.VISIBLE : View.GONE);
        viewHolder.cb_business.setChecked(list.get(position).isSelected());
        viewHolder.tv_business_address.setText(list.get(position).getAddress());
        viewHolder.tv_business_number.setText(list.get(position).getCusCode());
        viewHolder.tv_business_name.setText(list.get(position).getRealName());
        viewHolder.tv_business_mobile.setText(list.get(position).getMobilePhone());
        viewHolder.tv_business_node.setText(list.get(position).getStatusDesc());
        viewHolder.iv_business_hasLocation.setBackgroundResource(list.get(position).isHasLongitudeAndLatitude() ? R
                .mipmap.ic_location_sel : R.mipmap.ic_location_nor);
        viewHolder.set_business_isBack.setVisibility(list.get(position).isFlowBack() ? View.VISIBLE : View.GONE);
        viewHolder.set_business_isUrgent.setVisibility(list.get(position).isUrgent() ? View.VISIBLE : View.GONE);
        viewHolder.set_business_isReminder.setVisibility(list.get(position).isReminder() ? View.VISIBLE : View.GONE);
        return convertView;
    }

    public void checkAll(boolean checked) {
        for (Business business : list) {
            business.setSelected(checked);
            if (checked) {
                onBusinessSelectedListener.onBusinessAdd(business);
            } else {
                onBusinessSelectedListener.onBusinessRemove(business);
            }
        }
        notifyDataSetChanged();
    }

    public void modify(ModifyBusinessEvent event) {
        for (Business business : list) {
            if (business.getBuildingId().equals(event.getBuildingId())) {
                business.setAddress(event.getAddress());
                business.setCusCode(event.getCusCode());
                business.setReminder(event.isReminder());
                business.setUrgent(event.isUrgent());
            }
        }
        notifyDataSetChanged();
    }

    public void modifyMainContacts(Roster item) {
        for (Business business : list) {
            if (business.getBuildingId().equals(item.getHouseId())) {
                business.setRealName(item.getRealName());
                business.setMobilePhone(item.getMobilePhone());
                break;
            }
        }
        notifyDataSetChanged();
    }

    public void refreshReminder(List<String> buildingIdList) {
        for (Business business : list) {
            if (buildingIdList.contains(business.getBuildingId())) {
                business.setReminder(true);
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        public TextView tv_business_nameTip;
        public TextView tv_business_address;
        public TextView tv_business_number;
        public TextView tv_business_name;
        public TextView tv_business_mobile;
        public TextView tv_business_node;
        public ImageView iv_business_hasLocation;
        public SuperShapeTextView set_business_isBack;
        public SuperShapeTextView set_business_isUrgent;
        public SuperShapeTextView set_business_isReminder;
        public CheckBox cb_business;

        public ViewHolder(View root) {
            this.root = root;
            tv_business_nameTip = root.findViewById(R.id.tv_business_nameTip);
            tv_business_address = root.findViewById(R.id.tv_business_address);
            tv_business_number = root.findViewById(R.id.tv_business_number);
            tv_business_name = root.findViewById(R.id.tv_business_name);
            tv_business_mobile = root.findViewById(R.id.tv_business_mobile);
            tv_business_node = root.findViewById(R.id.tv_business_node);
            iv_business_hasLocation = root.findViewById(R.id.iv_business_hasLocation);
            set_business_isBack = root.findViewById(R.id.set_business_isBack);
            set_business_isUrgent = root.findViewById(R.id.set_business_isUrgent);
            set_business_isReminder = root.findViewById(R.id.set_business_isReminder);
            cb_business = root.findViewById(R.id.cb_business);
        }
    }

    public void setOnBusinessSelectedListener(OnBusinessItemSelectedListener onBusinessSelectedListener) {
        this.onBusinessSelectedListener = onBusinessSelectedListener;
    }
}
