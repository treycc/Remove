package com.jdp.hls.page.business.detail.personal;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.activity.FamilyRelationActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DetailPersonal;
import com.jdp.hls.page.deed.personal.immovable.DeedPersonalImmovableActivity;
import com.jdp.hls.page.deed.personal.land.DeedPersonalLandActivity;
import com.jdp.hls.page.deed.personal.property.DeedPersonalPropertyActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:个人业务详情
 * Create Time:2018/9/10 0010 上午 8:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DetailPersonalActivity extends BaseTitleActivity implements DetailPersonalContract.View {

    @BindView(R.id.et_detail_syscode)
    EnableEditText etDetailSyscode;
    @BindView(R.id.et_detail_realName)
    EnableEditText etDetailRealName;
    @BindView(R.id.et_detail_mobile)
    EnableEditText etDetailMobile;
    @BindView(R.id.et_detail_idcard)
    EnableEditText etDetailIdcard;
    @BindView(R.id.et_detail_address)
    EnableEditText etDetailAddress;
    @BindView(R.id.rl_familyRelation)
    RelativeLayout rlFamilyRelation;
    @BindView(R.id.spinner_detail_socialRelation)
    KSpinner spinnerDetailSocialRelation;
    @BindView(R.id.ll_detail_socialRelation)
    LinearLayout llDetailSocialRelation;
    @BindView(R.id.ll_detail_tempHouse)
    LinearLayout llDetailTempHouse;
    @BindView(R.id.ll_detail_hasShop)
    LinearLayout llDetailHasShop;
    @BindView(R.id.et_detail_shopArea)
    EnableEditText etDetailShopArea;
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
    @BindView(R.id.switch_detail_needHouse)
    Switch switchDetailNeedHouse;
    @BindView(R.id.switch_detail_publicity)
    Switch switchDetailPublicity;
    @BindView(R.id.switch_detail_hasShop)
    Switch switchDetailHasShop;
    private String buildingId;
    @Inject
    DetailPersonalPresenter detailPersonalPresenter;

    private boolean hasShop;
    private boolean needHouse;
    private boolean ifPublicity;
    private int socialRelation;
    private DetailPersonal detailPersonal;

    @OnClick({R.id.rl_familyRelation, R.id.ll_detail_propertyDeed, R.id.ll_detail_landDeed, R.id
            .ll_detail_immovableDeed})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_familyRelation:
                GoUtil.goActivity(this, FamilyRelationActivity.class);
                break;
            case R.id.ll_detail_propertyDeed:
                DeedPersonalPropertyActivity.goActivity(this,detailPersonal.getHouseId(),TextUtils.isEmpty(detailPersonal.getPropertyCertNum()));
                break;
            case R.id.ll_detail_landDeed:
                DeedPersonalLandActivity.goActivity(this,detailPersonal.getHouseId(),TextUtils.isEmpty(detailPersonal.getLandCertNum()));
                break;
            case R.id.ll_detail_immovableDeed:
                DeedPersonalImmovableActivity.goActivity(this,detailPersonal.getHouseId(),TextUtils.isEmpty(detailPersonal.getEstateCertNum()));
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
        return "高二路";
    }

    @Override
    protected void initView() {
        detailPersonalPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ToastUtil.showText("保存");
            }
        });
        initSpinners();
    }

    private void initSpinners() {
        List<TDict> societyRelationList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType
                .SOCIAL_RELATION);
        spinnerDetailSocialRelation.setDicts(societyRelationList, typeId -> {
            socialRelation = typeId;
        });
        switchDetailNeedHouse.setOnCheckedChangeListener((buttonView, isChecked) -> {
            needHouse = isChecked;
        });
        switchDetailPublicity.setOnCheckedChangeListener((buttonView, isChecked) -> {
            ifPublicity = isChecked;
        });
        switchDetailHasShop.setOnCheckedChangeListener((buttonView, isChecked) -> {
            hasShop = isChecked;
        });
    }

    @Override
    protected void initNet() {
        detailPersonalPresenter.getPersonalDetail(buildingId);
    }

    @Override
    public void onGetPersonalDetailSuccess(DetailPersonal detailPersonal) {
        this.detailPersonal = detailPersonal;
        etDetailSyscode.setText(detailPersonal.getSysCode());
        etDetailRealName.setText(detailPersonal.getRealName());
        etDetailMobile.setText(detailPersonal.getMobilePhone());
        etDetailAddress.setText(detailPersonal.getAddress());
        etDetailShopArea.setText(String.valueOf(detailPersonal.getBizUseArea()));
        tvDetailPropertyDeed.setText(detailPersonal.getPropertyCertNum());
        tvDetailLandDeed.setText(detailPersonal.getLandCertNum());
        tvDetailImmovableDeed.setText(detailPersonal.getEstateCertNum());
        etDetailRemark.setText(detailPersonal.getRemark());
    }

    public static void goActivity(Context context, String buildingId) {
        Intent intent = new Intent(context, DetailPersonalActivity.class);
        intent.putExtra("buildingId", buildingId);
        context.startActivity(intent);
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }

}
