package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.BankInfo;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.util.LatLngUtil;
import com.jdp.hls.view.DrawHelperLayout;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterListAdapter extends BaseLvAdapter<Roster> {
    public RosterListAdapter(Context context, List<Roster> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_roster, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Roster roster = list.get(position);

        viewHolder.tv_roster_address.setString(roster.getHouseAddress());
        viewHolder.tv_roster_name.setString(roster.getRealName());
        viewHolder.tv_roster_phone.setString(roster.getMobilePhone());

        viewHolder.iv_roster_isAssetEvaluated.setVisibility(roster.isEnterprise() ? View.VISIBLE : View.GONE);

        viewHolder.iv_roster_hasLocation.setBackgroundResource(LatLngUtil.isChinaLatLng(roster.getLatitude(), roster
                .getLongitude()) ? R.mipmap.ic_has_location_sel : R.mipmap.ic_has_location_nor);
        viewHolder.iv_roster_isMeasure.setBackgroundResource(roster.isMeasured() ? R.mipmap
                .ic_measure_action : R.mipmap.ic_measure_nor);
        viewHolder.iv_roster_isEvaluated.setBackgroundResource(roster.isEvaluated() ? R.mipmap
                .ic_evaluate_action : R.mipmap.ic_evaluate_nor);
        viewHolder.iv_roster_isAssetEvaluated.setBackgroundResource(roster.isAssetEvaluator() ? R.mipmap
                .ic_assetevaluate_action : R.mipmap.ic_assetevaluate_nor);


        viewHolder.tv_delete.setOnClickListener(v -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemDelete(roster, position);
            }
        });
        viewHolder.drawHelperLayout.close(false);
        viewHolder.drawHelperLayout.setOnRootClickListener(() -> {
            if (onItemOperListener != null) {
                onItemOperListener.onItemClick(roster);
            }
        });
        return convertView;
    }

    @Override
    public void modifyItem(Roster item) {
        for (Roster roster : list) {
            if (roster.getHouseId().equals(item.getHouseId())) {
                roster.setLongitude(item.getLongitude());
                roster.setLatitude(item.getLatitude());
                roster.setEnterprise(item.isEnterprise());
                roster.setEvaluated(item.isEvaluated());
                roster.setMeasured(item.isMeasured());
                roster.setAssetEvaluator(item.isAssetEvaluator());
                roster.setRealName(item.getRealName());
                roster.setMobilePhone(item.getMobilePhone());
                roster.setHouseAddress(item.getHouseAddress());
                break;
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_roster_address;
        public StringTextView tv_roster_name;
        public StringTextView tv_roster_phone;
        public ImageView iv_roster_isAssetEvaluated;
        public ImageView iv_roster_hasLocation;
        public ImageView iv_roster_isMeasure;
        public ImageView iv_roster_isEvaluated;
        public DrawHelperLayout drawHelperLayout;
        public TextView tv_delete;

        public ViewHolder(View root) {
            this.root = root;
            tv_roster_address = root.findViewById(R.id.tv_roster_address);
            tv_roster_name = root.findViewById(R.id.tv_roster_name);
            tv_roster_phone = root.findViewById(R.id.tv_roster_phone);

            iv_roster_isAssetEvaluated = root.findViewById(R.id.iv_roster_isAssetEvaluated);
            iv_roster_hasLocation = root.findViewById(R.id.iv_roster_hasLocation);
            iv_roster_isMeasure = root.findViewById(R.id.iv_roster_isMeasure);
            iv_roster_isEvaluated = root.findViewById(R.id.iv_roster_isEvaluated);
            drawHelperLayout = root.findViewById(R.id.drawHelperLayout);
            tv_delete = root.findViewById(R.id.tv_delete);
        }
    }
}
