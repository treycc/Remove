package com.jdp.hls.page.familyrelation.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.FamilyRelation;

import okhttp3.RequestBody;
import retrofit2.http.Body;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface FamilyRelationContract {
    interface View extends BaseView {
        void onGetFamilyRelationSuccess(FamilyRelation familyRelation);

        void onDeleteFamilyRememberSuccess(int position);
    }

    interface Presenter extends BasePresenter<View> {
        void getFamilyRelation(String houseId);

        void deleteFamilyRemember(@Body RequestBody requestBody, int position);
    }
}
