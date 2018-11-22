package com.jdp.hls.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.Group;
import com.jdp.hls.view.StringTextView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GroupAdapter extends BaseLvAdapter<Group> {

    public GroupAdapter(Context context, List<Group> list) {
        super(context, list);
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_group, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_groupName.setString(list.get(position).getGroupName());
        viewHolder.tv_operatorName.setString(list.get(position).getOperatorName());
        viewHolder.tv_measurerName.setString(list.get(position).getMeasurerName());
        viewHolder.tv_mapperName.setString(list.get(position).getMapperName());
        viewHolder.tv_mapConfirmerName.setString(list.get(position).getMapConfirmerName());
        viewHolder.tv_identifierName.setString(list.get(position).getIdentifierName());
        viewHolder.tv_assetEvaluatorName.setString(list.get(position).getAssetEvaluatorName());
        viewHolder.tv_evaluatorName.setString(list.get(position).getEvaluatorName());
        viewHolder.tv_checkerName.setString(list.get(position).getCheckerName());
        return convertView;
    }

    public void modifyItem(Group group) {
        for (Group item : list) {
            if (item.getGroupId() == group.getGroupId()) {
                item.setGroupName(group.getGroupName());
                item.setOperatorName(group.getOperatorName());
                item.setMeasurerName(group.getMeasurerName());
                item.setMapperName(group.getMapperName());
                item.setMapConfirmerName(group.getMapConfirmerName());
                item.setIdentifierName(group.getIdentifierName());
                item.setAssetEvaluatorName(group.getAssetEvaluatorName());
                item.setEvaluatorName(group.getEvaluatorName());
                item.setCheckerName(group.getCheckerName());
            }
        }
        notifyDataSetChanged();
    }

    public class ViewHolder {
        public final View root;
        public StringTextView tv_groupName;
        public StringTextView tv_operatorName;
        public StringTextView tv_measurerName;
        public StringTextView tv_mapperName;
        public StringTextView tv_mapConfirmerName;
        public StringTextView tv_identifierName;
        public StringTextView tv_assetEvaluatorName;
        public StringTextView tv_evaluatorName;
        public StringTextView tv_checkerName;

        public ViewHolder(View root) {
            this.root = root;
            tv_groupName = root.findViewById(R.id.tv_groupName);
            tv_operatorName = root.findViewById(R.id.tv_operatorName);
            tv_measurerName = root.findViewById(R.id.tv_measurerName);
            tv_mapperName = root.findViewById(R.id.tv_mapperName);
            tv_mapConfirmerName = root.findViewById(R.id.tv_mapConfirmerName);
            tv_identifierName = root.findViewById(R.id.tv_identifierName);
            tv_assetEvaluatorName = root.findViewById(R.id.tv_assetEvaluatorName);
            tv_evaluatorName = root.findViewById(R.id.tv_evaluatorName);
            tv_checkerName = root.findViewById(R.id.tv_checkerName);
        }
    }
}
