package com.jdp.hls.page.deed.personal.immovable;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedPersonalImmovable;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:不动产证-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalImmovableActivity extends BaseTitleActivity implements DeedPersonalImmovableContract.View {
    @BindView(R.id.et_immovableNum)
    EnableEditText etImmovableNum;
    @BindView(R.id.spinner_property_use)
    KSpinner spinnerPropertyUse;
    @BindView(R.id.spinner_property_structure)
    KSpinner spinnerPropertyStructure;
    @BindView(R.id.et_property_totalArea)
    EnableEditText etPropertyTotalArea;
    @BindView(R.id.et_property_shareArea)
    EnableEditText etPropertyShareArea;
    @BindView(R.id.spinner_landType)
    KSpinner spinnerLandType;
    @BindView(R.id.spinner_landUse)
    KSpinner spinnerLandUse;
    @BindView(R.id.et_land_certArea)
    EnableEditText etLandCertArea;
    @BindView(R.id.et_land_buildOccupyArea)
    EnableEditText etLandBuildOccupyArea;
    @BindView(R.id.et_land_address)
    EnableEditText etLandAddress;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @Inject
    DeedPersonalImmovablePresenter deedPersonalImmovablePresenter;
    private List<TDict> landUseList;
    private List<TDict> landTypeList;
    private List<TDict> propertyUseList;
    private List<TDict> propertyStructureList;
    private int propertyUse;
    private int propertyStructure;
    private int landUseId;
    private int landTypeId;
    private boolean isAdd;
    private String houdeId;
    private boolean allowEdit;

    @Override
    public void initVariable() {
        isAdd = getIntent().getBooleanExtra("isAdd", false);
        houdeId = getIntent().getStringExtra("houdeId");
        landUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_USE);
        landTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_TYPE);
        propertyUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_USE);
        propertyStructureList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_STRUCTURE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_personal_immovable;
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
        deedPersonalImmovablePresenter.attachView(this);
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
        propertyUse=spinnerPropertyUse.getDefaultTypeId();
        spinnerPropertyStructure.setDicts(propertyStructureList, typeId -> {
            propertyStructure = typeId;
        });
        propertyStructure=spinnerPropertyStructure.getDefaultTypeId();
    }

    @Override
    protected void initNet() {
        if (isAdd) {
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedPersonalImmovablePresenter.getDeedPersonalImmovable(houdeId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedPersonalImmovablePresenter.modifyDeedPersonalImmovable(requestBody);
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedPersonalImmovablePresenter.addDeedPersonalImmovable(requestBody);
        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        String certNum = etImmovableNum.getText().toString().trim();
        String houseTotalArea = etPropertyTotalArea.getText().toString().trim();
        String houseShareArea = etPropertyShareArea.getText().toString().trim();
        String landTotalArea = etLandCertArea.getText().toString().trim();
        String buildOccupyArea = etLandBuildOccupyArea.getText().toString().trim();
        String address = etLandAddress.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", houdeId)
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("PropertyUseTypeId", String.valueOf(propertyUse))
                .addFormDataPart("StructureTypeId", String.valueOf(propertyStructure))
                .addFormDataPart("LandUseTypeId", String.valueOf(landUseId))
                .addFormDataPart("LandNatureTypeId", String.valueOf(landTypeId))
                .addFormDataPart("HouseTotalArea", houseTotalArea)
                .addFormDataPart("HouseShareArea", houseShareArea)
                .addFormDataPart("LandTotalArea", landTotalArea)
                .addFormDataPart("BuildOccupyArea", buildOccupyArea)
                .addFormDataPart("Address", address).build();
    }

    public static void goActivity(Context context, String houdeId, boolean isAdd) {
        Intent intent = new Intent(context, DeedPersonalImmovableActivity.class);
        intent.putExtra("houdeId", houdeId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }


    @Override
    public void onGetDeedPersonalImmovableSuccess(DeedPersonalImmovable deedPersonalImmovable) {
        etImmovableNum.setText(deedPersonalImmovable.getCertNum());

        etLandCertArea.setText(String.valueOf(deedPersonalImmovable.getLandTotalArea()));
        etLandBuildOccupyArea.setText(String.valueOf(deedPersonalImmovable.getBuildOccupyArea()));

        etPropertyTotalArea.setText(String.valueOf(deedPersonalImmovable.getHouseTotalArea()));
        etPropertyShareArea.setText(String.valueOf(deedPersonalImmovable.getHouseShareArea()));

        etLandAddress.setText(deedPersonalImmovable.getAddress());

        landUseId = deedPersonalImmovable.getLandUseTypeId();
        landTypeId = deedPersonalImmovable.getLandNatureTypeId();
        spinnerLandUse.setSelectItem(landUseId);
        spinnerLandType.setSelectItem(landTypeId);

        propertyUse = deedPersonalImmovable.getPropertyUseTypeId();
        propertyStructure = deedPersonalImmovable.getStructureTypeId();
        spinnerPropertyUse.setSelectItem(propertyUse);
        spinnerPropertyStructure.setSelectItem(propertyStructure);

        List<ImgInfo> photoFiles = deedPersonalImmovable.getFiles();
        if (photoFiles != null) {
            rvPhotoPreview.setData(photoFiles);
        }
        allowEdit = deedPersonalImmovable.isAllowEdit();
        setEditable(allowEdit);
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        } else {
            //全部禁用
        }
    }

    @Override
    public void onAddDeedPersonalImmovableSuccess() {

    }

    @Override
    public void onModifyDeedPersonalImmovableSuccess() {

    }
}
