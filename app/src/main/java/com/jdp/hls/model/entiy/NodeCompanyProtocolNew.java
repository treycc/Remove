package com.jdp.hls.model.entiy;

import java.io.Serializable;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/3/12 0012 下午 3:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyProtocolNew implements Serializable{

    private List<ProtocolItem> Items;

    public List<ProtocolItem> getItems() {
        return Items;
    }

    public void setItems(List<ProtocolItem> items) {
        Items = items;
    }
}
