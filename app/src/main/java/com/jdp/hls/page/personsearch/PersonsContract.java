package com.jdp.hls.page.personsearch;


import com.jdp.hls.base.BasePresenter;
import com.jdp.hls.base.BaseView;
import com.jdp.hls.model.entiy.Person;

import java.util.List;

/**
 * Description：TODO
 * Create Time：2016/10/10 14:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public interface PersonsContract {
    interface View extends BaseView {
        void onGetPersonsSuccess(List<Person> persons);
    }

    interface Presenter extends BasePresenter<View> {
        void getPersons(String projectId);
    }
}
