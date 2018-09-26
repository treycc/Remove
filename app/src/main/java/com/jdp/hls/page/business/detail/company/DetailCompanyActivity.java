package com.jdp.hls.page.business.detail.company;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.activity.FamilyRelationActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DetailCompany;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.page.deed.company.immovable.DeedCompanyImmovableActivity;
import com.jdp.hls.page.deed.company.land.DeedCompanyLandActivity;
import com.jdp.hls.page.deed.company.license.DeedCompanyLicenseActivity;
import com.jdp.hls.page.deed.company.property.DeedCompanyPropertyActivity;
import com.jdp.hls.page.deed.personal.immovable.DeedPersonalImmovableActivity;
import com.jdp.hls.page.deed.personal.land.DeedPersonalLandActivity;
import com.jdp.hls.page.deed.personal.property.DeedPersonalPropertyActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;
    @BindView(R.id.et_detail_remark)
    EditText etDetailRemark;
    private String buildingId;

    @Inject
    DetailCompanyPresenter detailCompanyPresenter;
    private String estateCertNum;
    private String licenseNo;
    private String landCertNum;
    private String propertyCertNum;

    @Override
    public void initVariable() {
        buildingId = getIntent().getStringExtra("buildingId");
    }

    @OnClick({R.id.ll_detail_licenseDeed, R.id.ll_detail_propertyDeed, R.id.ll_detail_landDeed, R.id
            .ll_detail_immovableDeed})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_detail_licenseDeed:
                DeedCompanyLicenseActivity.goActivity(this, buildingId, TextUtils.isEmpty(licenseNo));
                break;
            case R.id.ll_detail_propertyDeed:
                DeedCompanyPropertyActivity.goActivity(this, buildingId, TextUtils.isEmpty(propertyCertNum));
                break;
            case R.id.ll_detail_landDeed:
                DeedCompanyLandActivity.goActivity(this, buildingId, TextUtils.isEmpty(landCertNum));
                break;
            case R.id.ll_detail_immovableDeed:
                DeedCompanyImmovableActivity.goActivity(this, buildingId, TextUtils.isEmpty(estateCertNum));
                break;
        }
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
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ToastUtil.showText("保存");
            }
        });
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
        List<ImgInfo> houseFiles = detailCompany.getHouseFiles();
        if (houseFiles != null && houseFiles.size() > 0) {
            rvPhotoPreview.setData(houseFiles);
        }
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }
}
