package com.jdp.hls.page.innerdecoration.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.DecorationItem;
import com.jdp.hls.model.entiy.Task;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface DecorationListContract {
    interface View extends BaseView {
        void onGetDecorationListSuccess(List<DecorationItem> decorationItemList);

        void onDeleteDecorationSuccess(int position);
    }

    interface Presenter extends BasePresenter<View> {
        void getDecorationList(String evalId, String buildingType,String itemType);

        void deleteDecoration(RequestBody requestBody,int position);
    }
}
