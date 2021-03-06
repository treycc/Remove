package com.jdp.hls.page.deed.personal.property;

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
import com.jdp.hls.model.entiy.DeedPersonalProperty;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.LogUtil;
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
 * Description:产权证-住宅
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalPropertyActivity extends BaseDeedActivity implements DeedPersonalPropertyContract.View {

    @BindView(R.id.et_property_num)
    EnableEditText etPropertyNum;
    @BindView(R.id.et_property_totalArea)
    EnableEditText etPropertyTotalArea;
    @BindView(R.id.et_property_shareArea)
    EnableEditText etPropertyShareArea;
    @BindView(R.id.spinner_property_structure)
    KSpinner spinnerPropertyStructure;
    @BindView(R.id.spinner_property_use)
    KSpinner spinnerPropertyUse;
    @BindView(R.id.et_property_address)
    EnableEditText etPropertyAddress;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.et_property_buildOccupyArea)
    EnableEditText etPropertyBuildOccupyArea;
    private List<TDict> propertyUseList;
    private List<TDict> propertyStructureList;
    private int propertyUse;
    private int propertyStructure;
    @Inject
    DeedPersonalPropertyPresenter deedPersonalPropertyPresenter;
    private boolean allowEdit;
    private String certNum;
    private String totalArea;
    private String shareArea;
    private String address;
    private String remark;
    private String buildOccupyArea;


    @Override
    public void initVariable() {
        super.initVariable();
        propertyUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_USE);
        propertyStructureList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_STRUCTURE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_personal_property;
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
        deedPersonalPropertyPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
        spinnerPropertyUse.setDicts(propertyUseList, typeId -> {
            propertyUse = typeId;
        });
        propertyUse = spinnerPropertyUse.getDefaultTypeId();
        spinnerPropertyStructure.setDicts(propertyStructureList, typeId -> {
            propertyStructure = typeId;
        });
        propertyStructure = spinnerPropertyStructure.getDefaultTypeId();
    }

    @Override
    public void initNet() {
        //1.增加 不做任何操作，点保存增加
        //2.修改 先获取接口，然后可编辑，点保存修改
        //3.查看 先获取接口，然后不可编辑，不出现按钮
        if (mIsAdd) {
            LogUtil.e(TAG, "add:" + mIsAdd);
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedPersonalPropertyPresenter.getDeedPersonalProperty(mBuildingId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedPersonalPropertyPresenter.modifyDeedPersonalProperty(getRequestBody());
            }
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedPersonalPropertyPresenter.addDeedPersonalProperty(getRequestBody());
            }
        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", mBuildingId)
                .addFormDataPart("PropertyUseTypeId", String.valueOf(propertyUse))
                .addFormDataPart("StructureTypeId", String.valueOf(propertyStructure))
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("TotalArea", totalArea)
                .addFormDataPart("ShareArea", shareArea)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("buildOccupyArea", buildOccupyArea)
                .addFormDataPart("Address", address);
        return bodyBuilder.build();
    }

    public boolean checkDataVaildable() {
        certNum = etPropertyNum.getText().toString().trim();
        totalArea = etPropertyTotalArea.getText().toString().trim();
        shareArea = etPropertyShareArea.getText().toString().trim();
        address = etPropertyAddress.getText().toString().trim();
        remark = etRemark.getText().toString().trim();
        buildOccupyArea = etPropertyBuildOccupyArea.getText().toString().trim();
        return CheckUtil.checkEmpty(certNum, "请输入证号") && CheckUtil.checkEmpty(address, "请输入地址");
    }


    public static void goActivity(Context context, String houdeId, boolean isAdd) {
        Intent intent = new Intent(context, DeedPersonalPropertyActivity.class);
        intent.putExtra("houdeId", houdeId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

    @Override
    public void onGetDeedPersonalPropertySuccess(DeedPersonalProperty deedPersonalProperty) {
        etPropertyNum.setText(deedPersonalProperty.getCertNum());
        etPropertyTotalArea.setText(String.valueOf(deedPersonalProperty.getTotalArea()));
        etPropertyShareArea.setText(String.valueOf(deedPersonalProperty.getShareArea()));
        etPropertyAddress.setText(deedPersonalProperty.getAddress());
        etRemark.setText(deedPersonalProperty.getRemark());
        etPropertyBuildOccupyArea.setText(deedPersonalProperty.getBuildOccupyArea());
        propertyUse = deedPersonalProperty.getPropertyUseTypeId();
        propertyStructure = deedPersonalProperty.getStructureTypeId();
        spinnerPropertyUse.setSelectItem(propertyUse);
        spinnerPropertyStructure.setSelectItem(propertyStructure);
        allowEdit = deedPersonalProperty.isAllowEdit();
        setEditable(allowEdit);
        rvPhotoPreview.setData(deedPersonalProperty.getFiles(), getFileConfig(), allowEdit);
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        }
        etPropertyNum.setEnabled(allowEdit);
        etPropertyTotalArea.setEnabled(allowEdit);
        etPropertyShareArea.setEnabled(allowEdit);
        etPropertyAddress.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
        etPropertyBuildOccupyArea.setEnabled(allowEdit);
        spinnerPropertyUse.enable(allowEdit);
        spinnerPropertyStructure.enable(allowEdit);
    }

    @Override
    public void onAddDeedPersonalPropertySuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.PERSONAL_DEED_PROPERTY, Status
                .BuildingType.PERSONAL));
    }

    @Override
    public void onModifyDeedPersonalPropertySuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.PERSONAL_DEED_PROPERTY, Status
                .BuildingType.PERSONAL));
    }
}
