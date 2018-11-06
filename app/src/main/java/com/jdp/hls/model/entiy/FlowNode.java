package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 6:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FlowNode {
    private int Id;
    private int NodeStatusId;
    private String NodeName;
    private boolean IsAvailable;
    private boolean IsTitle;
    private boolean showSubNode;
    private List<FlowNode> SubFlowNodes;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public boolean isShowSubNode() {
        return showSubNode;
    }

    public void setShowSubNode(boolean showSubNode) {
        this.showSubNode = showSubNode;
    }

    public boolean isAvailable() {
        return IsAvailable;
    }

    public void setAvailable(boolean available) {
        IsAvailable = available;
    }

    public int getNodeStatusId() {
        return NodeStatusId;
    }

    public void setNodeStatusId(int nodeStatusId) {
        this.NodeStatusId = nodeStatusId;
    }

    public String getNodeName() {
        return NodeName;
    }

    public void setNodeName(String nodeName) {
        this.NodeName = nodeName;
    }

    public boolean isTitle() {
        return IsTitle;
    }

    public void setTitle(boolean title) {
        IsTitle = title;
    }

    public List<FlowNode> getSubFlowNodes() {
        return SubFlowNodes;
    }

    public void setSubFlowNodes(List<FlowNode> subFlowNodes) {
        SubFlowNodes = subFlowNodes;
    }
}
