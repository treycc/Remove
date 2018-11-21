package com.jdp.hls.page.admin.group.list;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.model.entiy.Group;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Query;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface GroupListContract {
    interface View extends BaseView {
        void onGetGroupListSuccess(List<Group> groupList);
    }

    interface Presenter extends BasePresenter<View> {
        void getGroupList(String projectId);
    }
}
