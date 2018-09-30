package com.jdp.hls.page.node.mapping.company;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodeCompanyMapping;
import com.jdp.hls.model.entiy.NodePersonalMapping;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodeCompanyMappingContract {
    interface View extends BaseView {
        void onGetCompanyMappingSuccess(NodeCompanyMapping nodeCompanyMapping);
        void onModifyCompanyMappingSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyMapping(String houseId);
        void modifyCompanyMapping(RequestBody rosterBody);
    }
}
