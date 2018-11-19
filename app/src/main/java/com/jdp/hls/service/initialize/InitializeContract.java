package com.jdp.hls.service.initialize;

import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.model.entiy.AreaData;
import com.jdp.hls.model.entiy.AreaResult;
import com.jdp.hls.model.entiy.Dict;

import java.util.List;

import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface InitializeContract {
    interface View extends BaseView {
        void onGetDictsSuccess(List<Dict> dicts);

        void onGetAreaDataSuccess(AreaResult areaResult);
    }

    interface Presenter extends BasePresenter<View> {
        void getDict();

        void getAreaData(String updateTime);
    }
}
