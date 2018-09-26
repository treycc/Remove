package com.jdp.hls.page.deed.personal.land;

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
import com.jdp.hls.model.entiy.DeedPersonalLand;
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
 * Description:土地证-个人
 * Create Time:2018/9/10 0010 上午 10:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalLandActivity extends BaseTitleActivity implements DeedPersonalLandContract.View {
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
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;

    @Inject
    DeedPersonalLandPresenter deedPersonalLandPresenter;
    private boolean isAdd;
    private String houdeId;
    private List<TDict> landUseList;
    private List<TDict> landTypeList;
    private int landUseId;
    private int landTypeId;
    private boolean allowEdit;

    @Override
    public void initVariable() {
        isAdd = getIntent().getBooleanExtra("isAdd", false);
        houdeId = getIntent().getStringExtra("houdeId");
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
        rvPhotoPreview.create();
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
        if (isAdd) {
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedPersonalLandPresenter.getDeedPersonalLand(houdeId);
        }
    }

    @NonNull
    private RequestBody getRequestBody() {
        String certNum = etLandCertNum.getText().toString().trim();
        String certArea = etLandCertArea.getText().toString().trim();
        String buildOccupyArea = etBuildOccupyArea.getText().toString().trim();
        String address = etLandAddress.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", houdeId)
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("LandNatureId", String.valueOf(landTypeId))
                .addFormDataPart("LandUseTypeId", String.valueOf(landUseId))
                .addFormDataPart("TotalArea", certArea)
                .addFormDataPart("BuildOccupyArea", buildOccupyArea)
                .addFormDataPart("Address", address).build();
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        } else {
            //全部禁用
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedPersonalLandPresenter.modifyDeedPersonalLand(requestBody);
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedPersonalLandPresenter.addDeedPersonalLand(requestBody);
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public static void goActivity(Context context, String houdeId, boolean isAdd) {
        Intent intent = new Intent(context, DeedPersonalLandActivity.class);
        intent.putExtra("houdeId", houdeId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

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
        List<ImgInfo> photoFiles = deedPersonalLand.getFiles();
        if (photoFiles != null) {
            rvPhotoPreview.setData(photoFiles);
        }
        allowEdit = deedPersonalLand.isAllowEdit();
        setEditable(allowEdit);
    }


    @Override
    public void onAddDeedPersonalLandSuccess() {
        ToastUtil.showText("增加成功");
        finish();

    }

    @Override
    public void onModifyDeedPersonalLandSuccess() {
        ToastUtil.showText("修改成功");
        finish();
    }
}
