package com.jdp.hls.page.supervise.statistics.table;


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
public interface TableListSuperviseContract {
    interface View extends BaseView {
        void onGetTablesSuperviseSuccess(List<Table> tables);
    }

    interface Presenter extends BasePresenter<View> {
        void getTablesSupervise(String projectId, int buildingType);
    }
}
