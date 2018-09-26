package com.jdp.hls.page.table.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Table;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface TableListContract {
    interface View extends BaseView {
        void onGetTablesSuccess(List<Table> tables);
    }

    interface Presenter extends BasePresenter<View> {
        void getTables(String projectId, int buildingType);
    }
}
