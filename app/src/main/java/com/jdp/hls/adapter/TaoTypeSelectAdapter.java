package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaoTypeSelectAdapter extends BaseLvAdapter<TaoType> {
    private boolean editable;

    public TaoTypeSelectAdapter(Context context, List<TaoType> list, boolean editable) {
        super(context, list);
        this.editable = editable;
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_taotype_select, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TaoType taoType = (TaoType) getItem(position);
        viewHolder.tv_des.setText(String.format("%s(%sm²)", taoType.getPatternName(), taoType.getArea()));
        viewHolder.tv_quantity.setString(taoType.getQuantity());
        viewHolder.iv_reduce.setVisibility(editable ? View.VISIBLE : View.GONE);
        viewHolder.iv_add.setVisibility(editable ? View.VISIBLE : View.GONE);
        viewHolder.iv_add.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                int quantity = taoType.getQuantity();
                quantity++;
                taoType.setQuantity(quantity);
                notifyDataSetChanged();
            }
        });
        viewHolder.iv_reduce.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                int quantity = taoType.getQuantity();
                if (quantity > 1) {
                    quantity--;
                    taoType.setQuantity(quantity);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }


    public class ViewHolder {
        public final View root;
        public StringTextView tv_des;
        public StringTextView tv_quantity;
        public ImageView iv_reduce;
        public ImageView iv_add;

        public ViewHolder(View root) {
            this.root = root;
            tv_des = root.findViewById(R.id.tv_des);
            tv_quantity = root.findViewById(R.id.tv_quantity);
            iv_reduce = root.findViewById(R.id.iv_reduce);
            iv_add = root.findViewById(R.id.iv_add);
        }
    }
}
