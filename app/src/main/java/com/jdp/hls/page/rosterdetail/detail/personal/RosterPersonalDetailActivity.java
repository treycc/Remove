package com.jdp.hls.page.rosterdetail.detail.personal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.activity.LocationActivity;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.fragment.LngLatFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.RosterPersonalDetail;
import com.jdp.hls.page.rosterdetail.contacts.list.ContactsListActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.AddableRecyclerView;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import lib.kingja.switchbutton.SwitchMultiButton;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2019/1/30 0030 上午 9:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterPersonalDetailActivity extends BaseTitleActivity implements RosterPersonalDetailContract.View {
    @BindView(R.id.tv_personCount)
    StringTextView tvPersonCount;
    @BindView(R.id.ll_contacts)
    LinearLayout llContacts;
    @BindView(R.id.et_roster_address)
    EnableEditText etRosterAddress;
    @BindView(R.id.smb_measure)
    SwitchMultiButton smbMeasure;
    @BindView(R.id.smb_evaluate)
    SwitchMultiButton smbEvaluate;
    @BindView(R.id.rv_addable_photo_preview)
    AddableRecyclerView rvAddablePhotoPreview;
    @BindView(R.id.rl_addable_photo_preview)
    RelativeLayout rlAddablePhotoPreview;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.ll_location)
    LinearLayout llLocation;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private boolean isMeasured;
    private boolean isEvaluated;
    @Inject
    RosterPersonalDetailPresenter rosterPersonalDetailPresenter;
    private String buildingId;
    private double lng;
    private double lat;
    private LngLatFragment locationFragment;
    private int buildingType;

    @OnClick({R.id.ll_location, R.id.ll_contacts})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_location:
                GoUtil.goActivityForResult(this, LocationActivity.class, Constants.RequestCode.LOCATION);
                break;
            case R.id.ll_contacts:
                //联系人
                ContactsListActivity.goActivity(this, buildingId, buildingType);
                break;

        }
    }

    @Override
    public void initVariable() {
        buildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_roster_detail_personal;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        rosterPersonalDetailPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "个人房产";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        smbMeasure.setOnSwitchListener((position, tabText) -> {
            isMeasured = position == 1;
        });
        smbEvaluate.setOnSwitchListener((position, tabText) -> {
            isEvaluated = position == 1;
        });
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                saveData();
            }
        });
        locationFragment = (LngLatFragment) getSupportFragmentManager().findFragmentById(R.id
                .fragment_location);
    }

    private void saveData() {
        String address = etRosterAddress.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        if (CheckUtil.checkEmpty(address, "请输入地址")) {
            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("HouseId", buildingId)
                    .addFormDataPart("address", address)
                    .addFormDataPart("isEvaluated", String.valueOf(isEvaluated))
                    .addFormDataPart("isMeasured", String.valueOf(isMeasured))
                    .addFormDataPart("longitude", String.valueOf(lng))
                    .addFormDataPart("latitude", String.valueOf(lat))
                    .addFormDataPart("deleteFileIDs", TextUtils.isEmpty(rvAddablePhotoPreview.getDeleteImgIds()) ? "" :
                            rvAddablePhotoPreview.getDeleteImgIds())
                    .addFormDataPart("remark", remark);
            List<ImgInfo> imgInfos = rvAddablePhotoPreview.getDate();
            for (int i = 0; i < imgInfos.size(); i++) {
                Uri uri = imgInfos.get(i).getUri();
                if (uri != null) {
                    File photoFile = FileUtil.getFileByUri(uri, this);
                    bodyBuilder.addFormDataPart("HouseFile" + i, photoFile.getName(), RequestBody.create(MediaType
                            .parse("image/*"), photoFile));
                }
            }
            RequestBody requestBody = bodyBuilder.build();
            rosterPersonalDetailPresenter.saveRosterHouse(requestBody);
        }
    }

    @Override
    public void initNet() {
        if (TextUtils.isEmpty(buildingId)) {
            //新增
            showSuccessCallback();
            llContacts.setVisibility(View.GONE);
        } else {
            //修改
            rosterPersonalDetailPresenter.getRosterPersonalDetail(buildingId);
        }
    }

    @Override
    public void onGetRosterPersonalDetailSuccess(RosterPersonalDetail rosterPersonalDetail) {
        rvAddablePhotoPreview.setDate(rosterPersonalDetail.getHouseFiles(), true);
        buildingType = rosterPersonalDetail.getBuildingType();
        lng = rosterPersonalDetail.getLongitude();
        lat = rosterPersonalDetail.getLatitude();
        etRosterAddress.setText(rosterPersonalDetail.getHouseAddress());
        etRemark.setText(rosterPersonalDetail.getRemark());
        isMeasured = rosterPersonalDetail.isMeasured();
        isEvaluated = rosterPersonalDetail.isEvaluated();
        smbMeasure.setSelectedTab(isMeasured ? 1 : 0);
        smbEvaluate.setSelectedTab(isEvaluated ? 1 : 0);
        tvPersonCount.setString(String.format("共%d人", rosterPersonalDetail.getPersonCount()));
        setLocation(lng, lat);
    }

    private void setLocation(double lng, double lat) {
        if (lng > 0 && lat > 0) {
            locationFragment.setLnglat(lng, lat);
            ivLocation.setBackgroundResource(R.mipmap.ic_confirm_sel);
            tvLocation.setText("已定位");
        } else {
            ivLocation.setBackgroundResource(R.mipmap.ic_confirm_nor);
            tvLocation.setText("未定位");
        }
    }

    @Override
    public void onSaveRosterHouseSuccess() {
        if (TextUtils.isEmpty(buildingId)) {
            //新增
            llContacts.setVisibility(View.VISIBLE);
        } else {
            //修改
        }
        showSuccessDialogAndFinish();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    public static void goActivity(Activity context, String buildingId) {
        Intent intent = new Intent(context, RosterPersonalDetailActivity.class);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        context.startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    rvAddablePhotoPreview.onPhotoCallback(requestCode, resultCode, data);
                    break;
                case Constants.RequestCode.LOCATION:
                    lng = data.getDoubleExtra("lng", -1);
                    lat = data.getDoubleExtra("lat", -1);
                    setLocation(lng, lat);
                    break;

                case Constants.RequestCode.ContactsList:
                    int personCount = data.getIntExtra(Constants.Extra.Number, 0);
                    tvPersonCount.setString(String.format("共%d人",personCount));
                    break;
                default:
                    break;
            }
        }
    }
}
