package com.jdp.hls.page.node.protocol.personal.lastst.pay.list;

import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BaseLvAdapter;
import com.jdp.hls.adapter.PayAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AddPayEvent;
import com.jdp.hls.model.entiy.ModifyPayEvent;
import com.jdp.hls.model.entiy.PayItem;
import com.jdp.hls.page.airphoto.unrecordbuilding.UnrecordBuildingListActivity;
import com.jdp.hls.page.node.protocol.personal.lastst.pay.detail.PayDetailActivity;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/11/16 0016 下午 2:19
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayListActivity extends BaseTitleActivity implements PayListContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @Inject
    PayListPresenter payListPresenter;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    private PayAdapter payAdapter;
    private String buildingId;
    private String buildingType;
    private boolean isAllowEdit;

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
        }
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        buildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
        buildingType = getIntent().getStringExtra(Constants.Extra.BUILDING_TYPE);
        isAllowEdit = getIntent().getBooleanExtra(Constants.Extra.EDITABLE, true);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_search_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        payListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "支付明细";
    }

    @Override
    protected void initView() {
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                payAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    protected void initData() {
        srl.stepRefresh(this);
        payAdapter = new PayAdapter(this, null, isAllowEdit);
        plv.setAdapter(payAdapter);
        if (isAllowEdit) {
            setRightClick("增加", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    PayDetailActivity.goActivity(PayListActivity.this, 0, buildingId, buildingType, isAllowEdit);
                }
            });
        }
        payAdapter.setOnItemOperListener(new BaseLvAdapter.OnItemOperListener<PayItem>() {
            @Override
            public void onItemDelete(PayItem item, int position) {
                DialogUtil.showDoubleDialog(PayListActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    payListPresenter.deletePay(item.getId(),item);
                });

            }

            @Override
            public void onItemClick(PayItem item) {
                PayDetailActivity.goActivity(PayListActivity.this, item.getId(), buildingId, buildingType, isAllowEdit);
            }
        });
    }

    @Override
    public void initNet() {
        payListPresenter.getPayList(buildingId, buildingType);
    }


    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addPay(AddPayEvent event) {
        LogUtil.e(TAG, "增加项目");
        payAdapter.addSearchFirst(event.getPayItem());
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyPay(ModifyPayEvent event) {
        LogUtil.e(TAG, "修改项目");
        payAdapter.modifySearchItem(event.getPayItem());
    }

    public static void goActivity(Context context, String buildingId, String buildingType, boolean isAllowEdit) {
        Intent intent = new Intent(context, PayListActivity.class);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.EDITABLE, isAllowEdit);
        context.startActivity(intent);
    }

    @Override
    public void onGetPayListSuccess(List<PayItem> payItemList) {
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(payItemList, payAdapter, keyword);
    }

    @Override
    public void onDeletePaySuccess(PayItem payItem) {
        payAdapter.removeSearchItem(payItem);
    }
}
