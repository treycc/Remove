package com.jdp.hls.page.publicity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.PublicityListAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.event.AddPublicityEvent;
import com.jdp.hls.event.ModifyPublicityEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.PublicityItem;
import com.jdp.hls.page.publicity.apply.PublicityApplyActivity;
import com.jdp.hls.page.publicity.detail.PublicityDetailActivity;
import com.jdp.hls.page.publicity.list.PublicityListContract;
import com.jdp.hls.page.publicity.list.PublicityListPresenter;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshableSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/8/17 0017 下午 4:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityActivity extends BaseTitleActivity implements SwipeRefreshLayout
        .OnRefreshListener, PublicityListContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshableSwipeRefreshLayout srl;
    @Inject
    PublicityListPresenter publicityListPresenter;
    List<PublicityItem> publicityItemList = new ArrayList<>();

    private PublicityListAdapter adapter;

    @OnClick({R.id.iv_search})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                adapter.searchPerson(etKeyword.getText().toString().trim());
                break;
        }
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        PublicityItem publicityItem = (PublicityItem) adapterView.getItemAtPosition(position);
        PublicityDetailActivity.goActivity(this, publicityItem.getPubId(), publicityItem.getPubStatus(), position);
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_publicity_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return "公示管理";
    }

    @Override
    protected void initView() {
        publicityListPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        plv.setAdapter(adapter = new PublicityListAdapter(this, publicityItemList, R.layout.item_publicity));
        srl.setOnRefreshListener(this);
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                adapter.searchPerson(s.toString());
            }
        });
        setRightClick("申请", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(PublicityActivity.this, PublicityApplyActivity.class);
            }
        });
    }

    @Override
    public void initNet() {
        srl.setRefreshing(false);
        publicityListPresenter.getPublicityList(SpSir.getInstance().getProjectId(), -1);
    }

    @Override
    public void onRefresh() {
        initNet();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onGetPublicityListSuccess(List<PublicityItem> publicityItems) {
        if (publicityItems != null && publicityItems.size() > 0) {
            adapter.setData(publicityItems);
        } else {
            showEmptyCallback();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshPublicityEvent(AddPublicityEvent event) {
        PublicityItem publicityItem = event.getPublicityItem();
        if (publicityItem != null) {
            showSuccessCallback();
            adapter.addFirst(publicityItem);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyPublicityEvent(ModifyPublicityEvent event) {
        PublicityItem publicityItem = event.getPublicityItem();
        if (publicityItem != null) {
            adapter.modify(publicityItem);
        }
    }
}
