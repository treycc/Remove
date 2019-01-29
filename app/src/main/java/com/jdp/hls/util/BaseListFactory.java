package com.jdp.hls.util;

import com.jdp.hls.view.dialog.BaseListDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2019/1/29 0029 下午 2:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BaseListFactory {

    public static List<BaseListDialog.DisplayItem> getBuildingTypeList() {
        List<BaseListDialog.DisplayItem> reslutList = new ArrayList<>();
        reslutList.add(new BaseListDialog.DisplayItem(0, "个人"));
        reslutList.add(new BaseListDialog.DisplayItem(1, "企业"));
        return reslutList;
    }


    public static List<BaseListDialog.DisplayItem> getCreateTypeList() {
        List<BaseListDialog.DisplayItem> reslutList = new ArrayList<>();
        reslutList.add(new BaseListDialog.DisplayItem(0, "导入"));
        reslutList.add(new BaseListDialog.DisplayItem(1, "新增"));
        return reslutList;
    }
}