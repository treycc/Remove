package com.jdp.hls.page.innerdecoration.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DecorationItem;

import okhttp3.RequestBody;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DecorationDetailContract {
    interface View extends BaseView {
        void onModifyDecorationSuccess(DecorationItem decorationItem);

        void onAddDecorationSuccess(DecorationItem decorationItem);
    }

    interface Presenter extends BasePresenter<View> {
        void modifyDecoration(RequestBody requestBody);

        void addDecoration(RequestBody requestBody);
    }
}
