package com.jdp.hls.page.supervise.statistics.total.compensation;

import android.os.Bundle;

import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.base.BaseKvListActivity;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.TitleValue;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

/**
 * Description:TODO
 * Create Time:2019/3/14 0014 下午 1:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CompensationDetailActivity extends BaseKvListActivity<TitleValue> implements CompensationDetailContract
        .View {
    private String title;
    @Inject
    CompensationDetailPresenter compensationDetailPresenter;
    private int itemId;

    @Override
    protected void initVariable(Bundle bundle) {
        title = bundle.getString(Constants.Extra.TITLE);
        itemId = bundle.getInt(Constants.Extra.ID);
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        compensationDetailPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return title;
    }

    @Override
    protected void setKeyValue(StringTextView tvTitle, StringTextView tvValue, TitleValue keyValue) {
        tvTitle.setString(keyValue.getTitle());
        tvValue.setString(keyValue.getValue());
    }

    @Override
    public void initNet() {
        compensationDetailPresenter.getCompensationDetail(itemId);

    }

    @Override
    public void onGetCompensationDetailSuccess(List<TitleValue> titleValueList) {
        keyValueAdapter.setData(titleValueList);

    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
