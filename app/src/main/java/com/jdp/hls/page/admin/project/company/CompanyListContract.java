package com.jdp.hls.page.admin.project.company;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Company;
import com.jdp.hls.model.entiy.ProjectListInfo;

import java.util.List;

import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface CompanyListContract {
    interface View extends BaseView {
        void onGetCompanyListSuccess(List<Company> companyList);
    }

    interface Presenter extends BasePresenter<View> {
        void getCompanyList(int companyTypeId);
    }
}
