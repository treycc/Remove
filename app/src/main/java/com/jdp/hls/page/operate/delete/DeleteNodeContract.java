package com.jdp.hls.page.operate.delete;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DeleteNodeContract {
    interface View extends BaseView {
        void onDeleteNodeSuccess();
    }

    interface Presenter extends BasePresenter<View> {

        void deleteNode(RequestBody requestBody);
    }
}
