package com.jdp.hls.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.ProtocolItem;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProtocolAdapter extends BaseLvAdapter<ProtocolItem> {
    private boolean allowEdit;

    public ProtocolAdapter(Context context, List<ProtocolItem> list, boolean allowEdit) {
        super(context, list);
        this.allowEdit = allowEdit;
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_protocol, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ProtocolItem protocolItem = list.get(position);
        viewHolder.tv_title.setString(protocolItem.getTitle());
        viewHolder.et_value.setEnabled(protocolItem.isAllowEdit() && allowEdit);
        viewHolder.et_value.setTag(protocolItem);
        if (protocolItem.isAllowEdit() && allowEdit) {
            viewHolder.et_value.addTextChangedListener(new SimpleTextWatcher() {
                @Override
                public void afterTextChanged(Editable s) {
                    ProtocolItem protocolItem = (ProtocolItem) viewHolder.et_value.getTag();
                    LogUtil.e(TAG,"protocolItem:"+protocolItem.toString());
                    protocolItem.setValue(s.toString());
                }
            });
        }
        LogUtil.e(TAG, "value:"+protocolItem.getValue() );
        if(!TextUtils.isEmpty(protocolItem.getValue())){
            viewHolder.et_value.setString(protocolItem.getValue());
        }else{
            viewHolder.et_value.setString("");
        }
        return convertView;
    }


    public class ViewHolder {
        public final View root;
        public StringTextView tv_title;
        public EnableEditText et_value;

        public ViewHolder(View root) {
            this.root = root;
            tv_title = root.findViewById(R.id.tv_title);
            et_value = root.findViewById(R.id.et_value);
        }
    }
}
