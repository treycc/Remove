package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.constant.Status;
import com.jdp.hls.model.entiy.PayItem;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayAdapter extends BaseSearchAdapter<PayItem> {

    private boolean editable;
    private int TEMP_TYPE = 0;
    private int OTHER_TYPE = 1;

    public PayAdapter(Context context, List<PayItem> list, boolean editable) {
        super(context, list);
        this.editable = editable;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        PayItem item = (PayItem) getItem(position);
        return item.getUseItemId() == Status.PayTypeItem.TempPlacementFee ? TEMP_TYPE : OTHER_TYPE;
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        TempViewHolder tempViewHolder = null;
        ViewHolder viewHolder = null;
        int payType = getItemViewType(position);
        if (convertView == null) {
            if (payType == TEMP_TYPE) {
                convertView = View.inflate(context, R.layout.item_pay_temp, null);
                tempViewHolder = new TempViewHolder(convertView);
                convertView.setTag(tempViewHolder);
            } else {
                convertView = View.inflate(context, R.layout.item_pay_protocol, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }
        } else {
            if (payType == TEMP_TYPE) {
                tempViewHolder = (TempViewHolder) convertView.getTag();
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
        }
        PayItem payItem = (PayItem) getItem(position);
        if (payType == TEMP_TYPE) {
            tempViewHolder.tv_useItemName.setString(payItem.getUseItemName());
            tempViewHolder.tv_payDate.setString(payItem.getPayDate());
            tempViewHolder.tv_amount.setString(payItem.getAmount());
            tempViewHolder.tv_limitDate.setString(payItem.getLimitStartDate() + "至" + payItem.getLimitEndDate());
            tempViewHolder.tv_isDouble.setString(payItem.isIsDouble() ? "是" : "否");
            tempViewHolder.tv_remark.setString(payItem.getRemark());
            tempViewHolder.tv_receiveAccount.setString(payItem.getBankAccountName()+"  "+payItem.getBankAccount());
            tempViewHolder.tv_delete.setOnClickListener(v -> {
                if (onItemOperListener != null) {
                    onItemOperListener.onItemDelete(payItem, position);
                }

            });
            tempViewHolder.drawHelperLayout.close(false);
            tempViewHolder.drawHelperLayout.setDragable(editable);
            tempViewHolder.drawHelperLayout.setOnRootClickListener(() -> {
                if (onItemOperListener != null) {
                    onItemOperListener.onItemClick(list.get(position));
                }
            });
        } else {
            viewHolder.tv_useItemName.setString(payItem.getUseItemName());
            viewHolder.tv_payDate.setString(payItem.getPayDate());
            viewHolder.tv_amount.setString(payItem.getAmount());
            viewHolder.tv_remark.setString(payItem.getRemark());
            viewHolder.tv_receiveAccount.setString(payItem.getBankAccountName()+"  "+payItem.getBankAccount());
            viewHolder.tv_delete.setOnClickListener(v -> {
                if (onItemOperListener != null) {
                    onItemOperListener.onItemDelete(payItem, position);
                }

            });
            viewHolder.drawHelperLayout.close(false);
            viewHolder.drawHelperLayout.setDragable(editable);
            viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
                if (onItemOperListener != null) {
                    onItemOperListener.onItemClick(list.get(position));
                }
            });
        }
        return convertView;


    }

    @Override
    protected void doSearch(List<PayItem> list, List<PayItem> resultList, String keyword) {
        for (PayItem payItem : list) {
            if (payItem.getUseItemName().contains(keyword)) {
                resultList.add(payItem);
            }
        }
    }

    @Override
    public void modifySearchItem(PayItem payItem) {
        for (PayItem item : resultList) {
            if (item.getId() == payItem.getId()) {
                item.setRecBankAccountId(payItem.getRecBankAccountId());
                item.setBankAccountName(payItem.getBankAccountName());
                item.setBankAccount(payItem.getBankAccount());
                item.setAmount(payItem.getAmount());
                item.setPayDate(payItem.getPayDate());
                item.setUseItemName(payItem.getUseItemName());
                item.setUseItemId(payItem.getUseItemId());
                item.setLimitStartDate(payItem.getLimitStartDate());
                item.setLimitEndDate(payItem.getLimitEndDate());
                item.setRemark(payItem.getRemark());
                item.setIsDouble(payItem.isIsDouble());
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_useItemName;
        public StringTextView tv_payDate;
        public StringTextView tv_amount;
        public StringTextView tv_limitDate;
        public StringTextView tv_isDouble;
        public StringTextView tv_remark;
        public StringTextView tv_receiveAccount;
        public TextView tv_delete;
        public DrawHelperLayout drawHelperLayout;

        public ViewHolder(View root) {
            this.root = root;
            tv_useItemName = root.findViewById(R.id.tv_useItemName);
            tv_payDate = root.findViewById(R.id.tv_payDate);
            tv_amount = root.findViewById(R.id.tv_amount);
            tv_limitDate = root.findViewById(R.id.tv_limitDate);
            tv_isDouble = root.findViewById(R.id.tv_isDouble);
            tv_remark = root.findViewById(R.id.tv_remark);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
            tv_receiveAccount = root.findViewById(R.id.tv_receiveAccount);
        }
    }

    public class TempViewHolder {
        public final View root;
        public StringTextView tv_useItemName;
        public StringTextView tv_payDate;
        public StringTextView tv_amount;
        public StringTextView tv_remark;
        public StringTextView tv_limitDate;
        public StringTextView tv_isDouble;
        public StringTextView tv_receiveAccount;
        public TextView tv_delete;
        public LinearLayout ll_tempPlacementFee;
        public DrawHelperLayout drawHelperLayout;

        public TempViewHolder(View root) {
            this.root = root;
            tv_limitDate = root.findViewById(R.id.tv_limitDate);
            tv_isDouble = root.findViewById(R.id.tv_isDouble);
            tv_useItemName = root.findViewById(R.id.tv_useItemName);
            tv_payDate = root.findViewById(R.id.tv_payDate);
            tv_amount = root.findViewById(R.id.tv_amount);
            tv_remark = root.findViewById(R.id.tv_remark);
            ll_tempPlacementFee = root.findViewById(R.id.ll_tempPlacementFee);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
            tv_receiveAccount = root.findViewById(R.id.tv_receiveAccount);
        }
    }
}
