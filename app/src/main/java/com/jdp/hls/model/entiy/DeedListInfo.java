package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/3/8 0008 上午 10:09
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedListInfo {


    /**
     * IsAllowEdit : true
     * Items : [{"id":1,"CertNum":"string","Address":"integer","Remark":"string","CertType":1}]
     */

    private boolean IsAllowEdit;
    private List<DeedItem> Items;

    public boolean isAllowEdit() {
        return IsAllowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        IsAllowEdit = allowEdit;
    }

    public List<DeedItem> getItems() {
        return Items;
    }

    public void setItems(List<DeedItem> Items) {
        this.Items = Items;
    }

}
