package com.jdp.hls.page.airphoto.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AirPhotoItem;
import com.jdp.hls.model.entiy.ModifyAirPhotoEvent;
import com.jdp.hls.page.airphoto.unrecordbuilding.UnrecordBuildingListActivity;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;


/**
 * Description:航拍复查详情
 * Create Time:2018/9/13 0013 下午 4:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class AirPhotoDetailActivity extends BaseTitleActivity implements AirPhotoDetailContract.View {
    @BindView(R.id.tv_airphoto_cusCode)
    StringTextView tvAirphotoCusCode;
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
    RelativeLayout rlUnrecordBuilding;
    @BindView(R.id.et_airphoto_reason)
    EnableEditText etAirphotoReason;
    @BindView(R.id.et_airphoto_remark)
    EnableEditText etAirphotoRemark;
    @BindView(R.id.rv_photo_apply)
    PreviewRecyclerView rvPhotoApply;
    @BindView(R.id.ll_photo_apply)
    LinearLayout llPhotoApply;
    @BindView(R.id.rv_photo_age)
    PreviewRecyclerView rvPhotoAge;
    @BindView(R.id.ll_photo_age)
    LinearLayout llPhotoAge;
    private List<TDict> propertyUseList;
    private List<TDict> propertyStructureList;
    @Inject
    AirPhotoDetailPresenter airPhotoDetailPresenter;
    private String airCheckProId;
    private int propertyUse;
    private int propertyStructure;
    private AirPhotoItem airPhotoItem;
    private String deleteIds = "";
    private String editedBase64 = "";

    @OnClick({R.id.rl_unrecordBuilding})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_unrecordBuilding:
                UnrecordBuildingListActivity.goActivity(this, airPhotoItem.getItems());
                break;
        }
    }


    @Override
    public void initVariable() {
        airCheckProId = getIntent().getStringExtra(Constants.Extra.AIRCHECKPRO_ID);
        propertyUseList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_USE);
        propertyStructureList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PROPERTY_STRUCTURE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_airphoto_detail;
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
        return "航拍复查";
    }

    @Override
    protected void initView() {
        airPhotoDetailPresenter.attachView(this);
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
                saveAirPhoto();
            }
        });
    }

    private void saveAirPhoto() {
        String remark = etAirphotoRemark.getText().toString().trim();
        String layer = etAirphotoLayer.getText().toString().trim();
        String reason = etAirphotoReason.getText().toString().trim();
        airPhotoDetailPresenter.modifyAirPhotoDetail(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("AirCheckProId", airCheckProId)
                .addFormDataPart("BuildingId", airPhotoItem.getBuildingId())
                .addFormDataPart("BuilldingType", String.valueOf(airPhotoItem.getBuilldingType()))
                .addFormDataPart("BuildingStructureType", String.valueOf(propertyStructure))
                .addFormDataPart("PropertyUseType", String.valueOf(propertyUse))
                .addFormDataPart("Layer", layer)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("Reason", reason)
                .addFormDataPart("JsonItems", editedBase64)
                .addFormDataPart("DeleteItemIDs", deleteIds)
                .build());

    }

    @Override
    protected void initNet() {
        airPhotoDetailPresenter.getAirPhotoDetail(airCheckProId);
    }

    @Override
    public void onGetAirPhotoDetailSuccess(AirPhotoItem airPhotoItem) {
        this.airPhotoItem = airPhotoItem;
        spinnerPropertyUse.setSelectItem(airPhotoItem.getPropertyUseType());
        spinnerStructureType.setSelectItem(airPhotoItem.getBuildingStructureType());
        tvAirphotoCusCode.setString(airPhotoItem.getCusCode());
        tvAirphotoRealName.setString(airPhotoItem.getRealName());
        tvAirphotoAddress.setString(airPhotoItem.getAddress());
        etAirphotoLayer.setString(airPhotoItem.getLayer());
        etAirphotoReason.setString(airPhotoItem.getReason());
        etAirphotoRemark.setString(airPhotoItem.getRemark());
        rvPhotoApply.initPhotos(airPhotoItem.getFiles());
    }

    @Override
    public void onModifyAirPhotoDetailSuccess(AirPhotoItem airPhotoItem) {
        EventBus.getDefault().post(new ModifyAirPhotoEvent(airPhotoItem));
        showSuccessAndFinish();
    }

    public static void goActivity(Context context, String airCheckProId) {
        Intent intent = new Intent(context, AirPhotoDetailActivity.class);
        intent.putExtra(Constants.Extra.AIRCHECKPRO_ID, airCheckProId);
        context.startActivity(intent);
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.UNRECORDBUILDING:
                    deleteIds = data.getStringExtra(Constants.Extra.DELETEIDS);
                    editedBase64 = data.getStringExtra(Constants.Extra.EDITEDBASE64);
                    break;
                default:
                    break;
            }
        }
    }
}
