package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 6:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FlowNode {
    private int NodeStatusId;
    private String NodeName;
    private boolean IsAvailable;

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

    public FlowNode(int NodeStatusId, String NodeName) {
        this.NodeStatusId = NodeStatusId;
        this.NodeName = NodeName;
    }
}
