package com.jdp.hls.service.initialize;

import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.model.entiy.Dict;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface InitializeContract {
    interface View extends BaseView {
        void onGetDictsSuccess(List<Dict> dicts);
    }

    interface Presenter extends BasePresenter<View> {
        void getDict();
    }
}
