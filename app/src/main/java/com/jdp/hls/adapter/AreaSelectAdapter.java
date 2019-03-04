package com.jdp.hls.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.constant.Status;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.model.entiy.AreaSelectorItem;
import com.jdp.hls.model.entiy.KeyValue;
import com.jdp.hls.model.entiy.Table;
import com.jdp.hls.model.entiy.TitleItem;
import com.jdp.hls.page.supervise.statistics.total.pay.list.SupervisePayOwnerListActivity;
import com.jdp.hls.page.supervise.statistics.total.taotype.StatisticsTaotypeListActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.StringTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSelectAdapter extends BaseLvAdapter<AreaSelectorItem> {
    public AreaSelectAdapter(Context context, List<AreaSelectorItem> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_area_select, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AreaSelectorItem areaSelectorItem = (AreaSelectorItem) getItem(position);
        viewHolder.tv_areaName.setString(getDefaultTitle(areaSelectorItem));
        viewHolder.tv_areaName.setTextColor(areaSelectorItem.isVisible() ? ContextCompat.getColor(context, R.color
                .c_3) : ContextCompat.getColor(context, R.color.c_9));
        viewHolder.iv_arrow.setVisibility(areaSelectorItem.isVisible() ? View.VISIBLE : View.GONE);
        return convertView;
    }

    public String getDefaultTitle(AreaSelectorItem area) {
        if (!TextUtils.isEmpty(area.getAreaName())) {
            return area.getAreaName();
        }
        String result = "不限";
        switch (area.getAreaLevel()) {
            case Status.AreaLevel.PROVINCE:
                result = "省";
                break;
            case Status.AreaLevel.CITY:
                result = "市";
                break;
            case Status.AreaLevel.AREA:
                result = "区";
                break;
            case Status.AreaLevel.STREET:
                result = "街道";
                break;
        }
        return result;

    }

    public void resetData(Area area) {
        for (int i = 0; i < list.size(); i++) {
            AreaSelectorItem areaSelectorItem = list.get(i);
            if (area.getLevel() == areaSelectorItem.getAreaLevel()) {
                areaSelectorItem.setAreaLevel(area.getLevel());
                areaSelectorItem.setAreaName(area.getRegionName());
                areaSelectorItem.setAreaNumber(area.getRegionIntId());
                areaSelectorItem.setParentId(area.getParentId());
                LogUtil.e(TAG, "设置" + area.getRegionName());
                //TODO 清空剩下的选项
                if (i + 1 <= list.size() - 1) {
                    LogUtil.e(TAG, "不是最后一个");
                    for (int j = i + 1; j < list.size(); j++) {
                        LogUtil.e(TAG, "清空");
                        AreaSelectorItem clearItem = list.get(j);
                        clearItem.setParentId(0);
                        clearItem.setAreaName("");
                        clearItem.setAreaNumber(0);
                    }
                }

            }
        }
        notifyDataSetChanged();

    }

    public int getParentId(int parentId, int position) {
        if (parentId != 0) {
            return parentId;
        }
        AreaSelectorItem preItem = list.get(position - 1);
        return preItem.getAreaNumber();
    }

    public AreaSelectorItem getParent(int position) {
        return list.get(position - 1);
    }


    public class ViewHolder {
        public final View root;
        StringTextView tv_areaName;
        ImageView iv_arrow;

        public ViewHolder(View root) {
            this.root = root;
            tv_areaName = root.findViewById(R.id.tv_areaName);
            iv_arrow = root.findViewById(R.id.iv_arrow);
        }
    }
}
