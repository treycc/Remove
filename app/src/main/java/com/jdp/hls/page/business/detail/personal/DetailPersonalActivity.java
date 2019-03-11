package com.jdp.hls.page.business.detail.personal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.ModifyBusinessEvent;
import com.jdp.hls.event.RefreshCertCountEvent;
import com.jdp.hls.event.RefreshCertNumEvent;
import com.jdp.hls.fragment.LngLatFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DetailPersonal;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.page.business.deed.list.DeedListActivity;
import com.jdp.hls.page.business.detail.personal.branklist.BankListActivity;
import com.jdp.hls.page.familyrelation.list.FamilyRelationActivity;
import com.jdp.hls.page.rosterdetail.contacts.list.ContactsListActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.OtherUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import lib.kingja.switchbutton.SwitchMultiButton;
import okhttp3.MultipartBody;

/**
 * Description:住宅业务详情
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailPersonalActivity extends BaseTitleActivity implements DetailPersonalContract.View {

    @BindView(R.id.et_detail_cusCode)
    EnableEditText etDetailcusCode;
    @BindView(R.id.et_detail_address)
    EnableEditText etDetailAddress;
    @BindView(R.id.rl_unrecordBuilding)
    RelativeLayout rlFamilyRelation;
    @BindView(R.id.ll_detail_tempHouse)
    LinearLayout llDetailTempHouse;
    @BindView(R.id.ll_detail_hasShop)
    LinearLayout llDetailHasShop;
    @BindView(R.id.et_detail_bizUseArea)
    EnableEditText etDetailBizUseArea;
    @BindView(R.id.tv_detail_propertyDeed)
    TextView tvDetailPropertyDeed;
    @BindView(R.id.ll_detail_propertyDeed)
    LinearLayout llDetailPropertyDeed;
    @BindView(R.id.tv_detail_landDeed)
    TextView tvDetailLandDeed;
    @BindView(R.id.ll_detail_landDeed)
    LinearLayout llDetailLandDeed;
    @BindView(R.id.tv_detail_immovableDeed)
    TextView tvDetailImmovableDeed;
    @BindView(R.id.ll_detail_immovableDeed)
    LinearLayout llDetailImmovableDeed;
    @BindView(R.id.et_detail_remark)
    EnableEditText etDetailRemark;
    @BindView(R.id.switch_detail_needHouse)
    SwitchMultiButton switchDetailNeedHouse;
    @BindView(R.id.switch_detail_publicity)
    SwitchMultiButton switchDetailPublicity;
    @BindView(R.id.switch_detail_hasShop)
    SwitchMultiButton switchDetailHasShop;
    @BindView(R.id.ll_businessArea)
    LinearLayout llBusinessArea;
    @BindView(R.id.rv_photo_preview_procedure)
    PreviewRecyclerView rvPhotoPreviewProcedure;
    @BindView(R.id.rv_photo_preview_house)
    PreviewRecyclerView rvPhotoPreviewHouse;
    @BindView(R.id.tv_detail_bankAccount)
    TextView tvDetailBankAccount;
    @BindView(R.id.et_detail_vrUrl)
    EnableEditText etDetailVrUrl;
    @BindView(R.id.switch_detail_isUrgent)
    SwitchMultiButton switchDetailIsUrgent;
    @BindView(R.id.tv_personCount)
    StringTextView tvPersonCount;
    @BindView(R.id.ll_owner)
    LinearLayout llOwner;
    @BindView(R.id.ll_detail_bankDeed)
    LinearLayout llDetailBankDeed;
    private String buildingId;
    @Inject
    DetailPersonalPresenter detailPersonalPresenter;
    private double longitude;
    private double latitude;
    private boolean hasShop;
    private boolean needHouse;
    private boolean ifPublicity;
    private DetailPersonal detailPersonal;
    private LngLatFragment lngLatFragment;
    private boolean allowEdit;
    private String address;
    private String cusCode;
    private String vrUrl;
    private boolean isUrgent;
    private int landCount;
    private int estateCount;
    private int propertyCount;

    @OnClick({R.id.rl_unrecordBuilding, R.id.ll_detail_propertyDeed, R.id.ll_detail_landDeed, R.id
            .ll_detail_immovableDeed, R.id.ll_detail_bankDeed, R.id.ll_owner})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_unrecordBuilding:
                FamilyRelationActivity.goActivity(this, detailPersonal.getHouseId());
                break;
            case R.id.ll_detail_propertyDeed:
                DeedListActivity.goActivity(this, buildingId, Status.CertType.PROPERTY_PERSONAL, Status.BuildingType
                        .PERSONAL);
                break;
            case R.id.ll_detail_landDeed:
                DeedListActivity.goActivity(this, buildingId, Status.CertType.LAND_PERSONAL, Status.BuildingType
                        .PERSONAL);
                break;
            case R.id.ll_detail_immovableDeed:
                DeedListActivity.goActivity(this, buildingId, Status.CertType.IMMOVABLE_PERSONAL, Status.BuildingType
                        .PERSONAL);
                break;
            case R.id.ll_detail_bankDeed:
                BankListActivity.goActivity(this, buildingId);
                break;
            case R.id.ll_owner:
                ContactsListActivity.goActivity(this, buildingId, Status.BuildingType.PERSONAL, allowEdit);
                break;
        }
    }


    @Override
    public void initVariable() {
        buildingId = getIntent().getStringExtra("buildingId");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_business_personal_detail;
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
        return "";
    }

    @Override
    protected void initView() {
        EventBus.getDefault().register(this);
        detailPersonalPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        initSpinners();
        lngLatFragment = (LngLatFragment) getSupportFragmentManager().findFragmentById(R.id
                .fragment_lnglat);
    }

    private void initSpinners() {
        switchDetailNeedHouse.setOnSwitchListener((position, tabText) -> {
            needHouse = position == 1;
        });
        switchDetailPublicity.setOnSwitchListener((position, tabText) -> {
            ifPublicity = position == 1;
        });
        switchDetailIsUrgent.setOnSwitchListener((position, tabText) -> {
            isUrgent = position == 1;
        });
        switchDetailHasShop.setOnSwitchListener((position, tabText) -> {
            hasShop = position == 1;
            llBusinessArea.setVisibility(hasShop ? View.VISIBLE : View.GONE);
        });
    }

    @Override
    public void initNet() {
        detailPersonalPresenter.getPersonalDetail(buildingId);
    }

    @Override
    public void onGetPersonalDetailSuccess(DetailPersonal detailPersonal) {
        this.detailPersonal = detailPersonal;
        tvPersonCount.setHint(String.format("共%d人", detailPersonal.getPersonCount()));
        setContentTitle(detailPersonal.getAddress());
        hasShop = detailPersonal.isShop();
        isUrgent = detailPersonal.isUrgent();
        needHouse = detailPersonal.isNeedTempHouse();
        ifPublicity = detailPersonal.isAllowPublicity();
        etDetailcusCode.setText(detailPersonal.getCusCode());
        etDetailAddress.setText(detailPersonal.getAddress());
        etDetailVrUrl.setString(detailPersonal.getVRUrl());
        etDetailBizUseArea.setText(String.valueOf(detailPersonal.getBizUseArea()));
        landCount = detailPersonal.getLandCount();
        estateCount = detailPersonal.getEstateCount();
        propertyCount = detailPersonal.getPropertyCount();

        tvDetailLandDeed.setText(String.format(getString(R.string.deed_count), landCount));
        tvDetailImmovableDeed.setText(String.format(getString(R.string.deed_count), estateCount));
        tvDetailPropertyDeed.setText(String.format(getString(R.string.deed_count), propertyCount));

//        tvDetailBankAccount.setText(detailPersonal.getBankAccount());
        etDetailRemark.setText(detailPersonal.getRemark());

        switchDetailHasShop.setSelectedTab(hasShop ? 1 : 0);
        switchDetailNeedHouse.setSelectedTab(needHouse ? 1 : 0);
        switchDetailPublicity.setSelectedTab(ifPublicity ? 1 : 0);
        switchDetailIsUrgent.setSelectedTab(isUrgent ? 1 : 0);

        llBusinessArea.setVisibility(hasShop ? View.VISIBLE : View.GONE);
        initLngLat(detailPersonal.getLongitude(), detailPersonal.getLatitude());
        allowEdit = detailPersonal.isAllowEdit();
        if (allowEdit) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    modifyData();
                }
            });
        }
        lngLatFragment.setEditable(allowEdit);
        etDetailcusCode.setEnabled(allowEdit);
        etDetailAddress.setEnabled(allowEdit);
        etDetailVrUrl.setEnabled(allowEdit);
        etDetailBizUseArea.setEnabled(allowEdit);
        etDetailRemark.setEnabled(allowEdit);
        switchDetailHasShop.setEnabled(allowEdit);
        switchDetailNeedHouse.setEnabled(allowEdit);
        switchDetailPublicity.setEnabled(allowEdit);
        switchDetailIsUrgent.setEnabled(allowEdit);
        lngLatFragment.setEditable(allowEdit);
        rvPhotoPreviewHouse.setData(detailPersonal.getFiles(), new FileConfig(Status.FileType
                .PERSONAL_CURRENT,
                this.buildingId, String.valueOf(Status.BuildingType.PERSONAL)), allowEdit);
        rvPhotoPreviewProcedure.setData(detailPersonal.getApprovalFiles(), new FileConfig(Status.FileType
                .PROCEDURE,
                this.buildingId, String.valueOf(Status.BuildingType.PERSONAL)), allowEdit);

    }

    private void initLngLat(double lng, double lat) {
        longitude = lng;
        latitude = lat;
        if (longitude != 0 && latitude != 0) {
            lngLatFragment.setLnglat(longitude, latitude);
        }
    }

    private void modifyData() {
        cusCode = etDetailcusCode.getText().toString().trim();
        address = etDetailAddress.getText().toString().trim();
        String remark = etDetailRemark.getText().toString().trim();
        String bizUseArea = etDetailBizUseArea.getText().toString().trim();
        vrUrl = etDetailVrUrl.getText().toString().trim();
        if (!CheckUtil.checkEmpty(address, "请输入地址")) {
            return;
        }
        detailPersonalPresenter.modifyPersonalDetail(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", buildingId)
                .addFormDataPart("CusCode", cusCode)
                .addFormDataPart("IsShop", String.valueOf(hasShop))
                .addFormDataPart("IsUrgent", String.valueOf(isUrgent))
                .addFormDataPart("NeedTempHouse", String.valueOf(needHouse))
                .addFormDataPart("BizUseArea", bizUseArea)
                .addFormDataPart("Address", address)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("IsAllowPublicity", String.valueOf(ifPublicity))
                .addFormDataPart("Longitude", String.valueOf(longitude))
                .addFormDataPart("Latitude", String.valueOf(latitude))
                .addFormDataPart("VRUrl", vrUrl)
                .build());
    }


    @Override
    public void onModifyPersonalDetailSuccess() {
        ModifyBusinessEvent businessEvent = new ModifyBusinessEvent();
        businessEvent.setBuildingType(Status.BuildingType.PERSONAL);
        businessEvent.setAddress(address);
        businessEvent.setCusCode(cusCode);
        businessEvent.setBuildingId(buildingId);
        businessEvent.setUrgent(isUrgent);
        businessEvent.setVRUrl(vrUrl);
        EventBus.getDefault().post(businessEvent);
        showSuccessDialogAndFinish("保存成功");
    }

    public static void goActivity(Context context, String buildingId) {
        Intent intent = new Intent(context, DetailPersonalActivity.class);
        intent.putExtra("buildingId", buildingId);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.LOCATION:
                    longitude = data.getDoubleExtra("lng", -1);
                    latitude = data.getDoubleExtra("lat", -1);
                    initLngLat(longitude, latitude);
                    break;
                case Constants.RequestCode.PHOTO_PREVIEW:
                    rvPhotoPreviewHouse.onActivityResult(requestCode, data);
                    rvPhotoPreviewProcedure.onActivityResult(requestCode, data);
                    break;
                case Constants.RequestCode.ContactsList:
                    int personCount = data.getIntExtra(Constants.Extra.Number, 0);
                    tvPersonCount.setHint(String.format("共%d人", personCount));
                    break;
                default:
                    break;
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshDeedEvent(RefreshCertCountEvent event) {
        switch (event.getCertType()) {
            case Status.CertType.LAND_PERSONAL:
                landCount = OtherUtil.getNewCount(landCount, event.isAdd());
                tvDetailLandDeed.setText(String.format(getString(R.string.deed_count), OtherUtil.getNewCount
                        (landCount, event.isAdd())));
                break;
            case Status.CertType.PROPERTY_PERSONAL:
                propertyCount = OtherUtil.getNewCount(propertyCount, event.isAdd());
                tvDetailPropertyDeed.setText(String.format(getString(R.string.deed_count), propertyCount));
                break;
            case Status.CertType.IMMOVABLE_PERSONAL:
                estateCount = OtherUtil.getNewCount(propertyCount, event.isAdd());
                tvDetailImmovableDeed.setText(String.format(getString(R.string.deed_count), estateCount));
                break;
            case Status.FileType.BANK:
//                tvDetailBankAccount.setText(event.getCertNum());
                break;
            default:
                break;
        }
    }


}
