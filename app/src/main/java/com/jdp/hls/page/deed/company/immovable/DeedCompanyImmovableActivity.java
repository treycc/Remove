package com.jdp.hls.page.deed.company.immovable;

import android.content.Context;
import android.content.Intent;
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
import com.jdp.hls.model.entiy.DeedCompanyImmovable;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:不动产证-企业
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyImmovableActivity extends BaseTitleActivity implements DeedCompanyImmovableContract.View {
    @BindView(R.id.et_immovable_certNum)
    EnableEditText etImmovableCertNum;
    @BindView(R.id.spinner_landUse)
    KSpinner spinnerLandUse;
    @BindView(R.id.spinner_landType)
    KSpinner spinnerLandType;
    @BindView(R.id.spinner_property_use)
    KSpinner spinnerPropertyUse;
    @BindView(R.id.spinner_property_structure)
    KSpinner spinnerPropertyStructure;
    @BindView(R.id.et_immovable_address)
    EnableEditText etImmovableAddress;
    @BindView(R.id.et_immovable_landArea)
    EnableEditText etImmovableLandArea;
    @BindView(R.id.et_immovable_propertyArea)
    EnableEditText etImmovablePropertyArea;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;
    private boolean isAdd;
    private String enterpriseId;
    private List<TDict> landUseList;
    private List<TDict> landTypeList;
    private List<TDict> propertyUseList;
    private List<TDict> propertyStructureList;
    private int propertyUse;
    private int propertyStructure;
    private int landUseId;
    private int landTypeId;
    private boolean allowEdit;

    @Inject
    DeedCompanyImmovablePresenter deedCompanyImmovablePresenter;

    @Override
    public void initVariable() {
        isAdd = getIntent().getBooleanExtra("isAdd", false);
        enterpriseId = getIntent().getStringExtra("enterpriseId");
        landUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_USE);
        landTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_TYPE);
        propertyUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_USE);
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
        rvPhotoPreview.create();
        spinnerLandUse.setDicts(landUseList, typeId -> {
            landUseId = typeId;
        });
        landUseId = spinnerLandUse.getDefaultTypeId();
        spinnerLandType.setDicts(landTypeList, typeId -> {
            landTypeId = typeId;
        });
        landTypeId = spinnerLandType.getDefaultTypeId();
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
            deedCompanyImmovablePresenter.getDeedCompanyImmovable(enterpriseId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedCompanyImmovablePresenter.modifyDeedCompanyImmovable(requestBody);
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedCompanyImmovablePresenter.addDeedCompanyImmovable(requestBody);
        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        String certNum = etImmovableCertNum.getText().toString().trim();
        String landArea = etImmovableLandArea.getText().toString().trim();
        String propertyArea = etImmovablePropertyArea.getText().toString().trim();
        String address = etImmovableAddress.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", enterpriseId)
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("LandNatureTypeId", String.valueOf(landTypeId))
                .addFormDataPart("LandUseTypeId", String.valueOf(landUseId))
                .addFormDataPart("PropertyUseTypeId", String.valueOf(propertyUse))
                .addFormDataPart("StructureTypeId", String.valueOf(propertyStructure))
                .addFormDataPart("LandArea", landArea)
                .addFormDataPart("PropertyArea", propertyArea)
                .addFormDataPart("Address", address).build();
    }

    private void setEditable() {
        if (allowEdit) {
            setRightClick("保存", editListener);
        } else {
            //全部禁用
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public static void goActivity(Context context, String enterpriseId, boolean isAdd) {
        Intent intent = new Intent(context, DeedCompanyImmovableActivity.class);
        intent.putExtra("enterpriseId", enterpriseId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void onGetDeedCompanyImmovableSuccess(DeedCompanyImmovable deedCompanyImmovable) {
        etImmovableCertNum.setText(deedCompanyImmovable.getCertNum());
        etImmovableAddress.setText(deedCompanyImmovable.getAddress());
        etImmovableLandArea.setText(String.valueOf(deedCompanyImmovable.getLandArea()));
        etImmovablePropertyArea.setText(String.valueOf(deedCompanyImmovable.getPropertyArea()));

        landUseId = deedCompanyImmovable.getLandUseTypeId();
        landTypeId = deedCompanyImmovable.getLandNatureTypeId();
        spinnerLandUse.setSelectItem(landUseId);
        spinnerLandType.setSelectItem(landTypeId);
        propertyUse = deedCompanyImmovable.getPropertyUseTypeId();
        propertyStructure = deedCompanyImmovable.getStructureTypeId();
        spinnerPropertyUse.setSelectItem(propertyUse);
        spinnerPropertyStructure.setSelectItem(propertyStructure);
        List<ImgInfo> photoFiles = deedCompanyImmovable.getFiles();
        if (photoFiles != null) {
            rvPhotoPreview.setData(photoFiles);
        }
        allowEdit = deedCompanyImmovable.isAllowEdit();
        setEditable();
    }

    @Override
    public void onAddDeedCompanyImmovableSuccess() {
        ToastUtil.showText("添加成功");
        finish();

    }

    @Override
    public void onModifyDeedCompanyImmovableSuccess() {
        ToastUtil.showText("修改成功");
        finish();
    }
}
