package com.jdp.hls.page.node.mapping.personal;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodePersonalMapping;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodePersonalMappingContract {
    interface View extends BaseView {
        void onGetPersonalMappingSuccess(NodePersonalMapping nodePersonalMapping);
        void onModifyPersonalMappingSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonalMapping(String houseId);
        void modifyPersonalMapping(RequestBody rosterBody);
    }
}
