package com.jdp.hls.page.business.deed.list;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BaseLvAdapter;
import com.jdp.hls.adapter.DeedAdapter;
import com.jdp.hls.base.BaseDeedActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.AddBankInfoEvent;
import com.jdp.hls.event.ModifyBankInfoEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.model.entiy.DeedListInfo;
import com.jdp.hls.page.business.detail.personal.bankdetail.DeedPersonalBankActivity;
import com.jdp.hls.page.deed.company.immovable.DeedCompanyImmovableActivity;
import com.jdp.hls.page.deed.company.land.DeedCompanyLandActivity;
import com.jdp.hls.page.deed.company.property.DeedCompanyPropertyActivity;
import com.jdp.hls.page.deed.personal.immovable.DeedPersonalImmovableActivity;
import com.jdp.hls.page.deed.personal.land.DeedPersonalLandActivity;
import com.jdp.hls.page.deed.personal.property.DeedPersonalPropertyActivity;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PullToBottomListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:其它面积-列表
 * Create Time:2018/9/17 0017 下午 4:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedListActivity extends BaseTitleActivity implements DeedListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    private DeedAdapter deedAdapter;
    @Inject
    DeedListPresenter deedListPresenter;
    private String buildingId;
    private int certType;
    private int buildingType;

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        buildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
        certType = getIntent().getIntExtra(Constants.Extra.CERT_TYPE, 0);
        buildingType = getIntent().getIntExtra(Constants.Extra.BUILDING_TYPE, 0);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    protected int getContentView() {
        return R.layout.common_plv;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        deedListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return getCertTitle(certType);
    }

    public String getCertTitle(int certType) {
        String resutl = "证件列表";
        switch (certType) {
            case Status.CertType.LAND_PERSONAL:
            case Status.CertType.LAND_COMPANY:
                resutl = "土地证";
                break;
            case Status.CertType.PROPERTY_PERSONAL:
            case Status.CertType.PROPERTY_COMPANY:
                resutl = "产权证";
                break;
            case Status.CertType.IMMOVABLE_PERSONAL:
            case Status.CertType.IMMOVABLE_COMPANY:
                resutl = "不动产证";
                break;
        }
        return resutl;
    }

    @Override
    protected void initView() {
        deedAdapter = new DeedAdapter(this, null);
        plv.setAdapter(deedAdapter);
    }

    @Override
    protected void initData() {
        deedAdapter.setOnItemOperListener(new BaseLvAdapter.OnItemOperListener<DeedItem>() {
            @Override
            public void onItemDelete(DeedItem item, int position) {
                DialogUtil.showDoubleDialog(DeedListActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    deedListPresenter.deleteDeed(buildingId, certType, item.getId(), position);
                });
            }

            @Override
            public void onItemClick(DeedItem item) {
                switchDeedDetail(item.getCertType(), item.getId());

            }
        });
    }

    private void switchDeedDetail(int certType, int certId) {
        switch (certType) {
            case Status.CertType.LAND_PERSONAL:
                BaseDeedActivity.goActivity(DeedListActivity.this, DeedPersonalLandActivity.class, String
                        .valueOf(Status.FileType.PERSONAL_DEED_LAND), buildingId, String.valueOf(buildingType), certId);
                break;
            case Status.CertType.LAND_COMPANY:
                BaseDeedActivity.goActivity(DeedListActivity.this, DeedCompanyLandActivity.class, String
                        .valueOf(Status.FileType.COMPANY_DEED_LAND), buildingId, String.valueOf(buildingType), certId);
                break;
            case Status.CertType.PROPERTY_PERSONAL:
                BaseDeedActivity.goActivity(DeedListActivity.this, DeedPersonalPropertyActivity.class, String
                        .valueOf(Status.FileType.PERSONAL_DEED_PROPERTY), buildingId, String.valueOf(buildingType),
                        certId);
                break;
            case Status.CertType.PROPERTY_COMPANY:
                BaseDeedActivity.goActivity(DeedListActivity.this, DeedCompanyPropertyActivity.class, String
                        .valueOf(Status.FileType.COMPANY_DEED_PROPERTY), buildingId, String.valueOf(buildingType),
                        certId);
                break;
            case Status.CertType.IMMOVABLE_PERSONAL:
                BaseDeedActivity.goActivity(DeedListActivity.this, DeedPersonalImmovableActivity.class, String
                        .valueOf(Status.FileType.PERSONAL_DEED_IMMOVABLE), buildingId, String.valueOf(buildingType),
                        certId);
                break;
            case Status.CertType.IMMOVABLE_COMPANY:
                BaseDeedActivity.goActivity(DeedListActivity.this, DeedCompanyImmovableActivity.class, String
                        .valueOf(Status.FileType.COMPANY_DEED_IMMOVABLE), buildingId, String.valueOf(buildingType),
                        certId);
                break;
        }
    }

    @Override
    public void initNet() {
        deedListPresenter.getDeedList(buildingId, certType);
    }

    public static void goActivity(Context context, String buildingId, int certType, int buildingType) {
        Intent intent = new Intent(context, DeedListActivity.class);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.CERT_TYPE, certType);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addBankInfo(AddBankInfoEvent event) {
        showSuccessCallback();
//        deedAdapter.addFirst(event.getBankInfo());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyBankInfo(ModifyBankInfoEvent event) {
//        deedAdapter.modifyItem(event.getBankInfo());
    }

    @Override
    public void onGetDeedListSuccess(DeedListInfo deedListInfo) {
        boolean allowEdit = deedListInfo.isAllowEdit();
        if (allowEdit) {
            setRightClick("增加", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    switchDeedDetail(certType, 0);
                }
            });
        }
        setListView(deedListInfo.getItems(), deedAdapter, allowEdit);
    }



    @Override
    public void onDeleteDeed(int position) {
        deedAdapter.removeItem(position);
    }
}
