package com.jdp.hls.page.deed.personal.land;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseDeedActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.event.RefreshBusinessListEvent;
import com.jdp.hls.event.RefreshCertNumEvent;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedPersonalLand;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:土地证-个人
 * Create Time:2018/9/10 0010 上午 10:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalLandActivity extends BaseDeedActivity implements DeedPersonalLandContract.View {
    @BindView(R.id.spinner_landType)
    KSpinner spinnerLandType;
    @BindView(R.id.spinner_landUse)
    KSpinner spinnerLandUse;
    @BindView(R.id.et_land_certNum)
    EnableEditText etLandCertNum;
    @BindView(R.id.et_land_certArea)
    EnableEditText etLandCertArea;
    @BindView(R.id.et_land_totalArea)
    EnableEditText etBuildOccupyArea;
    @BindView(R.id.et_land_address)
    EnableEditText etLandAddress;

    @Inject
    DeedPersonalLandPresenter deedPersonalLandPresenter;
    private List<TDict> landUseList;
    private List<TDict> landTypeList;
    private int landUseId;
    private int landTypeId;
    private boolean allowEdit;
    private String certNum;
    private String certArea;
    private String buildOccupyArea;
    private String address;

    @Override
    public void initVariable() {
        super.initVariable();
        landUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_USE);
        landTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_TYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_personal_land;
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
        deedPersonalLandPresenter.attachView(this);
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
    }

    @Override
    protected void initNet() {
        if (mIsAdd) {
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedPersonalLandPresenter.getDeedPersonalLand(mBuildingId);
        }
    }

    @NonNull
    private RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", mBuildingId)
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("LandNatureId", String.valueOf(landTypeId))
                .addFormDataPart("LandUseTypeId", String.valueOf(landUseId))
                .addFormDataPart("TotalArea", certArea)
                .addFormDataPart("BuildOccupyArea", buildOccupyArea)
                .addFormDataPart("Address", address).build();
    }

    public boolean checkDataVaildable() {
        certNum = etLandCertNum.getText().toString().trim();
        certArea = etLandCertArea.getText().toString().trim();
        buildOccupyArea = etBuildOccupyArea.getText().toString().trim();
        address = etLandAddress.getText().toString().trim();
        return CheckUtil.checkEmpty(certNum, "请输入证件号") && CheckUtil.checkEmpty(address, "请输入地址");
    }


    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        }
        etLandCertNum.setEnabled(allowEdit);
        etLandCertArea.setEnabled(allowEdit);
        etBuildOccupyArea.setEnabled(allowEdit);
        etLandAddress.setEnabled(allowEdit);
        spinnerLandUse.enable(allowEdit);
        spinnerLandType.enable(allowEdit);
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedPersonalLandPresenter.modifyDeedPersonalLand(getRequestBody());
            }

        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedPersonalLandPresenter.addDeedPersonalLand(getRequestBody());
            }
        }
    };


    @Override
    public void onGetDeedPersonalLandSuccess(DeedPersonalLand deedPersonalLand) {
        etLandCertNum.setText(deedPersonalLand.getCertNum());
        etLandCertArea.setText(String.valueOf(deedPersonalLand.getTotalArea()));
        etBuildOccupyArea.setText(String.valueOf(deedPersonalLand.getBuildOccupyArea()));
        etLandAddress.setText(deedPersonalLand.getAddress());
        landUseId = deedPersonalLand.getLandUseTypeId();
        landTypeId = deedPersonalLand.getLandNatureId();
        spinnerLandUse.setSelectItem(landUseId);
        spinnerLandType.setSelectItem(landTypeId);
        allowEdit = deedPersonalLand.isAllowEdit();
        setEditable(allowEdit);
        rvPhotoPreview.setData(deedPersonalLand.getFiles(), getFileConfig(), allowEdit);
    }


    @Override
    public void onAddDeedPersonalLandSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum,Status.FileType.PERSONAL_DEED_LAND,Status.BuildingType.PERSONAL));
    }

    @Override
    public void onModifyDeedPersonalLandSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum,Status.FileType.PERSONAL_DEED_LAND,Status.BuildingType.PERSONAL));
    }


}
