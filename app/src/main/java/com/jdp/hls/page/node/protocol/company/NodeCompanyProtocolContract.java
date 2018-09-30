package com.jdp.hls.page.node.protocol.company;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodeCompanyProtocol;
import com.jdp.hls.model.entiy.NodePersonalProtocol;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodeCompanyProtocolContract {
    interface View extends BaseView {
        void onGetCompanyProtocolSuccess(NodeCompanyProtocol nodeCompanyProtocol);
        void onModifyCompanyProtocolSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyProtocol(String houseId);
        void modifyCompanyProtocol(RequestBody rosterBody);
    }
}
