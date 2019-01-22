package com.jdp.hls.adapter;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.util.AppUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.StringTextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSuperviseConfigAdapter extends BaseLvAdapter<AreaSupervise> {
    private List<AreaSupervise> visibleAreaList = new ArrayList<>();

    public AreaSuperviseConfigAdapter(Context context, List<AreaSupervise> list) {
        super(context, list);
        visibleAreaList = this.list;
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_area_config, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        AreaSupervise areaSupervise = (AreaSupervise) getItem(position);
        viewHolder.tv_areaName.setString(areaSupervise.getRegionName());
//        viewHolder.iv_subset.setVisibility(areaSupervise.hasAdded()?View.VISIBLE:View.GONE);
        viewHolder.cb_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!buttonView.isPressed()) {
                return;
            }
            // 1.选中则关闭节点，并取消选中状态
            selectArea(areaSupervise, isChecked);
            refresh();


        });
        viewHolder.cb_check.setChecked(areaSupervise.isSelected());
        convertView.setPadding(areaSupervise.getLevel() * AppUtil.dp2px(12), AppUtil.dp2px(12), AppUtil.dp2px(12),
                AppUtil.dp2px(12));
        return convertView;
    }

    private void selectArea(AreaSupervise areaSupervise, boolean isChecked) {
        for (AreaSupervise supervise : list) {
            if (supervise.getRegionId() == areaSupervise.getRegionId()) {
                supervise.setSelected(isChecked);
                if (isChecked) {
                    supervise.setExpand(false);
                }

            }
        }


    }

    @Override
    public int getCount() {
        return visibleAreaList.size();
    }

    @Override
    public Object getItem(int position) {
        return visibleAreaList.get(position);
    }


    public void refresh() {
        filterVisibleNode();
        notifyDataSetChanged();
    }

    public List<AreaSupervise> filterVisibleNode() {
        visibleAreaList = new ArrayList<>();
        LogUtil.e(TAG, "集合数量过滤前list:" + list.size());
        for (AreaSupervise node : list) {
            // 如果为跟节点，或者上层目录为展开状态
            if (node.isRootNode() || node.isParentExpand()) {
                visibleAreaList.add(node);
            }
        }
        LogUtil.e(TAG, "集合数量过滤后:" + visibleAreaList.size());
        return visibleAreaList;
    }

    public void addChildren(AreaSupervise areaSupervise, List<AreaSupervise> children) {
        for (int i = 0; i < list.size(); i++) {
            if (areaSupervise.getRegionId() == list.get(i).getRegionId()) {
                list.addAll(i + 1, children);
            }
        }
        LogUtil.e(TAG, "集合数量:" + list.size());
    }

    @Override
    public void setData(List<AreaSupervise> list) {
        this.visibleAreaList = list;
        this.list = list;
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_areaName;
        public ImageView iv_subset;
        public AppCompatCheckBox cb_check;

        public ViewHolder(View root) {
            this.root = root;
            tv_areaName = root.findViewById(R.id.tv_areaName);
            iv_subset = root.findViewById(R.id.iv_subset);
            cb_check = root.findViewById(R.id.cb_check);
        }
    }
}
