package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.PayItem;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayDetailAdapter extends BaseSearchAdapter<PayItem> {

    public PayDetailAdapter(Context context, List<PayItem> list) {
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
        PayItem payItem = (PayItem) getItem(position);
        viewHolder.tv_realName.setString(payItem.getRealName());
        return convertView;
    }

    @Override
    protected void doSearch(List<PayItem> list, List<PayItem> resultList, String keyword) {
        for (PayItem payItem : list) {
            if (payItem.getBankAccountName().contains(keyword)) {
                resultList.add(payItem);
            }
        }
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_realName;

        public ViewHolder(View root) {
            this.root = root;
            tv_realName = root.findViewById(R.id.tv_realName);
        }
    }
}
