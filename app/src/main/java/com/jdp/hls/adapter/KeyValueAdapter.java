package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.i.IKeyValue;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public abstract class KeyValueAdapter<T> extends BaseLvAdapter<T> {

    public KeyValueAdapter(Context context, List<T> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_kv, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        T keyValue = list.get(position);
        fillKeyValue(viewHolder.tv_title,viewHolder.tv_value,keyValue);
        return convertView;
    }

    protected  abstract void fillKeyValue(StringTextView tvTitle, StringTextView tvValue, T keyValue);


    public class ViewHolder {
        public final View root;
        public StringTextView tv_title;
        public StringTextView tv_value;

        public ViewHolder(View root) {
            this.root = root;
            tv_title = root.findViewById(R.id.tv_title);
            tv_value = root.findViewById(R.id.tv_value);
        }
    }
}
