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
        void onDeleteNodeSuccess();
        void onSendNodeSuccess();
        void onReviewNodeSuccess();
        void onBackNodeSuccess();
    }

    interface Presenter extends BasePresenter<View> {

        void deleteNode(RequestBody requestBody);
        void sendNode(RequestBody requestBody);
        void reviewNode(RequestBody requestBody);
        void backNode(RequestBody requestBody);
    }
}
