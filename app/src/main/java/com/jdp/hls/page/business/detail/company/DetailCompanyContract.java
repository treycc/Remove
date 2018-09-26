package com.jdp.hls.page.business.detail.company;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DetailCompany;
import com.jdp.hls.model.entiy.DetailPersonal;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DetailCompanyContract {
    interface View extends BaseView {
        void onGetCompanyDetailSuccess(DetailCompany detailCompany);
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyDetail(String buildingId);
    }
}
