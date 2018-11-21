package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.BusinessQuery;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class QueryAdapter extends BaseLvAdapter<BusinessQuery> {

    public QueryAdapter(Context context, List<BusinessQuery> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_query, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_sysCode.setText(list.get(position).getSysCode());
        viewHolder.tv_realName.setText(list.get(position).getRealName());
        viewHolder.tv_mobilePhone.setText(list.get(position).getMobilePhone());
        viewHolder.tv_operatorName.setText(list.get(position).getOperatorName());
        viewHolder.tv_idcard.setText(list.get(position).getIdcard());
        viewHolder.tv_address.setText(list.get(position).getAddress());
        viewHolder.tv_statusDesc.setText(list.get(position).getStatusDesc());
        viewHolder.tv_payTypeName.setText(list.get(position).getPayTypeName());
        return convertView;
    }


    public class ViewHolder {
        public final View root;
        public StringTextView tv_sysCode;
        public StringTextView tv_realName;
        public StringTextView tv_mobilePhone;
        public StringTextView tv_operatorName;
        public StringTextView tv_idcard;
        public StringTextView tv_address;
        public StringTextView tv_statusDesc;
        public StringTextView tv_payTypeName;

        public ViewHolder(View root) {
            this.root = root;
            tv_sysCode = root.findViewById(R.id.tv_sysCode);
            tv_realName = root.findViewById(R.id.tv_realName);
            tv_mobilePhone = root.findViewById(R.id.tv_mobilePhone);
            tv_operatorName = root.findViewById(R.id.tv_operatorName);
            tv_idcard = root.findViewById(R.id.tv_idcard);
            tv_address = root.findViewById(R.id.tv_address);
            tv_statusDesc = root.findViewById(R.id.tv_statusDesc);
            tv_payTypeName = root.findViewById(R.id.tv_payTypeName);
        }
    }
}
