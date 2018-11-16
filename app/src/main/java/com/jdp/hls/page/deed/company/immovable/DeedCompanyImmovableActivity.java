package com.jdp.hls.page.deed.company.immovable;

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
import com.jdp.hls.model.entiy.DeedCompanyImmovable;
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
 * Description:不动产证-企业
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyImmovableActivity extends BaseDeedActivity implements DeedCompanyImmovableContract.View {
    @BindView(R.id.et_immovable_certNum)
    EnableEditText etImmovableCertNum;
    @BindView(R.id.spinner_landType)
    KSpinner spinnerLandType;
    @BindView(R.id.spinner_property_structure)
    KSpinner spinnerPropertyStructure;
    @BindView(R.id.et_immovable_address)
    EnableEditText etImmovableAddress;
    @BindView(R.id.et_immovable_landArea)
    EnableEditText etImmovableLandArea;
    @BindView(R.id.et_immovable_propertyArea)
    EnableEditText etImmovablePropertyArea;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.et_immovable_landUse)
    EnableEditText etImmovableLandUse;
    @BindView(R.id.et_immovable_propertyUse)
    EnableEditText etImmovablePropertyUse;
    private List<TDict> landTypeList;
    private List<TDict> propertyStructureList;
    private int propertyStructure;
    private int landTypeId;

    @Inject
    DeedCompanyImmovablePresenter deedCompanyImmovablePresenter;
    private String certNum;
    private String landArea;
    private String propertyArea;
    private String address;
    private String remark;
    private String landUse;
    private String propertyUse;

    @Override
    public void initVariable() {
        super.initVariable();
        landTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_TYPE);
        propertyStructureList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_STRUCTURE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_company_immovable;
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
        return "不动产证";
    }

    @Override
    protected void initView() {
        deedCompanyImmovablePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
        spinnerLandType.setDicts(landTypeList, typeId -> {
            landTypeId = typeId;
        });
        landTypeId = spinnerLandType.getDefaultTypeId();
        spinnerPropertyStructure.setDicts(propertyStructureList, typeId -> {
            propertyStructure = typeId;
        });
        propertyStructure = spinnerPropertyStructure.getDefaultTypeId();
    }

    @Override
    public void initNet() {
        if (mIsAdd) {
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedCompanyImmovablePresenter.getDeedCompanyImmovable(mBuildingId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyImmovablePresenter.modifyDeedCompanyImmovable(getRequestBody());
            }

        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyImmovablePresenter.addDeedCompanyImmovable(getRequestBody());
            }
        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("LandNatureTypeId", String.valueOf(landTypeId))
                .addFormDataPart("LandUse", landUse)
                .addFormDataPart("PropertyUse", propertyUse)
                .addFormDataPart("StructureTypeId", String.valueOf(propertyStructure))
                .addFormDataPart("LandArea", landArea)
                .addFormDataPart("PropertyArea", propertyArea)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("Address", address).build();
    }

    public boolean checkDataVaildable() {
        certNum = etImmovableCertNum.getText().toString().trim();
        landArea = etImmovableLandArea.getText().toString().trim();
        propertyArea = etImmovablePropertyArea.getText().toString().trim();
        address = etImmovableAddress.getText().toString().trim();
        remark = etRemark.getText().toString().trim();
        landUse = etImmovableLandUse.getText().toString().trim();
        propertyUse = etImmovablePropertyUse.getText().toString().trim();
        return CheckUtil.checkEmpty(certNum, "请输入证件号") && CheckUtil.checkEmpty(address, "请输入地址");
    }


    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        }
        etImmovableCertNum.setEnabled(allowEdit);
        etImmovableAddress.setEnabled(allowEdit);
        etImmovableLandArea.setEnabled(allowEdit);
        etImmovablePropertyArea.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
        etImmovableLandUse.setEnabled(allowEdit);
        etImmovablePropertyUse.setEnabled(allowEdit);
        spinnerLandType.enable(allowEdit);
        spinnerPropertyStructure.enable(allowEdit);
    }

    public static void goActivity(Context context, String enterpriseId, boolean isAdd) {
        Intent intent = new Intent(context, DeedCompanyImmovableActivity.class);
        intent.putExtra("enterpriseId", enterpriseId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }


    @Override
    public void onGetDeedCompanyImmovableSuccess(DeedCompanyImmovable deedCompanyImmovable) {
        etImmovableCertNum.setText(deedCompanyImmovable.getCertNum());
        etImmovableAddress.setText(deedCompanyImmovable.getAddress());
        etImmovableLandArea.setText(String.valueOf(deedCompanyImmovable.getLandArea()));
        etImmovablePropertyArea.setText(String.valueOf(deedCompanyImmovable.getPropertyArea()));
        etRemark.setText(deedCompanyImmovable.getRemark());
        etImmovableLandUse.setText(deedCompanyImmovable.getLandUse());
        etImmovablePropertyUse.setText(deedCompanyImmovable.getPropertyUse());

        landTypeId = deedCompanyImmovable.getLandNatureTypeId();
        spinnerLandType.setSelectItem(landTypeId);
        propertyStructure = deedCompanyImmovable.getStructureTypeId();
        spinnerPropertyStructure.setSelectItem(propertyStructure);
        boolean allowEdit = deedCompanyImmovable.isAllowEdit();
        setEditable(allowEdit);
        rvPhotoPreview.setData(deedCompanyImmovable.getFiles(), getFileConfig(), allowEdit);
    }

    @Override
    public void onAddDeedCompanyImmovableSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.COMPANY_DEED_IMMOVABLE, Status
                .BuildingType.COMPANY));
    }

    @Override
    public void onModifyDeedCompanyImmovableSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.COMPANY_DEED_IMMOVABLE, Status
                .BuildingType.COMPANY));
    }

}
