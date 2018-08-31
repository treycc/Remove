package com.jdp.hls.page.rosteradd;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.activity.BigImgActivity;
import com.jdp.hls.activity.LocationActivity;
import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.adapter.ImgUriAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.RefreshRostersEvent;
import com.jdp.hls.fragment.LngLatFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.page.personSearch.PersonSearchActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.RequiredTextView;
import com.jdp.hls.view.RvItemDecoration;
import com.kingja.supershapeview.view.SuperShapeEditText;
import com.kingja.supershapeview.view.SuperShapeTextView;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import lib.kingja.switchbutton.SwitchMultiButton;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:增加花名册
 * Create Time:2018/8/3 0003 下午 3:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterAddActivity extends BaseTitleActivity implements RosterAddContract.View {
    @BindView(R.id.set_roster_name)
    SuperShapeEditText setRosterName;
    @BindView(R.id.ll_roster_name)
    LinearLayout llRosterName;
    @BindView(R.id.set_roster_address)
    SuperShapeEditText setRosterAddress;
    @BindView(R.id.ll_roster_address)
    LinearLayout llRosterAddress;
    @BindView(R.id.set_roster_phone)
    SuperShapeEditText setRosterPhone;
    @BindView(R.id.ll_roster_phone)
    LinearLayout llRosterPhone;
    @BindView(R.id.set_roster_idcard)
    SuperShapeEditText setRosterIdcard;
    @BindView(R.id.ll_roster_idcard)
    LinearLayout llRosterIdcard;
    @BindView(R.id.rv_roster_img)
    RecyclerView rvRosterImg;
    @BindView(R.id.ll_roster_img)
    LinearLayout llRosterImg;
    @BindView(R.id.set_roster_remark)
    SuperShapeEditText setRosterRemark;
    @BindView(R.id.ll_roster_remark)
    LinearLayout llRosterRemark;
    @BindView(R.id.ll_roster_location)
    LinearLayout llRosterLocation;
    @BindView(R.id.smb_roster_gender)
    SwitchMultiButton smbRosterGender;
    @BindView(R.id.smb_roster_type)
    SwitchMultiButton smbRosterType;
    @BindView(R.id.smb_roster_measured)
    SwitchMultiButton smbRosterMeasured;
    @BindView(R.id.smb_roster_evaluated)
    SwitchMultiButton smbRosterEvaluated;
    @BindView(R.id.iv_roster_location)
    ImageView ivRosterLocation;
    @BindView(R.id.rtv_ownerType)
    RequiredTextView rtvOwnerType;
    @BindView(R.id.set_roster_import)
    SuperShapeTextView setRosterImport;
    @BindView(R.id.set_roster_companyName)
    SuperShapeEditText setRosterCompanyName;
    @BindView(R.id.ll_roster_companyName)
    LinearLayout llRosterCompanyName;
    @BindView(R.id.ll_roster_gender)
    LinearLayout llRosterGender;
    @BindView(R.id.ll_roster_type)
    LinearLayout llRosterType;
    @BindView(R.id.tv_roster_hasLocationed)
    TextView tvRosterHasLocationed;
    private List<Uri> photoUris = new ArrayList<>();
    private ImgUriAdapter imgUriAdapter;
    List<Uri> mSelectedUris;
    private static final int REQUEST_CODE_LOCATION = 1;
    private LngLatFragment lngLatFragment;
    @Inject
    RosterAddPresenter rosterAddPresenter;
    private double lng;
    private double lat;
    private boolean gender = true;
    private boolean isEnterprise=false;
    private boolean isMeasured = false;
    private boolean isEvaluated = false;
    private String personId;
    private static final int REQUEST_CODE_PERSON = 2;

    @OnClick({R.id.ll_roster_location, R.id.fragment_lnglat, R.id.set_roster_import})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_roster_location:
            case R.id.fragment_lnglat:
                GoUtil.goActivityForResult(this, LocationActivity.class, REQUEST_CODE_LOCATION);
                break;
            case R.id.set_roster_import:
                GoUtil.goActivityForResult(this, PersonSearchActivity.class, REQUEST_CODE_PERSON);
                break;
        }
    }


    @Override
    public void initVariable() {
        checkPermissions();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_roster_add;
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
        return "添加花名册";
    }

    @Override
    protected void initView() {
        rosterAddPresenter.attachView(this);
        imgUriAdapter = new ImgUriAdapter(this, photoUris);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rvRosterImg.setLayoutManager(layoutManager);
        rvRosterImg.setAdapter(imgUriAdapter);
        rvRosterImg.setItemAnimator(new DefaultItemAnimator());
        rvRosterImg.addItemDecoration(new RvItemDecoration(this, RvItemDecoration.LayoutStyle.HORIZONTAL_LIST,
                12, 0x00ffffff));
        imgUriAdapter.setOnItemClickListener(new BaseRvPositionAdapter.OnItemClickListener<Uri>() {
            @Override
            public void onItemClick(List<Uri> list, int position) {
                if (imgUriAdapter.isLastItem(position)) {
                    MatisseUtil.openCamera(RosterAddActivity.this, Constants.MAX_IMG_UPLOAD_COUNT);
                } else {
                    BigImgActivity.goActivity(RosterAddActivity.this, MatisseUtil.getDTOImgInfoFromUri(imgUriAdapter
                            .getDate()), position);
                }
            }
        });
    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                checkDate();
            }
        });
        lngLatFragment = (LngLatFragment) getSupportFragmentManager().findFragmentById(R.id
                .fragment_lnglat);
        initSwitchButton();
    }

    private void initSwitchButton() {
        smbRosterGender.setOnSwitchListener((position, tabText) -> gender = position == 0 );
        smbRosterType.setOnSwitchListener((position, tabText) -> {
            isEnterprise = position == 1 ;
            llRosterCompanyName.setVisibility(position == 1 ? View.VISIBLE : View.GONE);
        });
        smbRosterMeasured.setOnSwitchListener((position, tabText) -> isMeasured = position == 0 );
        smbRosterEvaluated.setOnSwitchListener((position, tabText) -> isEvaluated = position == 0 );
    }

    public void checkPermissions() {
        RxPermissions rxPermission = new RxPermissions(this);
        Disposable disposable = rxPermission
                .requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) throws Exception {
                        if (permission.granted) {
                            // 用户已经同意该权限
                            Log.d(TAG, permission.name + " is granted.");
                        } else if (permission.shouldShowRequestPermissionRationale) {
                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
                        } else {
                            // 用户拒绝了该权限，并且选中『不再询问』
                            Log.d(TAG, permission.name + " is denied.");
                        }
                    }
                });
    }

    private void checkDate() {
        String address = setRosterAddress.getText().toString().trim();
        String name = setRosterName.getText().toString().trim();
        String phone = setRosterPhone.getText().toString().trim();
        String idcard = setRosterIdcard.getText().toString().trim();
        String remark = setRosterRemark.getText().toString().trim();
        String companyName = setRosterCompanyName.getText().toString().trim();
        if (CheckUtil.checkEmpty(address, "请输入地址")
                && CheckUtil.checkEmpty(name, "请输入户主姓名")
                && checkCompanyName(companyName)
                && CheckUtil.checkPhoneFormatAllowEmpty(phone)
                && CheckUtil.checkIdCardAllowEmpty(idcard)
                && CheckUtil.checkLngLat(lng, lat)) {
            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("isEnterprise", String.valueOf(isEnterprise))
                    .addFormDataPart("projectId", SpSir.getInstance().getProjectId())
                    .addFormDataPart("realName", name)
                    .addFormDataPart("gender", String.valueOf(gender))
                    .addFormDataPart("address", address)
                    .addFormDataPart("idcard", idcard)
                    .addFormDataPart("mobilePhone", phone)
                    .addFormDataPart("remark", remark)
                    .addFormDataPart("isEvaluated", String.valueOf(isEvaluated))
                    .addFormDataPart("isMeasured", String.valueOf(isMeasured))
                    .addFormDataPart("longitude", String.valueOf(lng))
                    .addFormDataPart("latitude", String.valueOf(lat));
            /*如果是企业，则传企业名称*/
            if (isEnterprise ) {
                bodyBuilder.addFormDataPart("enterpriseName", companyName);
            }
            /*如果是导入，则传personId*/
            if (!TextUtils.isEmpty(personId)) {
                bodyBuilder.addFormDataPart("personId", personId);
            }
            List<Uri> uris = imgUriAdapter.getDate();
            for (int i = 0; i < uris.size(); i++) {
                File photoFile = FileUtil.getFileByUri(uris.get(i), this);
                bodyBuilder.addFormDataPart("rosterFile" + i, photoFile.getName(), RequestBody.create(MediaType.parse
                        ("image/*"), photoFile));
            }
            RequestBody requestBody = bodyBuilder.build();
            rosterAddPresenter.addRoster(requestBody);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    mSelectedUris = Matisse.obtainResult(data);
                    imgUriAdapter.addData(mSelectedUris);
                    break;
                case REQUEST_CODE_LOCATION:
                    lng = data.getDoubleExtra("lng", -1);
                    lat = data.getDoubleExtra("lat", -1);
                    setLocation(lng, lat);
                    break;
                case REQUEST_CODE_PERSON:
                    Person person = (Person) data.getSerializableExtra("person");
                    String realName = person.getRealName();
                    String mobilePhone = person.getMobilePhone().trim();
                    String idcard = person.getIdcard().trim();
                    setSwtichEnable(mobilePhone, setRosterPhone);
                    setSwtichEnable(realName, setRosterName);
                    setSwtichEnable(idcard, setRosterIdcard);
                    smbRosterGender.setSelectedTab(person.isGender() ? 0 : 1);
                    smbRosterGender.setOnTouchListener(null);
                    personId = person.getPersonId();
                    break;
            }
        }
    }

    private void setSwtichEnable(String mobilePhone, SuperShapeEditText editText) {
        if (!TextUtils.isEmpty(mobilePhone)) {
            editText.setText(mobilePhone);
            editText.getSuperManager().setStrokeColor(ContextCompat.getColor(this, R.color.transparent));
            editText.setEnabled(false);
        } else {
            editText.getSuperManager().setStrokeColor(ContextCompat.getColor(this, R.color.c_6));
            editText.setEnabled(true);
        }
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

    @Override
    protected void initNet() {

    }

    @Override
    public void onAddRosterSuccess() {
        EventBus.getDefault().post(new RefreshRostersEvent());
        DialogUtil.showQuitDialog(this, "花名册添加成功");
    }

}
