package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Member;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GroupDetailAdapter extends BaseLvAdapter<Member> {

    public GroupDetailAdapter(Context context, List<Member> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_group_detail, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tv_accountTypeName.setText(list.get(position).getAccountTypeName());
        viewHolder.tv_realName.setText(list.get(position).getRealName());
        viewHolder.tv_realName.setHint(list.get(position).getMarkedWords());
        return convertView;
    }

    public void modifyMember(Member member) {
        for (Member item : list) {
            if (item.getCompanyTypeId() == member.getCompanyTypeId()) {
                item.setRealName(member.getRealName());
                item.setEmployeeId(member.getEmployeeId());
            }
        }
        notifyDataSetChanged();
    }


    public class ViewHolder {
        public final View root;
        public StringTextView tv_accountTypeName;
        public StringTextView tv_realName;

        public ViewHolder(View root) {
            this.root = root;
            tv_accountTypeName = root.findViewById(R.id.tv_accountTypeName);
            tv_realName = root.findViewById(R.id.tv_realName);
        }
    }
}
