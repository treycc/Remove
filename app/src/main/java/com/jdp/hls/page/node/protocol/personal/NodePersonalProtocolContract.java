package com.jdp.hls.page.node.protocol.personal;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodePersonalMeasure;
import com.jdp.hls.model.entiy.NodePersonalProtocol;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodePersonalProtocolContract {
    interface View extends BaseView {
        void onGetPersonalProtocolSuccess(NodePersonalProtocol nodePersonalProtocol);
        void onModifyPersonalProtocolSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getPersonalProtocol(String houseId);
        void modifyPersonalProtocol(RequestBody rosterBody);
    }
}
