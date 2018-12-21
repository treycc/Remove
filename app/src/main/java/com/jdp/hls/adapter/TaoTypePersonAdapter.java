package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.TaoTypePerson;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/20 0020 下午 1:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TaoTypePersonAdapter extends BaseSearchAdapter<TaoTypePerson> {


    public TaoTypePersonAdapter(Context context, List<TaoTypePerson> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_taotype_person, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        TaoTypePerson taoTypePerson = list.get(position);
        viewHolder.tv_cusCode.setString(taoTypePerson.getCusCode());
        viewHolder.tv_realName.setString(taoTypePerson.getRealName());
        viewHolder.tv_patternQuantity.setString(taoTypePerson.getPatternQuantity());
        viewHolder.tv_address.setString(taoTypePerson.getAddress());
        return convertView;
    }

    @Override
    protected void doSearch(List<TaoTypePerson> list, List<TaoTypePerson> resultList, String keyword) {
        for (TaoTypePerson taoTypePerson : list) {
            if (taoTypePerson.getRealName().contains(keyword)) {
                resultList.add(taoTypePerson);
            }
        }
    }


    public class ViewHolder {
        public final View root;
        StringTextView tv_cusCode;
        StringTextView tv_realName;
        StringTextView tv_patternQuantity;
        StringTextView tv_address;

        public ViewHolder(View root) {
            this.root = root;
            tv_cusCode = root.findViewById(R.id.tv_cusCode);
            tv_realName = root.findViewById(R.id.tv_realName);
            tv_patternQuantity = root.findViewById(R.id.tv_patternQuantity);
            tv_address = root.findViewById(R.id.tv_address);
        }
    }
}
