package com.jdp.hls.page.admin.query.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.BusinessQuery;
import com.jdp.hls.model.entiy.TaskInfo;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface QueryListContract {
    interface View extends BaseView {
        void onQueryListSuccess(List<BusinessQuery> businessQueryList);
    }

    interface Presenter extends BasePresenter<View> {
        void getQueryList(String projectId, int buildingType);
    }
}
