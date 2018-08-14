package com.jdp.hls.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.adapter.ImgUriAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.fragment.LngLatFragment;
import com.jdp.hls.fragment.LocationFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.RvItemDecoration;
import com.kingja.supershapeview.view.SuperShapeEditText;
import com.tbruyelle.rxpermissions2.Permission;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Description:增加花名册
 * Create Time:2018/8/3 0003 下午 3:33
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterAddActivity extends BaseTitleActivity {
    @BindView(R.id.set_roster_name)
    SuperShapeEditText setRosterName;
    @BindView(R.id.ll_roster_name)
    LinearLayout llRosterName;
    @BindView(R.id.set_roster_address)
    SuperShapeEditText setRosterAddress;
    @BindView(R.id.ll_roster_address)
    LinearLayout llRosterAddress;
    @BindView(R.id.rb_roster_man)
    RadioButton rbRosterMan;
    @BindView(R.id.rb_roster_woman)
    RadioButton rbRosterWoman;
    @BindView(R.id.rg_roster_gender)
    RadioGroup rgRosterGender;
    @BindView(R.id.ll_roster_gender)
    LinearLayout llRosterGender;
    @BindView(R.id.set_roster_phone)
    SuperShapeEditText setRosterPhone;
    @BindView(R.id.ll_roster_phone)
    LinearLayout llRosterPhone;
    @BindView(R.id.set_roster_idcard)
    SuperShapeEditText setRosterIdcard;
    @BindView(R.id.ll_roster_idcard)
    LinearLayout llRosterIdcard;
    @BindView(R.id.rb_roster_personal)
    RadioButton rbRosterPersonal;
    @BindView(R.id.rb_roster_company)
    RadioButton rbRosterCompany;
    @BindView(R.id.rg_roster_type)
    RadioGroup rgRosterType;
    @BindView(R.id.ll_roster_type)
    LinearLayout llRosterType;
    @BindView(R.id.switch_roster_measured)
    Switch switchRosterMeasure;
    @BindView(R.id.switch_roster_evaluated)
    Switch switchRosterAssess;
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
    private List<Uri> photoUris = new ArrayList<>();
    private ImgUriAdapter imgUriAdapter;
    List<Uri> mSelectedUris;
    private static final int REQUEST_CODE_CHOOSE = 0;
    private static final int REQUEST_CODE_LOCATION = 1;
    private LocationFragment locationFragment;
    private LngLatFragment lngLatFragment;

    @OnClick({R.id.ll_roster_location,R.id.fragment_lnglat})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_roster_location:
            case R.id.fragment_lnglat:
                GoUtil.goActivityForResult(this, LocationActivity.class, REQUEST_CODE_LOCATION);
                break;
        }
    }


    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_roster_add;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "增加";
    }

    @Override
    protected void initView() {
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
                    ToastUtil.showText("添加图片" + position);
                    openCamera();
                } else {
                    ToastUtil.showText("点击放大" + position);
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
//        initMapFragment();
        checkPermissions();
        lngLatFragment = (LngLatFragment) getSupportFragmentManager().findFragmentById(R.id
                .fragment_lnglat);

    }

    private void initMapFragment() {
        if (locationFragment == null) {
            locationFragment = LocationFragment.newInstance();
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
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
        String name = setRosterName.getText().toString().trim();
        String address = setRosterAddress.getText().toString().trim();
        String phone = setRosterPhone.getText().toString().trim();
        String idcard = setRosterIdcard.getText().toString().trim();
        String remark = setRosterRemark.getText().toString().trim();
        if (CheckUtil.checkEmpty(name, "请输入户主姓名")
                && CheckUtil.checkEmpty(address, "请输入地址")
                && CheckUtil.checkPhoneFormatAllowEmpty(phone)
                && CheckUtil.checkIdCardAllowEmpty(idcard, "身份证格式错误")) {
            ToastUtil.showText("保存");
        }
    }

    private void openCamera() {
        Matisse.from(this)
                .choose(MimeType.allOf())
                .countable(true)
//                .capture(true)
                .theme(R.style.PhotoTheme)//主题  暗色主题 R.style.Matisse_Dracula
                .maxSelectable(9) // 图片选择的最多数量
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .thumbnailScale(0.85f) // 缩略图的比例
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .forResult(REQUEST_CODE_CHOOSE); // 设置作为标记的请求码
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case REQUEST_CODE_CHOOSE:
                    mSelectedUris = Matisse.obtainResult(data);
                    imgUriAdapter.addData(mSelectedUris);
                    break;
                case REQUEST_CODE_LOCATION:
                    double lng = data.getDoubleExtra("lng", -1);
                    double lat = data.getDoubleExtra("lat", -1);
                    Log.e(TAG, "lng: " + lng + " lat:" + lat);
                    lngLatFragment.setLnglat(lng, lat);
                    break;
            }
        }
    }

    @Override
    protected void initNet() {

    }

}
