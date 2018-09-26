package com.jdp.hls.page.deed.company.land;

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
import com.jdp.hls.model.entiy.DeedCompanyLand;
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
 * Description:土地证-企业
 * Create Time:2018/9/10 0010 上午 10:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyLandActivity extends BaseTitleActivity implements DeedCompanyLandContract.View {
    @BindView(R.id.spinner_landType)
    KSpinner spinnerLandType;
    @BindView(R.id.spinner_landUse)
    KSpinner spinnerLandUse;
    @BindView(R.id.et_land_certNum)
    EnableEditText etLandCertNum;
    @BindView(R.id.et_land_area)
    EnableEditText etLandArea;
    @BindView(R.id.et_land_mu)
    EnableEditText etLandMu;
    @BindView(R.id.et_land_address)
    EnableEditText etLandAddress;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;
    private boolean isAdd;
    private String enterpriseId;
    private List<TDict> landUseList;
    private List<TDict> landTypeList;
    private int landUseId;
    private int landTypeId;
    private boolean allowEdit;
    @Inject
    DeedCompanyLandPresenter deedCompanyLandPresenter;

    @Override
    public void initVariable() {
        isAdd = getIntent().getBooleanExtra("isAdd", false);
        enterpriseId = getIntent().getStringExtra("enterpriseId");
        landUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_USE);
        landTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_TYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_company_land;
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
        deedCompanyLandPresenter.attachView(this);
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
            deedCompanyLandPresenter.getDeedCompanyLand(enterpriseId);
        }
    }
    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedCompanyLandPresenter.modifyDeedCompanyLand(requestBody);
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            RequestBody requestBody = getRequestBody();
            deedCompanyLandPresenter.addDeedCompanyLand(requestBody);
        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        String certNum = etLandCertNum.getText().toString().trim();
        String area = etLandArea.getText().toString().trim();
        String mu = etLandMu.getText().toString().trim();
        String address = etLandAddress.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", enterpriseId)
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("LandNatureTypeId", String.valueOf(landTypeId))
                .addFormDataPart("LandUseTypeId", String.valueOf(landUseId))
                .addFormDataPart("Area", area)
                .addFormDataPart("Mu", mu)
                .addFormDataPart("Address", address).build();
    }

    private void setEditable(boolean allowEdit) {
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
        Intent intent = new Intent(context, DeedCompanyLandActivity.class);
        intent.putExtra("enterpriseId", enterpriseId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

    @Override
    public void onGetDeedCompanyLandSuccess(DeedCompanyLand deedCompanyLand) {
        etLandCertNum.setText(deedCompanyLand.getCertNum());
        etLandArea.setText(String.valueOf(deedCompanyLand.getArea()));
        etLandMu.setText(String.valueOf(deedCompanyLand.getMu()));
        etLandAddress.setText(deedCompanyLand.getAddress());

        landUseId = deedCompanyLand.getLandUseTypeId();
        landTypeId = deedCompanyLand.getLandNatureTypeId();
        spinnerLandUse.setSelectItem(landUseId);
        spinnerLandType.setSelectItem(landTypeId);
        List<ImgInfo> photoFiles = deedCompanyLand.getFiles();
        if (photoFiles != null) {
            rvPhotoPreview.setData(photoFiles);
        }
        allowEdit = deedCompanyLand.isAllowEdit();
        setEditable(allowEdit);
    }

    @Override
    public void onAddDeedCompanyLandSuccess() {
        ToastUtil.showText("添加成功");

    }

    @Override
    public void onModifyDeedCompanyLandSuccess() {
        ToastUtil.showText("修改成功");
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }
}
