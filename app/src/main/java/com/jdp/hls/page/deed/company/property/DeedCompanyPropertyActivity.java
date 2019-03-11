package com.jdp.hls.page.deed.company.property;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseDeedMulActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedCompanyProperty;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:产权证-企业
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyPropertyActivity extends BaseDeedMulActivity implements DeedCompanyPropertyContract.View {
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
            setRightClick("保存", saveListener);
        } else {
            deedCompanyPropertyPresenter.getDeedCompanyPropertyDetail(mCertId);
        }
    }

    private NoDoubleClickListener saveListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyPropertyPresenter.saveDeedCompanyProperty(getRequestBody());
            }

        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("Id", String.valueOf(mCertId))
                .addFormDataPart("PropertyUseTypeId", String.valueOf(propertyUse))
                .addFormDataPart("StructureTypeId", String.valueOf(propertyStructure))
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("Address", address)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("PropertyUse", propertyUse)
                .addFormDataPart("Area", area).addFormDataPart("deleteFileIDs", TextUtils.isEmpty
                        (rvAddablePhotoPreview.getDeleteImgIds()) ? "" :
                        rvAddablePhotoPreview.getDeleteImgIds());
        return rvAddablePhotoPreview.getValidData(builder).build();
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
        rvAddablePhotoPreview.setDate(deedCompanyProperty.getFiles(), deedCompanyProperty.isAllowEdit());
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", saveListener);
        }
        etPropertyCertNum.setEnabled(allowEdit);
        etPropertyArea.setEnabled(allowEdit);
        etPropertyAddress.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
        etPropertyPropertyUse.setEnabled(allowEdit);
        spinnerPropertyStructure.enable(allowEdit);
    }

    @Override
    public void onSaveDeedCompanyPropertySuccess(DeedItem deedItem) {
        refreshDeedList(deedItem);
    }

}
