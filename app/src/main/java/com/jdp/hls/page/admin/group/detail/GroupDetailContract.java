package com.jdp.hls.page.admin.group.detail;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Company;
import com.jdp.hls.model.entiy.Group;
import com.jdp.hls.model.entiy.GroupDetail;

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
public interface GroupDetailContract {
    interface View extends BaseView {
        void onGetGroupDetailSuccess(GroupDetail groupDetail);

        void onSaveGroupInfoSuccess(Group group);
    }

    interface Presenter extends BasePresenter<View> {
        void getGroupDetail(String projectId, int groupId);

        void saveGroupInfo(RequestBody requestBody);
    }
}
