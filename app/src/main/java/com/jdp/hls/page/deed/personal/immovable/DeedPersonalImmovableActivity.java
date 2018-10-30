package com.jdp.hls.page.deed.personal.immovable;

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
import com.jdp.hls.model.entiy.DeedPersonalImmovable;
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
 * Description:不动产证-个人
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalImmovableActivity extends BaseDeedActivity implements DeedPersonalImmovableContract.View {
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
    private boolean allowEdit;
    private String certNum;
    private String address;
    private String houseTotalArea;
    private String houseShareArea;
    private String landTotalArea;
    private String buildOccupyArea;

    @Override
    public void initVariable() {
        super.initVariable();
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
        super.initData();
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
        if (mIsAdd) {
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedPersonalImmovablePresenter.getDeedPersonalImmovable(mBuildingId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedPersonalImmovablePresenter.modifyDeedPersonalImmovable(getRequestBody());
            }

        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedPersonalImmovablePresenter.addDeedPersonalImmovable(getRequestBody());
            }

        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", mBuildingId)
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

    public boolean checkDataVaildable() {
        certNum = etImmovableNum.getText().toString().trim();
        houseTotalArea = etPropertyTotalArea.getText().toString().trim();
        houseShareArea = etPropertyShareArea.getText().toString().trim();
        landTotalArea = etLandCertArea.getText().toString().trim();
        buildOccupyArea = etLandBuildOccupyArea.getText().toString().trim();
        address = etLandAddress.getText().toString().trim();
        return CheckUtil.checkEmpty(certNum,"请输入证件号")&&CheckUtil.checkEmpty(address,"请输入地址");
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
        allowEdit = deedPersonalImmovable.isAllowEdit();
        setEditable(allowEdit);
        rvPhotoPreview.setData(deedPersonalImmovable.getFiles(), getFileConfig(), allowEdit);
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        }
        etImmovableNum.setEnabled(allowEdit);
        etLandCertArea.setEnabled(allowEdit);
        etLandBuildOccupyArea.setEnabled(allowEdit);
        etPropertyTotalArea.setEnabled(allowEdit);
        etPropertyShareArea.setEnabled(allowEdit);
        etLandAddress.setEnabled(allowEdit);
        spinnerLandUse.enable(allowEdit);
        spinnerLandType.enable(allowEdit);
        spinnerPropertyUse.enable(allowEdit);
        spinnerPropertyStructure.enable(allowEdit);
    }

    @Override
    public void onAddDeedPersonalImmovableSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum,Status.FileType.PERSONAL_DEED_IMMOVABLE,Status.BuildingType.PERSONAL));
    }

    @Override
    public void onModifyDeedPersonalImmovableSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum,Status.FileType.PERSONAL_DEED_IMMOVABLE,Status.BuildingType.PERSONAL));
    }
}
