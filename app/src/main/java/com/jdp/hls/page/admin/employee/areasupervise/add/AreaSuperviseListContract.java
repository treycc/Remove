package com.jdp.hls.page.admin.employee.areasupervise.add;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.model.entiy.AreaSupervise;
import com.jdp.hls.model.entiy.Company;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface AreaSuperviseListContract {
    interface View extends BaseView {
        void onGetAreaSuperviseList(List<AreaSupervise> areaSuperviseList);
    }

    interface Presenter extends BasePresenter<View> {
        void getAreaSuperviseList();
    }
}
