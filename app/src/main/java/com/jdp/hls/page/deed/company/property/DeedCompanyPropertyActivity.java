package com.jdp.hls.page.deed.company.property;

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
import com.jdp.hls.model.entiy.DeedCompanyProperty;
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
 * Description:产权证-企业
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyPropertyActivity extends BaseDeedActivity implements DeedCompanyPropertyContract.View {
    @BindView(R.id.spinner_property_structure)
    KSpinner spinnerPropertyStructure;
    @BindView(R.id.et_property_area)
    EnableEditText etPropertyArea;
    @BindView(R.id.et_property_address)
    EnableEditText etPropertyAddress;
    @BindView(R.id.et_property_certNum)
    EnableEditText etPropertyCertNum;
    @BindView(R.id.et_property_propertyUse)
    EnableEditText etPropertyPropertyUse;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private List<TDict> propertyStructureList;
    private int propertyStructure;
    @Inject
    DeedCompanyPropertyPresenter deedCompanyPropertyPresenter;
    private String certNum;
    private String area;
    private String address;
    private String propertyUse;
    private String remark;

    @Override
    public void initVariable() {
        super.initVariable();
        propertyStructureList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_STRUCTURE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_company_property;
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
        return "产权证";
    }

    @Override
    protected void initView() {
        deedCompanyPropertyPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
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
            deedCompanyPropertyPresenter.getDeedCompanyProperty(mBuildingId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyPropertyPresenter.modifyDeedCompanyProperty(getRequestBody());
            }

        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyPropertyPresenter.addDeedCompanyProperty(getRequestBody());
            }

        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("PropertyUseTypeId", String.valueOf(propertyUse))
                .addFormDataPart("StructureTypeId", String.valueOf(propertyStructure))
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("Address", address)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("PropertyUse", propertyUse)
                .addFormDataPart("Area", area).build();
    }

    public boolean checkDataVaildable() {
        certNum = etPropertyCertNum.getText().toString().trim();
        area = etPropertyArea.getText().toString().trim();
        address = etPropertyAddress.getText().toString().trim();
        remark = etRemark.getText().toString().trim();
        propertyUse = etPropertyPropertyUse.getText().toString().trim();
        return CheckUtil.checkEmpty(certNum, "请输入证件号") && CheckUtil.checkEmpty(address, "请输入地址");
    }


    public static void goActivity(Context context, String enterpriseId, boolean isAdd) {
        Intent intent = new Intent(context, DeedCompanyPropertyActivity.class);
        intent.putExtra("enterpriseId", enterpriseId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

    @Override
    public void onGetDeedCompanyPropertySuccess(DeedCompanyProperty deedCompanyProperty) {
        etPropertyCertNum.setText(deedCompanyProperty.getCertNum());
        etPropertyArea.setText(String.valueOf(deedCompanyProperty.getArea()));
        etPropertyAddress.setText(String.valueOf(deedCompanyProperty.getAddress()));
        etRemark.setText(deedCompanyProperty.getRemark());
        etPropertyPropertyUse.setText(deedCompanyProperty.getPropertyUse());
        propertyStructure = deedCompanyProperty.getStructureTypeId();
        spinnerPropertyStructure.setSelectItem(propertyStructure);
        boolean allowEdit = deedCompanyProperty.isAllowEdit();
        setEditable(allowEdit);
        rvPhotoPreview.setData(deedCompanyProperty.getFiles(), getFileConfig(), allowEdit);
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        }
        etPropertyCertNum.setEnabled(allowEdit);
        etPropertyArea.setEnabled(allowEdit);
        etPropertyAddress.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
        etPropertyPropertyUse.setEnabled(allowEdit);
        spinnerPropertyStructure.enable(allowEdit);
    }

    @Override
    public void onAddDeedCompanyPropertySuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.COMPANY_DEED_PROPERTY, Status
                .BuildingType.COMPANY));
    }

    @Override
    public void onModifyDeedCompanyPropertySuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.COMPANY_DEED_PROPERTY, Status
                .BuildingType.COMPANY));

    }

}
