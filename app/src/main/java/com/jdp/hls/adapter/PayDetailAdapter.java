package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.constant.Status;
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
            convertView = View.inflate(context, R.layout.item_pay_detail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        PayItem payItem = (PayItem) getItem(position);
        viewHolder.tv_amount.setString(payItem.getAmount());
        viewHolder.tv_idcard.setString(payItem.getIdcard());
        viewHolder.tv_realName.setString(payItem.getRealName());
        viewHolder.tv_bankAccount.setString(payItem.getBankAccount());
        viewHolder.tv_limitDate.setString(payItem.getLimitStartDate() + "至" + payItem.getLimitEndDate());
        viewHolder.tv_isDouble.setString(payItem.isIsDouble() ? "是" : "否");
        viewHolder.tv_remark.setString(payItem.getRemark());
        viewHolder.ll_tempPlacementFee.setVisibility(payItem.getUseItemId() == Status.PayTypeItem.TempPlacementFee ?
                View.VISIBLE : View.GONE);
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
        public StringTextView tv_amount;
        public StringTextView tv_remark;
        public StringTextView tv_limitDate;
        public StringTextView tv_isDouble;
        public StringTextView tv_realName;
        public StringTextView tv_bankAccount;
        public StringTextView tv_idcard;
        public LinearLayout ll_tempPlacementFee;

        public ViewHolder(View root) {
            this.root = root;
            tv_limitDate = root.findViewById(R.id.tv_limitDate);
            tv_isDouble = root.findViewById(R.id.tv_isDouble);
            tv_amount = root.findViewById(R.id.tv_amount);
            tv_remark = root.findViewById(R.id.tv_remark);
            tv_realName = root.findViewById(R.id.tv_realName);
            tv_bankAccount = root.findViewById(R.id.tv_bankAccount);
            tv_idcard = root.findViewById(R.id.tv_idcard);
            ll_tempPlacementFee = root.findViewById(R.id.ll_tempPlacementFee);
        }
    }
}
