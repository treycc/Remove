package com.jdp.hls.page.suggest;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Roster;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Field;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface SuggestContract {
    interface View extends BaseView {
        void onSuggestSuccess();
    }

    interface Presenter extends BasePresenter<View> {
        void suggest(RequestBody rosterBody);
    }
}
