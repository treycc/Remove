package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.DecorationItem;
import com.jdp.hls.model.entiy.OtherArea;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.MathUtil;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DecorationAdapter extends BaseLvAdapter<DecorationItem> {
    private OnItemOperListener onItemOperListener;
    private boolean editable;
    public DecorationAdapter(Context context, List<DecorationItem> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_decoration, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        double price = list.get(position).getPrice();
        int quantity = list.get(position).getQuantity();
        viewHolder.tv_innerDecoratiohn_name.setString(list.get(position).getStandardName());
        viewHolder.tv_innerDecoratiohn_price.setString(price);
        viewHolder.tv_innerDecoratiohn_quantity.setString(quantity);
        viewHolder.tv_innerDecoratiohn_totalMoney.setString(MathUtil.mul(price, quantity));
        viewHolder.tv_innerDecoratiohn_remark.setString(list.get(position).getRemark());
        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemDelete(String.valueOf(list.get(position).getId()), position);
            }

        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setDragable(editable);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemClick(list.get(position));
            }
        });
        return convertView;
    }
    public void setEditableData(List<DecorationItem> decorationItemList, boolean editable) {
        this.editable = editable;
        setData(decorationItemList);
    }
    public double getTotalMoney() {
        double totalMoney = 0;
        for (DecorationItem decorationItem : list) {
            totalMoney += MathUtil.mul(decorationItem.getPrice(), decorationItem.getQuantity());
        }
        return totalMoney;
    }

    public void addFirst(DecorationItem decorationItem) {
        list.add(0, decorationItem);
        notifyDataSetChanged();
    }
    public void modify(DecorationItem decorationItem) {
        for (DecorationItem item : list) {
            if (item.getId() == decorationItem.getId()) {
                item.setDPSCId(decorationItem.getDPSCId());
                item.setDPSId(decorationItem.getDPSId());
                item.setGradeName(decorationItem.getGradeName());
                item.setItemType(decorationItem.getItemType());
                item.setItemTypeName(decorationItem.getItemTypeName());
                item.setPrice(decorationItem.getPrice());
                item.setQuantity(decorationItem.getQuantity());
                item.setRemark(decorationItem.getRemark());
                item.setStandardName(decorationItem.getStandardName());
                item.setStandardUnit(decorationItem.getStandardUnit());
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        StringTextView tv_innerDecoratiohn_name;
        StringTextView tv_innerDecoratiohn_price;
        StringTextView tv_innerDecoratiohn_quantity;
        StringTextView tv_innerDecoratiohn_totalMoney;
        StringTextView tv_innerDecoratiohn_remark;
        DrawHelperLayout drawHelperLayout;
        TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv_innerDecoratiohn_name = root.findViewById(R.id.tv_innerDecoratiohn_name);
            tv_innerDecoratiohn_price = root.findViewById(R.id.tv_innerDecoratiohn_price);
            tv_innerDecoratiohn_quantity = root.findViewById(R.id.tv_innerDecoratiohn_quantity);
            tv_innerDecoratiohn_totalMoney = root.findViewById(R.id.tv_innerDecoratiohn_totalMoney);
            tv_innerDecoratiohn_remark = root.findViewById(R.id.tv_innerDecoratiohn_remark);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }

    public interface OnItemOperListener {
        void onItemDelete(String id, int position);

        void onItemClick(DecorationItem decorationItem);
    }

    public void setOnItemOperListener(OnItemOperListener onItemOperListener) {
        this.onItemOperListener = onItemOperListener;
    }
}
