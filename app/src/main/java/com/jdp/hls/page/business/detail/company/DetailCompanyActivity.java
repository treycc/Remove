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
import com.jdp.hls.fragment.LngLatFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DetailCompany;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.page.deed.company.immovable.DeedCompanyImmovableActivity;
import com.jdp.hls.page.deed.company.land.DeedCompanyLandActivity;
import com.jdp.hls.page.deed.company.license.DeedCompanyBusinessActivity;
import com.jdp.hls.page.deed.company.property.DeedCompanyPropertyActivity;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.et_detail_RealName)
    EnableEditText etDetailRealName;
    @BindView(R.id.et_detail_MobilePhone)
    EnableEditText etDetailMobilePhone;
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
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.et_detail_remark)
    EditText etDetailRemark;
    @BindView(R.id.switch_detail_publicity)
    Switch switchDetailPublicity;
    @BindView(R.id.et_mapping_currentOccupyArea)
    EnableEditText etDetailCurrentOccupyArea;
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

    @Override
    public void initVariable() {
        buildingId = getIntent().getStringExtra("buildingId");
    }

    @OnClick({R.id.ll_detail_licenseDeed, R.id.ll_detail_propertyDeed, R.id.ll_detail_landDeed, R.id
            .ll_detail_immovableDeed})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_detail_licenseDeed:
                String businessNum = tvDetailBusinessDeed.getText().toString().trim();
                goDeedActivity(DeedCompanyBusinessActivity.class, Status.FileType.COMPANY_DEED_BUSINESS, TextUtils
                        .isEmpty(businessNum));
                break;
            case R.id.ll_detail_propertyDeed:
                String propertyNum = tvDetailPropertyDeed.getText().toString().trim();
                goDeedActivity(DeedCompanyPropertyActivity.class, Status.FileType.COMPANY_DEED_PROPERTY, TextUtils
                        .isEmpty(propertyNum));
                break;
            case R.id.ll_detail_landDeed:
                String landNum = tvDetailLandDeed.getText().toString().trim();
                goDeedActivity(DeedCompanyLandActivity.class, Status.FileType.COMPANY_DEED_LAND, TextUtils
                        .isEmpty(landNum));
                break;
            case R.id.ll_detail_immovableDeed:
                String immovableNum = tvDetailImmovableDeed.getText().toString().trim();
                goDeedActivity(DeedCompanyImmovableActivity.class, Status.FileType.COMPANY_DEED_IMMOVABLE, TextUtils
                        .isEmpty(immovableNum));
                break;
        }
    }

    private void goDeedActivity(Class<? extends BaseDeedActivity> clazz, int fileType, boolean isAdd) {
        if (isAdd && !allowEdit) {
            ToastUtil.showText("证件还未添加");
            return;
        }
        BaseDeedActivity.goActivity(this, clazz, String.valueOf(fileType), buildingId, Status.BuildingTypeStr
                .COMPANY, isAdd);
    }

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
        return "高二路";
    }

    @Override
    protected void initView() {
        detailCompanyPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        rvPhotoPreview.create();
        switchDetailPublicity.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ifPublicity = isChecked;
        });
        lngLatFragment = (LngLatFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_lnglat);
    }

    @Override
    protected void initNet() {
        detailCompanyPresenter.getCompanyDetail(buildingId);
    }

    public static void goActivity(Context context, String buildingId) {
        Intent intent = new Intent(context, DetailCompanyActivity.class);
        intent.putExtra("buildingId", buildingId);
        context.startActivity(intent);
    }

    @Override
    public void onGetCompanyDetailSuccess(DetailCompany detailCompany) {
        estateCertNum = detailCompany.getEstateCertNum();
        licenseNo = detailCompany.getLicenseNo();
        landCertNum = detailCompany.getLandCertNum();
        propertyCertNum = detailCompany.getPropertyCertNum();
        etDetailCurrentOccupyArea.setText(detailCompany.getCurrentOccupyArea());
        etDetailCusCode.setText(detailCompany.getCusCode());
        etDetailEnterpriseName.setText(detailCompany.getEnterpriseName());
        etDetailAddress.setText(detailCompany.getAddress());
        etDetailRealName.setText(detailCompany.getRealName());
        etDetailMobilePhone.setText(detailCompany.getMobilePhone());
        etDetailBizInfo.setText(detailCompany.getBizInfo());
        etDetailRentInfo.setText(detailCompany.getRentInfo());
        etDetailRemark.setText(detailCompany.getRemark());
        tvDetailBusinessDeed.setText(licenseNo);
        tvDetailLandDeed.setText(landCertNum);
        tvDetailImmovableDeed.setText(estateCertNum);
        tvDetailPropertyDeed.setText(propertyCertNum);
        ifPublicity = detailCompany.isAllowPublicity();
        switchDetailPublicity.setChecked(ifPublicity);

        initLngLat(detailCompany.getLongitude(), detailCompany.getLatitude());
        allowEdit = detailCompany.isAllowEdit();
        rvPhotoPreview.setData(detailCompany.getFiles(), new FileConfig(Status.FileType.COMPANY_CURRENT,
                buildingId, String.valueOf(Status.BuildingType.COMPANY)), allowEdit);
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
        etDetailRealName.setEnabled(allowEdit);
        etDetailMobilePhone.setEnabled(allowEdit);
        etDetailBizInfo.setEnabled(allowEdit);
        etDetailRentInfo.setEnabled(allowEdit);
        etDetailRemark.setEnabled(allowEdit);
        etDetailCurrentOccupyArea.setEnabled(allowEdit);
        switchDetailPublicity.setEnabled(allowEdit);
        lngLatFragment.setEditable(allowEdit);

    }

    private void saveData() {
        String cusCode = etDetailCusCode.getText().toString().trim();
        String enterpriseName = etDetailEnterpriseName.getText().toString().trim();
        String address = etDetailAddress.getText().toString().trim();
        String mobile = etDetailMobilePhone.getText().toString().trim();
        String realName = etDetailRealName.getText().toString().trim();
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
                .addFormDataPart("Longitude", String.valueOf(longitude))
                .addFormDataPart("Latitude", String.valueOf(latitude))
                .addFormDataPart("RentInfo", rentInfo)
                .addFormDataPart("BizInfo", bizInfo)
                .addFormDataPart("RealName", realName)
                .addFormDataPart("MobilePhone", mobile)
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
        showSuccessAndFinish("保存成功");
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        rvPhotoPreview.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.LOCATION:
                    longitude = data.getDoubleExtra("lng", -1);
                    latitude = data.getDoubleExtra("lat", -1);
                    initLngLat(longitude, latitude);
                    break;
                case Status.FileType.COMPANY_DEED_PROPERTY:
                    tvDetailPropertyDeed.setText(data.getStringExtra(Constants.Extra.CERTNUM));
                    break;
                case Status.FileType.COMPANY_DEED_LAND:
                    tvDetailLandDeed.setText(data.getStringExtra(Constants.Extra.CERTNUM));
                    break;
                case Status.FileType.COMPANY_DEED_IMMOVABLE:
                    tvDetailImmovableDeed.setText(data.getStringExtra(Constants.Extra.CERTNUM));
                    break;
                case Status.FileType.COMPANY_DEED_BUSINESS:
                    tvDetailBusinessDeed.setText(data.getStringExtra(Constants.Extra.CERTNUM));
                    break;
                default:
                    break;
            }
        }
    }
}
