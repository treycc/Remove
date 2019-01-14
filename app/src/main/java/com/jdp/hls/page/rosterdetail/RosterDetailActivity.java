package com.jdp.hls.page.rosterdetail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.activity.BigImgActivity;
import com.jdp.hls.activity.LocationActivity;
import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.adapter.ImgAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.ModifyRostersEvent;
import com.jdp.hls.event.RefreshRostersEvent;
import com.jdp.hls.fragment.LngLatFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.RosterDetail;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.PermissionsUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.RequiredTextView;
import com.jdp.hls.view.RvItemDecoration;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lib.kingja.switchbutton.SwitchMultiButton;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:花名册详情
 * Create Time:2018/7/30 0030 上午 10:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterDetailActivity extends BaseTitleActivity implements RosterDetailContract.View {
    @BindView(R.id.et_roster_name)
    EnableEditText etRosterName;
    @BindView(R.id.et_roster_address)
    EnableEditText etRosterAddress;
    @BindView(R.id.et_roster_phone)
    EnableEditText tvRosterPhone;
    @BindView(R.id.et_roster_idcard)
    EnableEditText etRosterIdcard;
    @BindView(R.id.rv_roster_img)
    RecyclerView rvRosterImg;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.smb_roster_gender)
    SwitchMultiButton smbRosterGender;
    @BindView(R.id.smb_roster_measured)
    SwitchMultiButton smbRosterMeasured;
    @BindView(R.id.smb_roster_evaluated)
    SwitchMultiButton smbRosterEvaluated;
    @BindView(R.id.iv_roster_location)
    ImageView ivRosterLocation;
    @BindView(R.id.tv_roster_hasLocationed)
    TextView tvRosterHasLocationed;
    @BindView(R.id.ll_roster_companyName)
    LinearLayout llRosterCompanyName;
    @BindView(R.id.et_roster_companyName)
    EnableEditText etRosterCompanyName;
    @BindView(R.id.ll_roster_location)
    LinearLayout llRosterLocation;
    @BindView(R.id.smb_roster_assetEvaluator)
    SwitchMultiButton smbRosterAssetEvaluator;
    @BindView(R.id.ll_assetEvaluator)
    LinearLayout llAssetEvaluator;
    @BindView(R.id.rtv_ownerType)
    RequiredTextView rtvOwnerType;
    private List<ImgInfo> imgInfos = new ArrayList<>();
    private Roster roster;
    private LngLatFragment lngLatFragment;
    private ImgAdapter imgAdapter;
    List<Uri> mSelectedUris;
    @Inject
    RosterDetailPresenter rosterDetailPresenter;
    private double lng;
    private double lat;
    private boolean gender = true;
    private boolean isEnterprise = false;
    private boolean isMeasured = false;
    private boolean isEvaluated = false;
    private boolean isAssetEvaluator = false;
    private String personId;
    private String houseId;
    private String realNameTip;


    @OnClick({R.id.ll_roster_location})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_roster_location:
                GoUtil.goActivityForResult(this, LocationActivity.class, Constants.RequestCode.LOCATION);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.LOCATION:
                    lng = data.getDoubleExtra("lng", -1);
                    lat = data.getDoubleExtra("lat", -1);
                    setLocation(lng, lat);
                    break;
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    mSelectedUris = Matisse.obtainResult(data);
                    imgAdapter.addData(MatisseUtil.getImgInfoFromUri(mSelectedUris));
                    break;
                default:
                    break;
            }
        }
    }


    @Override
    public void initVariable() {
        roster = (Roster) getIntent().getSerializableExtra("roster");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_roster_detail;
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
        return "详情";
    }

    @Override
    protected void initView() {
        rosterDetailPresenter.attachView(this);
        imgAdapter = new ImgAdapter(this, imgInfos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rvRosterImg.setLayoutManager(layoutManager);
        rvRosterImg.setAdapter(imgAdapter);
        rvRosterImg.setItemAnimator(new DefaultItemAnimator());
        rvRosterImg.addItemDecoration(new RvItemDecoration(this, RvItemDecoration.LayoutStyle.HORIZONTAL_LIST,
                0, 0x00ffffff));
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            checkDate();
        }
    };

    private void checkDate() {
        String address = etRosterAddress.getText().toString().trim();
        String name = etRosterName.getText().toString().trim();
        String phone = tvRosterPhone.getText().toString().trim();
        String idcard = etRosterIdcard.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        String companyName = etRosterCompanyName.getText().toString().trim();
        if (CheckUtil.checkEmpty(name, "请输入" + realNameTip)
                && CheckUtil.checkEmpty(address, "请输入地址")
                && checkCompanyName(companyName)
                && CheckUtil.checkLngLat(lng, lat)) {
            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("isEnterprise", String.valueOf(isEnterprise))
                    .addFormDataPart("projectId", SpSir.getInstance().getProjectId())
                    .addFormDataPart("houseId", houseId)
                    .addFormDataPart("personId", personId)
                    .addFormDataPart("realName", name)
                    .addFormDataPart("gender", String.valueOf(gender))
                    .addFormDataPart("address", address)
                    .addFormDataPart("idcard", idcard)
                    .addFormDataPart("mobilePhone", phone)
                    .addFormDataPart("isEvaluated", String.valueOf(isEvaluated))
                    .addFormDataPart("isMeasured", String.valueOf(isMeasured))
                    .addFormDataPart("isAssetEvaluator", String.valueOf(isAssetEvaluator))
                    .addFormDataPart("longitude", String.valueOf(lng))
                    .addFormDataPart("latitude", String.valueOf(lat))
                    .addFormDataPart("deleteFileIDs", imgAdapter.getDeleteImgIds())
                    .addFormDataPart("remark", remark);
            /*如果是企业，则传企业名称*/
            if (isEnterprise) {
                bodyBuilder.addFormDataPart("enterpriseName", companyName);
            }
            List<ImgInfo> imgInfos = imgAdapter.getDate();
            for (int i = 0; i < imgInfos.size(); i++) {
                Uri uri = imgInfos.get(i).getUri();
                if (uri != null) {
                    File photoFile = FileUtil.getFileByUri(uri, this);
                    bodyBuilder.addFormDataPart("rosterFile" + i, photoFile.getName(), RequestBody.create(MediaType
                            .parse("image/*"), photoFile));
                }

            }
            RequestBody requestBody = bodyBuilder.build();
            rosterDetailPresenter.modifyRoster(requestBody);
        }
    }

    private boolean checkCompanyName(String companyName) {
        if (isEnterprise) {
            if (TextUtils.isEmpty(companyName)) {
                ToastUtil.showText("请输入企业名称");
                return false;
            }
        }
        return true;
    }

    @Override
    protected void initData() {
        setRightClick("保存", noDoubleClickListener);
        lngLatFragment = (LngLatFragment) getSupportFragmentManager().findFragmentById(R.id
                .fragment_lnglat);
        imgAdapter.setOnItemClickListener(new BaseRvPositionAdapter.OnItemClickListener<ImgInfo>() {
            @Override
            public void onItemClick(List<ImgInfo> list, int position) {
                if (imgAdapter.isLastItem(position)) {
                    PermissionsUtil.checkOpenPhoto(RosterDetailActivity.this);
                } else {
                    BigImgActivity.goActivity(RosterDetailActivity.this, MatisseUtil.getDTOImgInfoFromImgInfo(imgAdapter
                            .getDate()), position);
                }
            }
        });
        initSwitchButton();
    }

    private void initSwitchButton() {
        smbRosterGender.setOnSwitchListener((position, tabText) -> {
            gender = position == 0;
        });
        smbRosterMeasured.setOnSwitchListener((position, tabText) -> {
            isMeasured = position == 1;
        });
        smbRosterEvaluated.setOnSwitchListener((position, tabText) -> {
            isEvaluated = position == 1;
        });
        smbRosterAssetEvaluator.setOnSwitchListener((position, tabText) -> {
            isAssetEvaluator = position == 1;
        });
    }

    @Override
    public void initNet() {
        rosterDetailPresenter.getRosterDetail(roster.getHouseId(), SpSir.getInstance().getEmployeeId(), roster
                .isEnterprise() ? 1 : 0);
    }

    public static void goActivity(Activity context, Roster roster) {
        Intent intent = new Intent(context, RosterDetailActivity.class);
        intent.putExtra("roster", roster);
        context.startActivity(intent);
    }

    @Override
    public void onGetRosterDetailSuccess(RosterDetail rosterDetail) {
        houseId = rosterDetail.getHouseId();
        personId = rosterDetail.getPersonId();
        lng = rosterDetail.getLongitude();
        lat = rosterDetail.getLatitude();
        etRosterName.setText(rosterDetail.getRealName());
        etRosterAddress.setText(rosterDetail.getHouseAddress());
        tvRosterPhone.setText(rosterDetail.getMobilePhone());
        etRosterIdcard.setText(rosterDetail.getIdcard());
        etRemark.setText(rosterDetail.getRemark());
        isEnterprise = rosterDetail.isEnterprise();
        gender = rosterDetail.isGender();
        isMeasured = rosterDetail.isMeasured();
        isEvaluated = rosterDetail.isEvaluated();
        isAssetEvaluator = rosterDetail.isAssetEvaluator();
        smbRosterGender.setSelectedTab(gender ? 0 : 1);
        smbRosterMeasured.setSelectedTab(isMeasured ? 1 : 0);
        smbRosterEvaluated.setSelectedTab(isEvaluated ? 1 : 0);
        smbRosterAssetEvaluator.setSelectedTab(isAssetEvaluator ? 1 : 0);
        llRosterCompanyName.setVisibility(rosterDetail.isEnterprise() ? View.VISIBLE : View.GONE);
        llAssetEvaluator.setVisibility(rosterDetail.isEnterprise() ? View.VISIBLE : View.GONE);
        etRosterCompanyName.setText(rosterDetail.getEnterpriseName());
        realNameTip = (isEnterprise ? "负责人" : "户主");
        rtvOwnerType.setText(realNameTip);

        setLocation(lng, lat);
        List<ImgInfo> houseFiles = rosterDetail.getHouseFiles();
        if (houseFiles != null && houseFiles.size() > 0) {
            imgAdapter.addData(houseFiles);
        }
    }

    @Override
    public void onModifyRosterSuccess() {
        EventBus.getDefault().post(new RefreshRostersEvent());
        EventBus.getDefault().post(getModifyRostersEvent());
        DialogUtil.showQuitDialog(this, "花名册修改成功");
    }

    private ModifyRostersEvent getModifyRostersEvent() {
        String address = etRosterAddress.getText().toString().trim();
        String name = etRosterName.getText().toString().trim();
        String phone = tvRosterPhone.getText().toString().trim();
        Roster roster = new Roster();
        roster.setHouseId(houseId);
        roster.setLongitude(lng);
        roster.setLatitude(lat);
        roster.setEnterprise(isEnterprise);
        roster.setEvaluated(isEvaluated);
        roster.setMeasured(isMeasured);
        roster.setRealName(name);
        roster.setMobilePhone(phone);
        roster.setHouseAddress(address);
        return new ModifyRostersEvent(roster);
    }


    private void setLocation(double lng, double lat) {
        if (lng > 0 && lat > 0) {
            lngLatFragment.setLnglat(lng, lat);
            ivRosterLocation.setBackgroundResource(R.mipmap.ic_confirm_sel);
            tvRosterHasLocationed.setText("已定位");
        } else {
            ivRosterLocation.setBackgroundResource(R.mipmap.ic_confirm_nor);
            tvRosterHasLocationed.setText("未定位");
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
