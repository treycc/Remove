package com.jdp.hls.page.innerdecoration.list;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.DecorationAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AddDecorationEvent;
import com.jdp.hls.model.entiy.DecorationItem;
import com.jdp.hls.model.entiy.ModifyDecorationEvent;
import com.jdp.hls.page.innerdecoration.detail.DecorationDetailActivity;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:装修明细表|附属物及其它构筑物明细表
 * Create Time:2018/9/12 0012 上午 9:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DecorationListActivity extends BaseTitleActivity implements DecorationListContract.View {
    @BindView(R.id.plv)
    ListView plv;
    @BindView(R.id.tv_payMoney)
    StringTextView tvPayMoney;
    @BindView(R.id.tv_payMoney_tip)
    TextView tvPayMoneyTip;
    @BindView(R.id.ll_totalMoneyBar)
    LinearLayout llTotalMoneyBar;
    private String evalId;
    private String buildingType;
    private String compensationType;
    private List<DecorationItem> decorationItemList = new ArrayList<>();
    @Inject
    DecorationListPresenter decorationListPresenter;
    private DecorationAdapter decorationAdapter;
    private boolean editable;

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        editable = getIntent().getBooleanExtra(Constants.Extra.EDITABLE,false);
        evalId = getIntent().getStringExtra(Constants.Extra.ID);
        buildingType = getIntent().getStringExtra(Constants.Extra.BUILDING_TYPE);
        compensationType = getIntent().getStringExtra(Constants.Extra.COMPENSATION_TYPE);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_decoration_list;
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
        return compensationType.equals(Status.CompensationType.DECORATION) ? "内装饰装修明细" : "附属物及其它构筑物明细";
    }

    @Override
    protected void initView() {
        decorationListPresenter.attachView(this);
        decorationAdapter = new DecorationAdapter(this, decorationItemList);
        plv.setAdapter(decorationAdapter);
    }

    @Override
    protected void initData() {
        if (editable) {
            setRightClick("增加", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    DecorationDetailActivity.goActivity(DecorationListActivity.this, evalId, buildingType, Integer
                            .valueOf(compensationType));
                }
            });
        }
        tvPayMoneyTip.setText(compensationType.equals(Status.CompensationType.DECORATION) ? "内装饰装修补偿总金额" : "附属物补偿总金额");
        decorationAdapter.setOnItemOperListener(new DecorationAdapter.OnItemOperListener() {
            @Override
            public void onItemDelete(String id, int position) {
                DialogUtil.showDoubleDialog(DecorationListActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    decorationListPresenter.deleteDecoration(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("Ids", id)
                            .addFormDataPart("buildingType", buildingType)
                            .build(), position);
                });
            }

            @Override
            public void onItemClick(DecorationItem decorationItem) {
                DecorationDetailActivity.goActivity(DecorationListActivity.this, decorationItem, buildingType,
                        Integer.valueOf(compensationType),editable);
            }
        });
    }

    @Override
    protected void initNet() {
        decorationListPresenter.getDecorationList(evalId, buildingType, compensationType);
    }

    public static void goActivity(Context context, String evalId, String buildingType, String compensationType,
                                  boolean editable) {
        Intent intent = new Intent(context, DecorationListActivity.class);
        intent.putExtra(Constants.Extra.ID, evalId);
        intent.putExtra(Constants.Extra.EDITABLE, editable);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.COMPENSATION_TYPE, compensationType);
        context.startActivity(intent);
    }

    @Override
    public void onGetDecorationListSuccess(List<DecorationItem> decorationItemList) {
        this.decorationItemList = decorationItemList;
        checkListSize(decorationItemList);
    }

    private void checkListSize(List<DecorationItem> decorationItemList) {
        if (decorationItemList != null && decorationItemList.size() > 0) {
            showSuccessCallback();
            decorationAdapter.setEditableData(decorationItemList,editable);
            llTotalMoneyBar.setVisibility(View.VISIBLE);
            tvPayMoney.setString(decorationAdapter.getTotalMoney());
        } else {
            llTotalMoneyBar.setVisibility(View.GONE);
            showEmptyCallback();
        }
    }

    @Override
    public void onDeleteDecorationSuccess(int position) {
        decorationAdapter.removeItem(position);
        checkListSize(decorationAdapter.getData());
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addItem(AddDecorationEvent event) {
        showSuccessCallback();
        decorationAdapter.addFirst(event.getDecorationItem());
        tvPayMoney.setString(decorationAdapter.getTotalMoney());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyItem(ModifyDecorationEvent event) {
        decorationAdapter.modify(event.getDecorationItem());
        tvPayMoney.setString(decorationAdapter.getTotalMoney());
    }

}
