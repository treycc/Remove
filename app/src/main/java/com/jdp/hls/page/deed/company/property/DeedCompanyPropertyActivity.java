package com.jdp.hls.page.deed.company.property;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedCompanyProperty;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;

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
public class DeedCompanyPropertyActivity extends BaseTitleActivity implements DeedCompanyPropertyContract.View {
    @BindView(R.id.spinner_property_use)
    KSpinner spinnerPropertyUse;
    @BindView(R.id.spinner_property_structure)
    KSpinner spinnerPropertyStructure;
    @BindView(R.id.et_property_area)
    EnableEditText etPropertyArea;
    @BindView(R.id.et_property_address)
    EnableEditText etPropertyAddress;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;
    @BindView(R.id.et_property_certNum)
    EnableEditText etPropertyCertNum;
    private boolean isAdd;
    private String enterpriseId;
    private List<TDict> propertyUseList;
    private List<TDict> propertyStructureList;
    private int propertyUse;
    private int propertyStructure;
    @Inject
    DeedCompanyPropertyPresenter deedCompanyPropertyPresenter;
    private boolean allowEdit;

    @Override
    public void initVariable() {
        isAdd = getIntent().getBooleanExtra("isAdd", false);
        enterpriseId = getIntent().getStringExtra("enterpriseId");

        propertyUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_USE);
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
        rvPhotoPreview.create();
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
    protected void initNet() {
        if (isAdd) {
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedCompanyPropertyPresenter.getDeedCompanyProperty(enterpriseId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedCompanyPropertyPresenter.modifyDeedCompanyProperty(requestBody);
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedCompanyPropertyPresenter.addDeedCompanyProperty(requestBody);
        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        String certNum = etPropertyCertNum.getText().toString().trim();
        String area = etPropertyArea.getText().toString().trim();
        String address = etPropertyAddress.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", enterpriseId)
                .addFormDataPart("PropertyUseTypeId", String.valueOf(propertyUse))
                .addFormDataPart("StructureTypeId", String.valueOf(propertyStructure))
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("Address", address)
                .addFormDataPart("Area", area).build();
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
        propertyUse = deedCompanyProperty.getPropertyUseTypeId();
        propertyStructure = deedCompanyProperty.getStructureTypeId();
        spinnerPropertyUse.setSelectItem(propertyUse);
        spinnerPropertyStructure.setSelectItem(propertyStructure);
        List<ImgInfo> imgInfos = deedCompanyProperty.getFiles();
        if (imgInfos != null && imgInfos.size() > 0) {
            rvPhotoPreview.setData(imgInfos);
        }
        allowEdit = deedCompanyProperty.isAllowEdit();
        setEditable();
    }

    private void setEditable() {
        if (allowEdit) {
            setRightClick("保存", editListener);
        } else {
            //全部禁用
        }
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onAddDeedCompanyPropertySuccess() {
        ToastUtil.showText("添加成功");
        finish();
    }

    @Override
    public void onModifyDeedCompanyPropertySuccess() {
        ToastUtil.showText("修改成功");
        finish();

    }


}
