package com.jdp.hls.page.business.detail.company;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseDeedActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.ModifyBusinessEvent;
import com.jdp.hls.event.RefreshCertNumEvent;
import com.jdp.hls.fragment.LngLatFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DetailCompany;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.page.deed.company.bank.DeedCompanyBankActivity;
import com.jdp.hls.page.deed.company.immovable.DeedCompanyImmovableActivity;
import com.jdp.hls.page.deed.company.land.DeedCompanyLandActivity;
import com.jdp.hls.page.deed.company.license.DeedCompanyBusinessActivity;
import com.jdp.hls.page.deed.company.property.DeedCompanyPropertyActivity;
import com.jdp.hls.page.rosterdetail.contacts.list.ContactsListActivity;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
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
 * Description:公司业务详情
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailCompanyActivity extends BaseTitleActivity implements DetailCompanyContract.View {

    @BindView(R.id.et_detail_cusCode)
    EnableEditText etDetailCusCode;
    @BindView(R.id.et_detail_EnterpriseName)
    EnableEditText etDetailEnterpriseName;
    @BindView(R.id.et_detail_address)
    EnableEditText etDetailAddress;
    @BindView(R.id.et_detail_bizInfo)
    EnableEditText etDetailBizInfo;
    @BindView(R.id.et_detail_rentInfo)
    EnableEditText etDetailRentInfo;
    @BindView(R.id.tv_detail_businessDeed)
    TextView tvDetailBusinessDeed;
    @BindView(R.id.ll_detail_licenseDeed)
    LinearLayout llDetailLicenseDeed;
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
    EditText etDetailRemark;
    @BindView(R.id.switch_detail_publicity)
    SwitchMultiButton switchDetailPublicity;
    @BindView(R.id.et_mapping_currentOccupyArea)
    EnableEditText etDetailCurrentOccupyArea;
    @BindView(R.id.tv_detail_bankAccount)
    TextView tvDetailBankAccount;
    @BindView(R.id.ll_detail_bankDeed)
    LinearLayout llDetailBankDeed;
    @BindView(R.id.rv_photo_preview_procedure)
    PreviewRecyclerView rvPhotoPreviewProcedure;
    @BindView(R.id.rv_photo_preview_house)
    PreviewRecyclerView rvPhotoPreviewHouse;
    @BindView(R.id.rv_photo_preview_otherCert)
    PreviewRecyclerView rvPhotoPreviewOtherCert;
    @BindView(R.id.switch_detail_isUrgent)
    SwitchMultiButton switchDetailIsUrgent;
    @BindView(R.id.tv_personCount)
    StringTextView tvPersonCount;
    @BindView(R.id.ll_owner)
    LinearLayout llOwner;
    private String buildingId;
    private boolean ifPublicity;
    @Inject
    DetailCompanyPresenter detailCompanyPresenter;
    private String estateCertNum;
    private String licenseNo;
    private String landCertNum;
    private String propertyCertNum;
    private double longitude;
    private double latitude;
    private LngLatFragment lngLatFragment;
    private boolean allowEdit;
    private String address;
    private String cusCode;
    private boolean isUrgent;
    private String enterpriseName;

    @Override
    public void initVariable() {
        buildingId = getIntent().getStringExtra("buildingId");
    }

    @OnClick({R.id.ll_detail_licenseDeed, R.id.ll_detail_propertyDeed, R.id.ll_detail_landDeed, R.id
            .ll_detail_immovableDeed, R.id.ll_detail_bankDeed, R.id.ll_owner})
    public void click(View view) {
        switch (view.getId()) {
//            case R.id.ll_detail_licenseDeed:
//                String businessNum = tvDetailBusinessDeed.getText().toString().trim();
//                goDeedActivity(DeedCompanyBusinessActivity.class, Status.FileType.COMPANY_DEED_BUSINESS, TextUtils
//                        .isEmpty(businessNum));
//                break;
//            case R.id.ll_detail_propertyDeed:
//                String propertyNum = tvDetailPropertyDeed.getText().toString().trim();
//                goDeedActivity(DeedCompanyPropertyActivity.class, Status.FileType.COMPANY_DEED_PROPERTY, TextUtils
//                        .isEmpty(propertyNum));
//                break;
//            case R.id.ll_detail_landDeed:
//                String landNum = tvDetailLandDeed.getText().toString().trim();
//                goDeedActivity(DeedCompanyLandActivity.class, Status.FileType.COMPANY_DEED_LAND, TextUtils
//                        .isEmpty(landNum));
//                break;
//            case R.id.ll_detail_immovableDeed:
//                String immovableNum = tvDetailImmovableDeed.getText().toString().trim();
//                goDeedActivity(DeedCompanyImmovableActivity.class, Status.FileType.COMPANY_DEED_IMMOVABLE, TextUtils
//                        .isEmpty(immovableNum));
//                break;
//            case R.id.ll_detail_bankDeed:
//                String openAccountCertNum = tvDetailBankAccount.getText().toString().trim();
//                goDeedActivity(DeedCompanyBankActivity.class, Status.FileType.BANK, TextUtils.isEmpty
//                        (openAccountCertNum));
//                break;
            case R.id.ll_owner:
                ContactsListActivity.goActivity(this, buildingId, Status.BuildingType.COMPANY, allowEdit);
        }
    }

//    private void goDeedActivity(Class<? extends BaseDeedActivity> clazz, int fileType, boolean isAdd) {
//        if (isAdd && !allowEdit) {
//            ToastUtil.showText("证件还未添加");
//            return;
//        }
//        BaseDeedActivity.goActivity(this, clazz, String.valueOf(fileType), buildingId, Status.BuildingTypeStr
//                .COMPANY, isAdd);
//    }

    @Override
    protected int getContentView() {
        return R.layout.activity_business_company_detail;
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
        detailCompanyPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        switchDetailPublicity.setOnSwitchListener((position, tabText) -> {
            ifPublicity = position==1;
        });
        switchDetailIsUrgent.setOnSwitchListener((position, tabText) -> {
            isUrgent = position == 1;
        });
        lngLatFragment = (LngLatFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_lnglat);
    }

    @Override
    public void initNet() {
        detailCompanyPresenter.getCompanyDetail(buildingId);
    }

    public static void goActivity(Context context, String buildingId) {
        Intent intent = new Intent(context, DetailCompanyActivity.class);
        intent.putExtra("buildingId", buildingId);
        context.startActivity(intent);
    }

    @Override
    public void onGetCompanyDetailSuccess(DetailCompany detailCompany) {
        setContentTitle(detailCompany.getAddress());
        tvPersonCount.setHint(String.format("共%d人", detailCompany.getPersonCount()));
        estateCertNum = detailCompany.getEstateCertNum();
        licenseNo = detailCompany.getLicenseNo();
        landCertNum = detailCompany.getLandCertNum();
        propertyCertNum = detailCompany.getPropertyCertNum();
        etDetailCurrentOccupyArea.setText(detailCompany.getCurrentOccupyArea());
        etDetailCusCode.setText(detailCompany.getCusCode());
        etDetailEnterpriseName.setText(detailCompany.getEnterpriseName());
        etDetailAddress.setText(detailCompany.getAddress());
        etDetailBizInfo.setText(detailCompany.getBizInfo());
        etDetailRentInfo.setText(detailCompany.getRentInfo());
        etDetailRemark.setText(detailCompany.getRemark());
        tvDetailBankAccount.setText(detailCompany.getBankAccount());
        tvDetailBusinessDeed.setText(licenseNo);
        tvDetailLandDeed.setText(landCertNum);
        tvDetailImmovableDeed.setText(estateCertNum);
        tvDetailPropertyDeed.setText(propertyCertNum);

        ifPublicity = detailCompany.isAllowPublicity();
        isUrgent = detailCompany.isUrgent();
        switchDetailPublicity.setSelectedTab(ifPublicity ? 1 : 0);
        switchDetailIsUrgent.setSelectedTab(isUrgent ? 1 : 0);

        initLngLat(detailCompany.getLongitude(), detailCompany.getLatitude());
        allowEdit = detailCompany.isAllowEdit();
        if (allowEdit) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    saveData();
                }
            });
        }
        etDetailCusCode.setEnabled(allowEdit);
        etDetailEnterpriseName.setEnabled(allowEdit);
        etDetailAddress.setEnabled(allowEdit);
        etDetailBizInfo.setEnabled(allowEdit);
        etDetailRentInfo.setEnabled(allowEdit);
        etDetailRemark.setEnabled(allowEdit);
        etDetailCurrentOccupyArea.setEnabled(allowEdit);
        switchDetailPublicity.setEnabled(allowEdit);
        switchDetailIsUrgent.setEnabled(allowEdit);
        lngLatFragment.setEditable(allowEdit);
        rvPhotoPreviewHouse.setData(detailCompany.getFiles(), new FileConfig(Status.FileType
                .COMPANY_CURRENT, this.buildingId, String.valueOf(Status.BuildingType.COMPANY)), allowEdit);
        rvPhotoPreviewProcedure.setData(detailCompany.getApprovalFiles(), new FileConfig(Status.FileType
                .PROCEDURE, this.buildingId, String.valueOf(Status.BuildingType.COMPANY)), allowEdit);
        rvPhotoPreviewOtherCert.setData(detailCompany.getOtherFiles(), new FileConfig(Status.FileType
                .OTHER, this.buildingId, String.valueOf(Status.BuildingType.COMPANY)), allowEdit);
    }

    private void saveData() {
        cusCode = etDetailCusCode.getText().toString().trim();
        address = etDetailAddress.getText().toString().trim();
        enterpriseName = etDetailEnterpriseName.getText().toString().trim();
        String remark = etDetailRemark.getText().toString().trim();
        String rentInfo = etDetailRentInfo.getText().toString().trim();
        String bizInfo = etDetailBizInfo.getText().toString().trim();
        String currentOccupyArea = etDetailCurrentOccupyArea.getText().toString().trim();
        detailCompanyPresenter.modifyCompanyDetail(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", buildingId)
                .addFormDataPart("EnterpriseName", enterpriseName)
                .addFormDataPart("CusCode", cusCode)
                .addFormDataPart("Address", address)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("IsAllowPublicity", String.valueOf(ifPublicity))
                .addFormDataPart("IsUrgent", String.valueOf(isUrgent))
                .addFormDataPart("Longitude", String.valueOf(longitude))
                .addFormDataPart("Latitude", String.valueOf(latitude))
                .addFormDataPart("RentInfo", rentInfo)
                .addFormDataPart("BizInfo", bizInfo)
                .addFormDataPart("CurrentOccupyArea", currentOccupyArea)
                .build());
    }

    private void initLngLat(double lng, double lat) {
        longitude = lng;
        latitude = lat;
        if (longitude != 0 && latitude != 0) {
            lngLatFragment.setLnglat(longitude, latitude);
        }
    }

    @Override
    public void onModifyCompanyDetailSuccess() {
        ModifyBusinessEvent businessEvent = new ModifyBusinessEvent();
        businessEvent.setBuildingType(Status.BuildingType.COMPANY);
        businessEvent.setAddress(address);
        businessEvent.setCusCode(cusCode);
        businessEvent.setEnterpriseName(enterpriseName);
        businessEvent.setBuildingId(buildingId);
        businessEvent.setUrgent(isUrgent);
        EventBus.getDefault().post(businessEvent);
        showSuccessDialogAndFinish("保存成功");
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
                    rvPhotoPreviewOtherCert.onActivityResult(requestCode, data);
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
    public void refreshDeedEvent(RefreshCertNumEvent event) {
        if (event.getBuildType() == Status.BuildingType.COMPANY) {
            switch (event.getCertType()) {
                case Status.FileType.COMPANY_DEED_PROPERTY:
                    tvDetailPropertyDeed.setText(event.getCertNum());
                    break;
                case Status.FileType.COMPANY_DEED_LAND:
                    tvDetailLandDeed.setText(event.getCertNum());
                    break;
                case Status.FileType.COMPANY_DEED_IMMOVABLE:
                    tvDetailImmovableDeed.setText(event.getCertNum());
                    break;
                case Status.FileType.COMPANY_DEED_BUSINESS:
                    tvDetailBusinessDeed.setText(event.getCertNum());
                    break;
                case Status.FileType.BANK:
                    tvDetailBankAccount.setText(event.getCertNum());
                    break;
                default:
                    break;
            }
        }

    }
}
