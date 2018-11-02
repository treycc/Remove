package com.jdp.hls.page.deed.company.land;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseDeedActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.event.RefreshCertNumEvent;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedCompanyLand;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:土地证-企业
 * Create Time:2018/9/10 0010 上午 10:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyLandActivity extends BaseDeedActivity implements DeedCompanyLandContract.View {
    @BindView(R.id.spinner_landType)
    KSpinner spinnerLandType;
    @BindView(R.id.et_land_certNum)
    EnableEditText etLandCertNum;
    @BindView(R.id.et_land_area)
    EnableEditText etLandArea;
    @BindView(R.id.et_land_mu)
    EnableEditText etLandMu;
    @BindView(R.id.et_land_address)
    EnableEditText etLandAddress;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.et_land_landUse)
    EnableEditText etLandLandUse;
    private List<TDict> landTypeList;
    private int landTypeId;
    @Inject
    DeedCompanyLandPresenter deedCompanyLandPresenter;
    private String certNum;
    private String area;
    private String mu;
    private String address;
    private String remark;
    private String landUse;

    @Override
    public void initVariable() {
        super.initVariable();
        landTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_TYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_company_land;
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
        return "土地证";
    }

    @Override
    protected void initView() {
        deedCompanyLandPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
        spinnerLandType.setDicts(landTypeList, typeId -> {
            landTypeId = typeId;
        });
        landTypeId = spinnerLandType.getDefaultTypeId();
    }

    @Override
    protected void initNet() {
        if (mIsAdd) {
            setRightClick("保存", addListener);
        } else {
            deedCompanyLandPresenter.getDeedCompanyLand(mBuildingId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyLandPresenter.modifyDeedCompanyLand(getRequestBody());
            }
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyLandPresenter.addDeedCompanyLand(getRequestBody());
            }
        }
    };

    public boolean checkDataVaildable() {
        certNum = etLandCertNum.getText().toString().trim();
        area = etLandArea.getText().toString().trim();
        mu = etLandMu.getText().toString().trim();
        remark = etRemark.getText().toString().trim();
        address = etLandAddress.getText().toString().trim();
        landUse = etLandLandUse.getText().toString().trim();
        return CheckUtil.checkEmpty(certNum, "请输入证件号") && CheckUtil.checkEmpty(address, "请输入地址");
    }

    @NonNull
    private RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("LandNatureTypeId", String.valueOf(landTypeId))
                .addFormDataPart("LandUse", landUse)
                .addFormDataPart("Area", area)
                .addFormDataPart("Mu", mu)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("Address", address).build();
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        }
        etLandCertNum.setEnabled(allowEdit);
        etLandArea.setEnabled(allowEdit);
        etLandMu.setEnabled(allowEdit);
        etLandAddress.setEnabled(allowEdit);
        spinnerLandType.enable(allowEdit);
        etRemark.setEnabled(allowEdit);
        etLandLandUse.setEnabled(allowEdit);
    }

    public static void goActivity(Context context, String enterpriseId, boolean isAdd) {
        Intent intent = new Intent(context, DeedCompanyLandActivity.class);
        intent.putExtra("enterpriseId", enterpriseId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

    @Override
    public void onGetDeedCompanyLandSuccess(DeedCompanyLand deedCompanyLand) {
        etLandCertNum.setText(deedCompanyLand.getCertNum());
        etLandArea.setText(String.valueOf(deedCompanyLand.getArea()));
        etLandMu.setText(String.valueOf(deedCompanyLand.getMu()));
        etLandAddress.setText(deedCompanyLand.getAddress());
        etRemark.setText(deedCompanyLand.getRemark());
        etLandLandUse.setText(deedCompanyLand.getLandUse());
        landTypeId = deedCompanyLand.getLandNatureTypeId();
        spinnerLandType.setSelectItem(landTypeId);
        boolean allowEdit = deedCompanyLand.isAllowEdit();
        setEditable(allowEdit);
        rvPhotoPreview.setData(deedCompanyLand.getFiles(), getFileConfig(), allowEdit);
    }

    @Override
    public void onAddDeedCompanyLandSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.COMPANY_DEED_LAND, Status.BuildingType
                .COMPANY));
    }

    @Override
    public void onModifyDeedCompanyLandSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.COMPANY_DEED_LAND, Status.BuildingType
                .COMPANY));
    }

}
