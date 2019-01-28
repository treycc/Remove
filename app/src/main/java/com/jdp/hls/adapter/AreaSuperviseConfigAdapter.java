package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.util.AppUtil;
import com.jdp.hls.view.StringTextView;
import com.kingja.supershapeview.view.SuperShapeTextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AreaSuperviseConfigAdapter extends BaseLvAdapter<AreaSupervise> {
    private List<AreaSupervise> selectedAreaList;
    private List<AreaSupervise> visibleAreaList;
    private Map<Integer, List<Integer>> sourceMap = new HashMap<>();
    private List<Area> areasList;

    public AreaSuperviseConfigAdapter(Context context, List<AreaSupervise> list, List<AreaSupervise> selectedAreaList) {
        super(context, list);
        initData();
        this.selectedAreaList = (selectedAreaList==null?new ArrayList<>():selectedAreaList);
        visibleAreaList = this.list;
    }

    private void initData() {
        areasList = DBManager.getInstance().getAreas();
        for (int i = 0; i < areasList.size(); i++) {
            Area area = areasList.get(i);
            if (sourceMap.get(area.getParentId()) == null) {
                List<Integer> indexList = new ArrayList<>();
                indexList.add(i);
                sourceMap.put(area.getParentId(), indexList);
            } else {
                List<Integer> indexList = sourceMap.get(area.getParentId());
                indexList.add(i);
                sourceMap.put(area.getParentId(), indexList);
            }
        }
    }

    public boolean hasSubNods(int regionId) {
        return sourceMap.get(regionId) != null;

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
        viewHolder.set_expand.setText((areaSupervise.expandable() && hasSubNods(areaSupervise.getRegionId())) ? "+" :
                "-");
        viewHolder.tv_areaName.setString(areaSupervise.getRegionName());
        viewHolder.iv_subset.setVisibility((areaSupervise.hasAdded() || isSelectedStatus(areaSupervise)) ? View
                .VISIBLE : View.GONE);
        viewHolder.cb_check.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (!buttonView.isPressed()) {
                return;
            }
            // 1.选中则关闭节点，并取消选中状态

            setSelectStatus(areaSupervise, isChecked);

            selectArea(areaSupervise, isChecked);
            refresh();
        });
        viewHolder.cb_check.setChecked(areaSupervise.isSelected());
        convertView.setPadding(areaSupervise.getLevel() * AppUtil.dp2px(16), AppUtil.dp2px(12), AppUtil.dp2px(12),
                AppUtil.dp2px(12));
        return convertView;
    }

    private boolean isSelectedStatus(AreaSupervise currentItem) {
        if (selectedAreaList != null && selectedAreaList.size() > 0) {
            for (AreaSupervise selectArea : selectedAreaList) {
                String regionIdStr = String.valueOf(currentItem.getRegionId()).replaceAll("0+$", "");
                if (String.valueOf(selectArea.getRegionId()).startsWith(regionIdStr)) {
                    return true;
                }
            }
        }
        return false;
    }
    private void setSelectStatus(AreaSupervise areaSupervise, boolean isChecked) {
        if (isChecked) {
            String regionIdStr = String.valueOf(areaSupervise.getRegionId()).replaceAll("0+$", "");
            Iterator<AreaSupervise> it = selectedAreaList.iterator();
            while (it.hasNext()) {
                AreaSupervise item = it.next();
                String itemregionIdStr = String.valueOf(item.getRegionId()).replaceAll("0+$", "");
                if (areaSupervise.getRegionId() == item.getParentId() ||String.valueOf(item.getRegionId())
                        .startsWith(regionIdStr)||String.valueOf(areaSupervise.getRegionId())
                        .startsWith(itemregionIdStr)) {
                    it.remove();
                }
            }
            selectedAreaList.add(areaSupervise);
        } else {
            if (selectedAreaList.contains(areaSupervise)) {
                selectedAreaList.remove(areaSupervise);
            }
        }
    }

    private void selectArea(AreaSupervise areaSupervise, boolean isChecked) {
        for (AreaSupervise supervise : list) {
            if (supervise.getRegionId() == areaSupervise.getRegionId()) {
                supervise.setSelected(isChecked);
                if (isChecked) {
                    supervise.setExpand(false, true);
                }
            }
        }
    }

    public List<AreaSupervise> getSelectedArea() {
        return selectedAreaList;
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
        for (AreaSupervise node : list) {
            // 如果为跟节点，或者上层目录为展开状态
            if (node.isRootNode() || node.isParentExpand()) {
                visibleAreaList.add(node);
            }
        }
        return visibleAreaList;
    }

    public void addChildren(AreaSupervise areaSupervise, List<AreaSupervise> children) {
        for (AreaSupervise child : children) {
            child.setParent(areaSupervise);
        }
        areaSupervise.setChildren(children);
        for (int i = 0; i < list.size(); i++) {
            if (areaSupervise.getRegionId() == list.get(i).getRegionId()) {
                list.addAll(i + 1, children);
            }
        }
    }

    @Override
    public void setData(List<AreaSupervise> list) {
        this.visibleAreaList = list;
        this.list = list;
        notifyDataSetChanged();
    }

    public List<Integer> getChildrenIndexs(int regionId) {
        return sourceMap.get(regionId);
    }

    public List<AreaSupervise> getChildrenList(List<Integer> areaIndexs) {
        List<AreaSupervise> areaList = new ArrayList<>();
        for (Integer index : areaIndexs) {
            Area area = areasList.get(index);
            areaList.add(new AreaSupervise(area.getLevel(), area.getRegionIntId(), area.getRegionName(), area
                    .getParentId(), isSelected(area.getRegionIntId())));
        }
        return areaList;
    }

    private boolean isSelected(int regionIntId) {
        if (selectedAreaList != null && selectedAreaList.size() > 0) {
            for (AreaSupervise areaSupervise : selectedAreaList) {
                if (areaSupervise.getRegionId() == regionIntId) {
                    return true;
                }
            }
        }
        return false;
    }

    public class ViewHolder {
        public final View root;
        public SuperShapeTextView set_expand;
        public StringTextView tv_areaName;
        public ImageView iv_subset;
        public CheckBox cb_check;

        public ViewHolder(View root) {
            this.root = root;
            set_expand = root.findViewById(R.id.set_expand);
            tv_areaName = root.findViewById(R.id.tv_areaName);
            iv_subset = root.findViewById(R.id.iv_subset);
            cb_check = root.findViewById(R.id.cb_check);
        }
    }
}
