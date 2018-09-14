package com.jdp.hls.page.publicity.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.PublicityItem;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PublicityListContract {
    interface View extends BaseView {
        void onGetPublicityListSuccess(List<PublicityItem> publicityItems);
    }

    interface Presenter extends BasePresenter<View> {
        void  getPublicityList(String projectId, int type);
    }
}
