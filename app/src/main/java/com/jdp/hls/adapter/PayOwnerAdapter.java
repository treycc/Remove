package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.PayItem;
import com.jdp.hls.model.entiy.PayOwner;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayOwnerAdapter extends BaseSearchAdapter<PayOwner> {

    public PayOwnerAdapter(Context context, List<PayOwner> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_pay, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PayOwner payItem = (PayOwner) getItem(position);
        viewHolder.tv_realName.setString(payItem.getRealName());
        viewHolder.tv_cusCode.setString(payItem.getCusCode());
        viewHolder.tv_address.setString(payItem.getAddress());
        viewHolder.tv_mobilePhone.setString(payItem.getMobilePhone());
        viewHolder.tv_payableAmount.setString(String.format("%.2f元",payItem.getPayableAmount()));
        viewHolder.tv_paidAmount.setString(String.format("%.2f元(%d笔)",payItem.getPaidAmount(),payItem.getQuantity()));
        return convertView;
    }

    @Override
    protected void doSearch(List<PayOwner> list, List<PayOwner> resultList, String keyword) {
        for (PayOwner payItem : list) {
            if (payItem.getRealName().contains(keyword)||payItem.getCusCode().contains(keyword)) {
                resultList.add(payItem);
            }
        }
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_realName;
        public StringTextView tv_paidAmount;
        public StringTextView tv_payableAmount;
        public StringTextView tv_address;
        public StringTextView tv_mobilePhone;
        public StringTextView tv_cusCode;

        public ViewHolder(View root) {
            this.root = root;
            tv_realName = root.findViewById(R.id.tv_realName);
            tv_paidAmount = root.findViewById(R.id.tv_paidAmount);
            tv_payableAmount = root.findViewById(R.id.tv_payableAmount);
            tv_address = root.findViewById(R.id.tv_address);
            tv_mobilePhone = root.findViewById(R.id.tv_mobilePhone);
            tv_cusCode = root.findViewById(R.id.tv_cusCode);
        }
    }
}
