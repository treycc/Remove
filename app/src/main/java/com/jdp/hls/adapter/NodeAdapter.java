package com.jdp.hls.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.model.entiy.FlowNode;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NodeUtil;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.FixedListView;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 4:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeAdapter extends BaseLvAdapter<FlowNode> {

    private final String buildingId;

    public NodeAdapter(Context context, List<FlowNode> list, String buildingId) {
        super(context, list);
        this.buildingId = buildingId;
    }

    @Override
    public View simpleGetView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = View
                    .inflate(context, R.layout.item_node, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        FlowNode flowNode = list.get(position);
        boolean isTitle = flowNode.isTitle();
        viewHolder.tv_node_des.setText(flowNode.getNodeName());
        if (isTitle) {
            //是标题
            viewHolder.iv_arrow.setBackgroundResource(R.mipmap.ic_arrow_down);
            List<FlowNode> subFlowNodes = flowNode.getSubFlowNodes();
            if (subFlowNodes != null && subFlowNodes.size() > 0) {
                //添加子列表
                viewHolder.lv_node_sub.setAdapter(new SubNodeAdapter(context, subFlowNodes, R.layout.item_node_sub));
                viewHolder.lv_node_sub.setOnItemClickListener((parentSub, view, positionSub, id) -> {
                    FlowNode flowNodeSub = (FlowNode) parentSub.getItemAtPosition(positionSub);
                    if (flowNodeSub.isAvailable()) {
                        NodeUtil.goNodeActivity((Activity)context,flowNodeSub.getId(),buildingId);
                    }

                });
                //控制是否展开
                viewHolder.lv_node_sub.setVisibility(flowNode.isShowSubNode() ? View.VISIBLE : View.GONE);
            }
        } else {
            //不是标题
            viewHolder.iv_arrow.setBackgroundResource(R.mipmap.ic_arrow_right);
            //有无点击权限
            viewHolder.iv_arrow.setVisibility(flowNode.isAvailable() ? View.VISIBLE : View.GONE);
        }
        viewHolder.v_div.setVisibility(position==list.size()-1?View.GONE:View.VISIBLE);

        return convertView;
    }


    public boolean openable(int position) {
        FlowNode flowNode = list.get(position);
        List<FlowNode> subFlowNodes = flowNode.getSubFlowNodes();
        return flowNode.isTitle() && subFlowNodes != null && subFlowNodes.size() > 0.;
    }

    public void setVisibility(int position) {
        FlowNode flowNode = list.get(position);
        flowNode.setShowSubNode(!flowNode.isShowSubNode());
        LogUtil.e(TAG,"是否显示:"+flowNode.isShowSubNode());
        notifyDataSetChanged();
    }


    public class ViewHolder {
        public final View root;
        public TextView tv_node_des;
        public ImageView iv_arrow;
        public FixedListView lv_node_sub;
        public View v_div;

        public ViewHolder(View root) {
            this.root = root;
            tv_node_des = root.findViewById(R.id.tv_node_des);
            iv_arrow = root.findViewById(R.id.iv_arrow);
            lv_node_sub = root.findViewById(R.id.lv_node_sub);
            v_div = root.findViewById(R.id.v_div);
        }
    }
}
