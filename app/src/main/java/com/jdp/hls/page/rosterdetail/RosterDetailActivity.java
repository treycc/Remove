package com.jdp.hls.page.rosterdetail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.jdp.hls.R;
import com.jdp.hls.activity.BigImgActivity;
import com.jdp.hls.activity.LocationActivity;
import com.jdp.hls.activity.ModifyActivity;
import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.adapter.ImgAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
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
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.ModifyMap;
import com.jdp.hls.view.RvItemDecoration;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
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
    @BindView(R.id.tv_roster_name)
    TextView tvRosterName;
    @BindView(R.id.ll_roster_name)
    LinearLayout llRosterName;
    @BindView(R.id.tv_roster_address)
    TextView tvRosterAddress;
    @BindView(R.id.ll_roster_address)
    LinearLayout llRosterAddress;
    @BindView(R.id.tv_roster_phone)
    TextView tvRosterPhone;
    @BindView(R.id.ll_roster_phone)
    LinearLayout llRosterPhone;
    @BindView(R.id.tv_roster_idcard)
    TextView tvRosterIdcard;
    @BindView(R.id.ll_roster_idcard)
    LinearLayout llRosterIdcard;
    @BindView(R.id.rv_roster_img)
    RecyclerView rvRosterImg;
    @BindView(R.id.tv_roster_remark)
    TextView tvRosterRemark;
    @BindView(R.id.ll_roster_remark)
    LinearLayout llRosterRemark;
    @BindView(R.id.ll_roster_img)
    LinearLayout llRosterImg;
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
    @BindView(R.id.tv_roster_companyName)
    TextView tvRosterCompanyName;
    @BindView(R.id.ll_roster_location)
    LinearLayout llRosterLocation;
    private static final int REQUEST_CODE_LOCATION = 1;
    private List<ImgInfo> imgInfos = new ArrayList<>();
    private Roster roster;
    private LngLatFragment lngLatFragment;
    private ImgAdapter imgAdapter;
    List<Uri> mSelectedUris;
    private ModifyMap modifyMap;
    @Inject
    RosterDetailPresenter rosterDetailPresenter;
    private double lng;
    private double lat;
    private boolean gender = true;
    private boolean isEnterprise = false;
    private boolean isMeasured = true;
    private boolean isEvaluated = true;
    private String personId;
    private String houseId;


    @OnClick({R.id.ll_roster_name, R.id.ll_roster_address, R.id.ll_roster_idcard, R.id.ll_roster_phone, R.id
            .ll_roster_location, R.id.ll_roster_companyName, R.id.ll_roster_remark})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_roster_companyName:
                String companyName = tvRosterCompanyName.getText().toString().trim();
                ModifyActivity.goActivityInActivity(this, Constants.ModifyCode.MODIFY_COMPANY_NAME, "企业名称",
                        companyName);
                break;
            case R.id.ll_roster_name:
                String name = tvRosterName.getText().toString().trim();
                ModifyActivity.goActivityInActivity(this, Constants.ModifyCode.MODIFY_OWNER_NAME, "户主", name);
                break;
            case R.id.ll_roster_address:
                String address = tvRosterAddress.getText().toString().trim();
                ModifyActivity.goActivityInActivity(this, Constants.ModifyCode.MODIFY_ADDRESS, "地址", address);
                break;
            case R.id.ll_roster_idcard:
                String idcard = tvRosterIdcard.getText().toString().trim();
                ModifyActivity.goActivityInActivity(this, Constants.ModifyCode.MODIFY_IDCARD, "身份证号", idcard);
                break;
            case R.id.ll_roster_phone:
                String phone = tvRosterPhone.getText().toString().trim();
                ModifyActivity.goActivityInActivity(this, Constants.ModifyCode.MODIFY_PHONE, "手机号", phone);
                break;
            case R.id.ll_roster_remark:
                String remark = tvRosterRemark.getText().toString().trim();
                ModifyActivity.goActivityInActivity(this, Constants.ModifyCode.MODIFY_REMARK, "备注", remark);
                break;
            case R.id.ll_roster_location:
                GoUtil.goActivityForResult(this, LocationActivity.class, REQUEST_CODE_LOCATION);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            String newVaule = data.getStringExtra("newVaule");
            switch (requestCode) {
                case Constants.ModifyCode.MODIFY_COMPANY_NAME:
                    tvRosterCompanyName.setText(newVaule);
                    modifyMap.setCompanyName(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_OWNER_NAME:
                    tvRosterName.setText(newVaule);
                    modifyMap.setRealName(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_ADDRESS:
                    tvRosterAddress.setText(newVaule);
                    modifyMap.setAddress(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_PHONE:
                    tvRosterPhone.setText(newVaule);
                    modifyMap.setMobile(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_IDCARD:
                    tvRosterIdcard.setText(newVaule);
                    modifyMap.setIdcard(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_REMARK:
                    tvRosterRemark.setText(newVaule);
                    modifyMap.setRemark(newVaule);
                    break;
                case REQUEST_CODE_LOCATION:
                    lng = data.getDoubleExtra("lng", -1);
                    lat = data.getDoubleExtra("lat", -1);
                    setLocation(lng, lat);
                    modifyMap.setLocation(lng, lat);
                    break;
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    mSelectedUris = Matisse.obtainResult(data);
                    imgAdapter.addData(MatisseUtil.getImgInfoFromUri(mSelectedUris));
                    modifyMap.setImgs();
                    break;
                default:
                    break;

            }
            checkHasModified();
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
        String address = tvRosterAddress.getText().toString().trim();
        String name = tvRosterName.getText().toString().trim();
        String phone = tvRosterPhone.getText().toString().trim();
        String idcard = tvRosterIdcard.getText().toString().trim();
        String remark = tvRosterRemark.getText().toString().trim();
        String companyName = tvRosterCompanyName.getText().toString().trim();
        if (CheckUtil.checkEmpty(name, "请输入户主姓名")
                && CheckUtil.checkEmpty(address, "请输入地址")
                && checkCompanyName(companyName)
                && CheckUtil.checkPhoneFormatAllowEmpty(phone)
                && CheckUtil.checkIdCardAllowEmpty(idcard)
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
                            .parse
                                    ("image/*"), photoFile));
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
                    MatisseUtil.openCamera(RosterDetailActivity.this, Constants.MAX_IMG_UPLOAD_COUNT);
                } else {
                    BigImgActivity.goActivity(RosterDetailActivity.this, MatisseUtil.getDTOImgInfoFromImgInfo(imgAdapter
                            .getDate()), position);
                }
            }
        });
        imgAdapter.setOnImgDeletedListener(new ImgAdapter.OnImgDeletedListener() {
            @Override
            public void onImgDeleted() {
                modifyMap.setImgs();
                checkHasModified();
            }
        });
        initSwitchButton();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getSupportFragmentManager().beginTransaction().remove(lngLatFragment).commitAllowingStateLoss();
    }

    private void initSwitchButton() {
        smbRosterGender.setOnSwitchListener((position, tabText) -> {
            gender = position == 0;
            modifyMap.setGender(gender);
            checkHasModified();
        });
        smbRosterMeasured.setOnSwitchListener((position, tabText) -> {
            isMeasured = position == 0;
            modifyMap.setMeasured(isMeasured);
            checkHasModified();
        });
        smbRosterEvaluated.setOnSwitchListener((position, tabText) -> {
            isEvaluated = position == 0;
            modifyMap.setEvaluated(isEvaluated);
            checkHasModified();
        });
    }

    @Override
    protected void initNet() {
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
        modifyMap = new ModifyMap(rosterDetail);
        tvRosterName.setText(rosterDetail.getRealName());
        tvRosterAddress.setText(rosterDetail.getHouseAddress());
        tvRosterPhone.setText(rosterDetail.getMobilePhone());
        tvRosterIdcard.setText(rosterDetail.getIdcard());
        tvRosterRemark.setText(rosterDetail.getRemark());
        isEnterprise = rosterDetail.isEnterprise();
        gender = rosterDetail.isGender();
        isMeasured = rosterDetail.isMeasured();
         isEvaluated = rosterDetail.isEvaluated();
        smbRosterGender.setSelectedTab(gender ? 0 : 1);
        smbRosterMeasured.setSelectedTab(isMeasured ? 0 : 1);
        smbRosterEvaluated.setSelectedTab(isEvaluated ? 0 : 1);
        llRosterCompanyName.setVisibility(rosterDetail.isEnterprise() ? View.VISIBLE : View.GONE);
        tvRosterCompanyName.setText(rosterDetail.getEnterpriseName());
        setLocation(lng, lat);
        List<ImgInfo> houseFiles = rosterDetail.getHouseFiles();
        if (houseFiles != null && houseFiles.size() > 0) {
            imgAdapter.addData(houseFiles);
        }
    }

    @Override
    public void onModifyRosterSuccess() {
        EventBus.getDefault().post(new RefreshRostersEvent(isEnterprise));
        DialogUtil.showQuitDialog(this, "花名册修改成功");
    }

    private void setLocation(double lng, double lat) {
        if (lng != 0 && lat != 0) {
            lngLatFragment.setLnglat(lng, lat);
            ivRosterLocation.setBackgroundResource(R.mipmap.ic_confirm_sel);
            tvRosterHasLocationed.setText("已定位");
        } else {
            ivRosterLocation.setBackgroundResource(R.mipmap.ic_confirm_nor);
            tvRosterHasLocationed.setText("未定位");
        }
    }

    private void checkHasModified() {
        if (modifyMap.hasModified()) {
            setRightClick("保存", noDoubleClickListener);
        } else {
            hideRightClick();
        }
    }

    @Override
    public void onBackPressed() {
        showQuitDialog();
    }

    private void showQuitDialog() {
        if (modifyMap == null) {
            finish();
            return;
        }
        if (!modifyMap.hasModified()) {
            finish();
            return;
        }
        DialogUtil.showDoubleDialog(this, "当前正处于修改状态，是否确认退出？", new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                finish();
            }
        });
    }

    @Override
    protected void onBack() {
        showQuitDialog();
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }
}
