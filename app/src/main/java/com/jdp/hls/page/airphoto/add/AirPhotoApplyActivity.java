package com.jdp.hls.page.airphoto.add;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AirPhotoBuilding;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;


/**
 * Description:初审
 * Create Time:2018/9/13 0013 下午 4:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoApplyActivity extends BaseTitleActivity implements AirPhotoApplyContract.View {

    @BindView(R.id.tv_airphoto_cusCode)
    StringTextView tvAirphotoCusCode;
    @BindView(R.id.tv_airphoto_realNameTip)
    TextView tvAirphotoRealNameTip;
    @BindView(R.id.tv_airphoto_realName)
    StringTextView tvAirphotoRealName;
    @BindView(R.id.tv_airphoto_address)
    StringTextView tvAirphotoAddress;
    @BindView(R.id.spinner_propertyUse)
    KSpinner spinnerPropertyUse;
    @BindView(R.id.spinner_structureType)
    KSpinner spinnerStructureType;
    @BindView(R.id.et_airphoto_layer)
    EnableEditText etAirphotoLayer;
    @BindView(R.id.rl_unrecordBuilding)
    RelativeLayout rlFamilyRelation;
    @BindView(R.id.et_airphoto_reason)
    EnableEditText etAirphotoReason;
    @BindView(R.id.et_airphoto_remark)
    EnableEditText etAirphotoRemark;
    @BindView(R.id.rv_photo_apply)
    PreviewRecyclerView rvPhotoApply;
    @BindView(R.id.ll_photo_apply)
    LinearLayout llPhotoApply;
    private List<TDict> propertyUseList;
    private List<TDict> propertyStructureList;
    @Inject
    AirPhotoApplyPresenter airPhotoApplyPresenter;
    private int propertyUse;
    private int propertyStructure;
    private AirPhotoBuilding airPhotoBuilding;

    @Override
    public void initVariable() {
        airPhotoBuilding = (AirPhotoBuilding) getIntent().getSerializableExtra(Constants.Extra.AIRPHOTO_BUILDING);
        propertyUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_USE);
        propertyStructureList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_STRUCTURE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_airphoto_apply;
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
        return "发起初审";
    }

    @Override
    protected void initView() {
        airPhotoApplyPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        spinnerPropertyUse.setDicts(propertyUseList, typeId -> {
            propertyUse = typeId;
        });
        propertyUse = spinnerPropertyUse.getDefaultTypeId();
        spinnerStructureType.setDicts(propertyStructureList, typeId -> {
            propertyStructure = typeId;
        });
        propertyStructure = spinnerStructureType.getDefaultTypeId();
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                applyAirPhoto();
            }
        });
        tvAirphotoRealNameTip.setText(airPhotoBuilding.getBuilldingType() == Status.BuildingType.PERSONAL ? "户主姓名" :
                "负责人");
        tvAirphotoRealName.setString(airPhotoBuilding.getRealName());
        tvAirphotoAddress.setString(airPhotoBuilding.getAddress());
    }

    private void applyAirPhoto() {
        String remark = etAirphotoRemark.getText().toString().trim();
        String layer = etAirphotoLayer.getText().toString().trim();
        String reason = etAirphotoReason.getText().toString().trim();
        airPhotoApplyPresenter.applyAirPhoto(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("BuildingId", airPhotoBuilding.getBuildingId())
                .addFormDataPart("BuildingType", String.valueOf(airPhotoBuilding.getBuilldingType()))
                .addFormDataPart("BuildingStructureType", String.valueOf(propertyStructure))
                .addFormDataPart("PropertyUseType", String.valueOf(propertyUse))
                .addFormDataPart("Layer", layer)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("Reason", reason)
                .addFormDataPart("JsonItems", "")
                .build());

    }

    @Override
    protected void initNet() {
    }


    public static void goActivity(Context context, AirPhotoBuilding airPhotoBuilding) {
        Intent intent = new Intent(context, AirPhotoApplyActivity.class);
        intent.putExtra(Constants.Extra.AIRPHOTO_BUILDING, airPhotoBuilding);
        context.startActivity(intent);
    }

    @Override
    public void onApplyAirPhotoSuccess() {
        showSuccessAndFinish();
    }

}
