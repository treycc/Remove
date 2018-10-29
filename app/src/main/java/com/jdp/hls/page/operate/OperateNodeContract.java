package com.jdp.hls.page.operate;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface OperateNodeContract {
    interface View extends BaseView {
        void onDeleteNodeSuccess(String buildingIds);

        void onSendNodeSuccess(String buildingIds);

        void onReviewNodeSuccess(String buildingIds);

        void onBackNodeSuccess(String buildingIds);

        void onRecoverNodeSuccess(String buildingIds);
    }

    interface Presenter extends BasePresenter<View> {

        void deleteNode(RequestBody requestBody, String buildingIds);

        void sendNode(RequestBody requestBody, String buildingIds);

        void reviewNode(RequestBody requestBody, String buildingIds);

        void backNode(RequestBody requestBody, String buildingIds);

        void recoverNode(RequestBody requestBody, String buildingIds);
    }
}
