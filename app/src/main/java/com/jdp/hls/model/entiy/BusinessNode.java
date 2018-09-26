package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/9/17 0017 下午 6:07
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessNode {
    private int nodeCode;
    private String nodeDes;

    public int getNodeCode() {
        return nodeCode;
    }

    public void setNodeCode(int nodeCode) {
        this.nodeCode = nodeCode;
    }

    public String getNodeDes() {
        return nodeDes;
    }

    public void setNodeDes(String nodeDes) {
        this.nodeDes = nodeDes;
    }

    public BusinessNode(int nodeCode, String nodeDes) {
        this.nodeCode = nodeCode;
        this.nodeDes = nodeDes;
    }
}
