package com.jdp.hls.page.airphoto.add;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import com.jdp.hls.event.AddAirPhotoEvent;
import com.jdp.hls.model.entiy.AirPhotoBuilding;
import com.jdp.hls.model.entiy.AirPhotoItem;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.UnRecordBuilding;
import com.jdp.hls.page.airphoto.unrecordbuilding.UnrecordBuildingListActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.AddableRecyclerView;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


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
    @BindView(R.id.rv_photo)
    AddableRecyclerView rvPhoto;
    @BindView(R.id.ll_airphoto_send)
    LinearLayout llAirphotoSend;
    private List<TDict> propertyUseList;
    private List<TDict> propertyStructureList;
    @Inject
    AirPhotoApplyPresenter airPhotoApplyPresenter;
    private int propertyUse;
    private int propertyStructure;
    private AirPhotoBuilding airPhotoBuilding;
    private String editedBase64 = "";
    List<UnRecordBuilding> unRecordBuildingList = new ArrayList<>();
    private boolean iSaveSend;
    private String remark;
    private String layer;
    private String reason;


    @OnClick({R.id.rl_unrecordBuilding, R.id.ll_airphoto_send})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_unrecordBuilding:
                UnrecordBuildingListActivity.goActivity(this, unRecordBuildingList, true);
                break;
            case R.id.ll_airphoto_send:
                iSaveSend = true;
                if (checkDataValidable()) {
                    airPhotoApplyPresenter.applyAirPhoto(getRequestBody().addFormDataPart("IsSend", "true").build());
                }

                break;
        }
    }

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
        tvAirphotoCusCode.setString(airPhotoBuilding.getCusCode());
    }

    private void applyAirPhoto() {
        iSaveSend = false;
        if (checkDataValidable()) {
            airPhotoApplyPresenter.applyAirPhoto(getRequestBody().build());
        }

    }

    private boolean checkDataValidable() {
        remark = etAirphotoRemark.getText().toString().trim();
        layer = etAirphotoLayer.getText().toString().trim();
        reason = etAirphotoReason.getText().toString().trim();
        return CheckUtil.checkEmpty(layer, "请输入层次");
    }

    @NonNull
    private MultipartBody.Builder getRequestBody() {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("BuildingId", airPhotoBuilding.getBuildingId())
                .addFormDataPart("BuildingType", String.valueOf(airPhotoBuilding.getBuilldingType()))
                .addFormDataPart("BuildingStructureType", String.valueOf(propertyStructure))
                .addFormDataPart("PropertyUseType", String.valueOf(propertyUse))
                .addFormDataPart("Layer", layer)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("Reason", reason)
                .addFormDataPart("JsonItems", editedBase64);
        List<ImgInfo> imgInfos = rvPhoto.getDate();
        for (int i = 0; i < imgInfos.size(); i++) {
            Uri uri = imgInfos.get(i).getUri();
            if (uri != null) {
                File photoFile = FileUtil.getFileByUri(uri, this);
                bodyBuilder.addFormDataPart("Files" + i, photoFile.getName(), RequestBody.create(MediaType
                        .parse("image/*"), photoFile));
            }
        }
        return bodyBuilder;
    }

    @Override
    public void initNet() {

    }

    public static void goActivity(Activity context, AirPhotoBuilding airPhotoBuilding) {
        Intent intent = new Intent(context, AirPhotoApplyActivity.class);
        intent.putExtra(Constants.Extra.AIRPHOTO_BUILDING, airPhotoBuilding);
        context.startActivity(intent);
        context.finish();
    }

    @Override
    public void onApplyAirPhotoSuccess(AirPhotoItem airPhotoItem) {
        if (iSaveSend) {
            EventBus.getDefault().post(new AddAirPhotoEvent(airPhotoItem, Constants.AirPhotoType.DONE));
            showSuccessDialogAndFinish("发送成功");
        } else {
            EventBus.getDefault().post(new AddAirPhotoEvent(airPhotoItem, Constants.AirPhotoType.TODO));
            showSuccessDialogAndFinish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.UNRECORDBUILDING:
                    editedBase64 = data.getStringExtra(Constants.Extra.EDITEDBASE64);
                    unRecordBuildingList = (List<UnRecordBuilding>) data.getSerializableExtra(Constants.Extra
                            .UNRECORD_BUILDING_LIST);
                    LogUtil.e(TAG, "editedBase64:" + editedBase64);
                    LogUtil.e(TAG, "unRecordBuildingList:" + unRecordBuildingList.size());
                    break;
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    rvPhoto.onPhotoCallback(requestCode, resultCode, data);
                    break;
                default:
                    break;
            }
        }
    }

}
