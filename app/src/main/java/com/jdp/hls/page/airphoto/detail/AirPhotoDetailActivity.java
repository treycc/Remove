package com.jdp.hls.page.airphoto.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
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
import com.jdp.hls.model.entiy.AuthAirPhoto;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.ModifyAirPhotoEvent;
import com.jdp.hls.model.entiy.UnRecordBuilding;
import com.jdp.hls.page.airphoto.unrecordbuilding.UnrecordBuildingListActivity;
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
    @BindView(R.id.rv_airphotoPhoto)
    AddableRecyclerView rvAirphotoPhoto;
    @BindView(R.id.rv_agePhoto)
    AddableRecyclerView rvAgePhoto;
    @BindView(R.id.ll_airphoto_send)
    LinearLayout llAirphotoSend;
    @BindView(R.id.ll_airphoto_review)
    LinearLayout llAirphotoReview;
    @BindView(R.id.ll_airphoto_finish)
    LinearLayout llAirphotoFinish;
    @BindView(R.id.ll_airphoto_operateBar)
    LinearLayout llAirphotoOperateBar;
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
    List<UnRecordBuilding> unRecordBuildingList = new ArrayList<>();
    private boolean allowEditAppraise;

    @OnClick({R.id.rl_unrecordBuilding})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.rl_unrecordBuilding:
                UnrecordBuildingListActivity.goActivity(this, unRecordBuildingList);
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
    }

    @Override
    protected void initNet() {
        airPhotoDetailPresenter.getAirPhotoDetail(airCheckProId);
    }

    @Override
    public void onGetAirPhotoDetailSuccess(AirPhotoItem airPhotoItem) {
        this.airPhotoItem = airPhotoItem;
        setContentTitle(airPhotoItem.getCheckTypeName());
        unRecordBuildingList = airPhotoItem.getItems();
        spinnerPropertyUse.setSelectItem(airPhotoItem.getPropertyUseType());
        spinnerStructureType.setSelectItem(airPhotoItem.getBuildingStructureType());
        tvAirphotoCusCode.setString(airPhotoItem.getCusCode());
        tvAirphotoRealName.setString(airPhotoItem.getRealName());
        tvAirphotoAddress.setString(airPhotoItem.getAddress());
        etAirphotoLayer.setString(airPhotoItem.getLayer());
        etAirphotoReason.setString(airPhotoItem.getReason());
        etAirphotoRemark.setString(airPhotoItem.getRemark());
        List<ImgInfo> agePhotos = airPhotoItem.getAppraiseFiles();
        List<ImgInfo> airPhotos = airPhotoItem.getFiles();
        AuthAirPhoto auth = airPhotoItem.getAuth();
        boolean allowEdit = airPhotoItem.isAllowEdit();
        int checkType = airPhotoItem.getCheckType();

        //初审可以改，初审后不能改
        spinnerPropertyUse.enable(checkType == Status.AirPhotoType.FIRST && allowEdit);
        spinnerStructureType.enable(checkType == Status.AirPhotoType.FIRST && allowEdit);
        etAirphotoLayer.setEnabled(checkType == Status.AirPhotoType.FIRST && allowEdit);

        allowEditAppraise = auth.isAllowEditAppraise();
        etAirphotoReason.setEnabled(allowEdit);
        etAirphotoRemark.setEnabled(allowEdit);
        rvAirphotoPhoto.setDate(airPhotos, allowEdit);
        rvAgePhoto.setDate(agePhotos, allowEditAppraise);
        //按钮状态
        initOperate(airPhotoItem.getAuth());

        //航拍保存
        if (allowEdit) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    airPhotoDetailPresenter.modifyAirPhotoDetail(getSaveBody().build());
                }
            });
        }
        //年限保存
        if (allowEditAppraise) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    airPhotoDetailPresenter.updateAgePhotos(getAgeBody().build());
                }
            });
        }
    }

    private void initOperate(AuthAirPhoto auth) {
        boolean showSend = auth.isAllowSend();
        boolean showCheck = auth.isAllowCheck();
        boolean showFinish = auth.isAllowCloseFinished();
        boolean allowEditAppraise = auth.isAllowEditAppraise();
        llAirphotoOperateBar.setVisibility((showFinish || showSend || showCheck) ? View.VISIBLE : View.GONE);
        llAirphotoSend.setVisibility(showSend ? View.VISIBLE : View.GONE);
        llAirphotoReview.setVisibility(showCheck ? View.VISIBLE : View.GONE);
        llAirphotoFinish.setVisibility(showFinish ? View.VISIBLE : View.GONE);
        if (showSend) {
            llAirphotoSend.setOnClickListener(v -> {
                if (allowEditAppraise) {
                    airPhotoDetailPresenter.updateAgePhotos(getAgeBody().addFormDataPart("IsSend", "true").build());
                } else {
                    airPhotoDetailPresenter.sendAirPhoto(getSaveBody().addFormDataPart("IsSend", "true").build());
                }
            });
        }
        if (showCheck) {
            llAirphotoReview.setOnClickListener(v -> {
                airPhotoDetailPresenter.reviewAirPhoto(getSaveBody().build());
            });
        }
        if (showFinish) {
            llAirphotoFinish.setOnClickListener(v -> {
                airPhotoDetailPresenter.finishAirPhoto(getAirProIdBody());
            });
        }
    }

    private MultipartBody.Builder getSaveBody() {
        String remark = etAirphotoRemark.getText().toString().trim();
        String layer = etAirphotoLayer.getText().toString().trim();
        String reason = etAirphotoReason.getText().toString().trim();
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
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
                .addFormDataPart("DeleteFileIDs", rvAirphotoPhoto.getDeleteImgIds());
        List<ImgInfo> imgInfos = rvAirphotoPhoto.getDate();
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

    private MultipartBody.Builder getAgeBody() {
        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("AirCheckProId", airCheckProId)
                .addFormDataPart("DeleteFileIDs", rvAirphotoPhoto.getDeleteImgIds());
        List<ImgInfo> imgInfos = rvAgePhoto.getDate();
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

    @NonNull
    private RequestBody getAirProIdBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("AirCheckProId", String.valueOf(airPhotoItem.getAirCheckProId()))
                .build();
    }

    @Override
    public void onModifyAirPhotoDetailSuccess(AirPhotoItem airPhotoItem) {
        EventBus.getDefault().post(new ModifyAirPhotoEvent(airPhotoItem));
        showSuccessAndFinish();
    }

    @Override
    public void onSendAirPhotoSuccess(AirPhotoItem airPhotoItem) {
        showSuccessAndFinish("发送成功");
    }

    @Override
    public void onReviewAirPhotoSuccess(AirPhotoItem airPhotoItem) {
        EventBus.getDefault().post(new ModifyAirPhotoEvent(airPhotoItem));
        showSuccessAndFinish("复查成功");
    }

    @Override
    public void onUpdateAgePhotosSuccess() {
        showSuccessAndFinish("操作成功");
    }

    @Override
    public void onFinishAirPhotoSuccess() {
        showSuccessAndFinish("完结成功");
    }


    public static void goActivity(Context context, String airCheckProId) {
        Intent intent = new Intent(context, AirPhotoDetailActivity.class);
        intent.putExtra(Constants.Extra.AIRCHECKPRO_ID, airCheckProId);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.UNRECORDBUILDING:
                    deleteIds = data.getStringExtra(Constants.Extra.DELETEIDS);
                    editedBase64 = data.getStringExtra(Constants.Extra.EDITEDBASE64);
                    unRecordBuildingList = (List<UnRecordBuilding>) data.getSerializableExtra(Constants.Extra
                            .UNRECORD_BUILDING_LIST);
                    LogUtil.e(TAG, "deleteIds:" + deleteIds);
                    LogUtil.e(TAG, "editedBase64:" + editedBase64);
                    LogUtil.e(TAG, "unRecordBuildingList:" + unRecordBuildingList.size());
                    break;
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    if (allowEditAppraise) {
                        rvAgePhoto.onPhotoCallback(requestCode, resultCode, data);
                    } else {
                        rvAirphotoPhoto.onPhotoCallback(requestCode, resultCode, data);
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
