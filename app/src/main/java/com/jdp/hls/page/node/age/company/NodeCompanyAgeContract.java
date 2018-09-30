package com.jdp.hls.page.node.age.company;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.NodeCompanyAge;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface NodeCompanyAgeContract {
    interface View extends BaseView {
        void onGetCompanyAgeSuccess(NodeCompanyAge nodeCompanyAge);
        void onModifyCompanyAgeSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyAge(String houseId);
        void modifyCompanyAge(RequestBody rosterBody);
    }
}
