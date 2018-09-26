package com.jdp.hls.page.business.basic.company;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.BaiscCompany;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface BaiscCompanyContract {
    interface View extends BaseView {
        void onGetCompanyBasicSuccess(BaiscCompany baiscCompany);
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyBasic(String buildingId);
    }
}
