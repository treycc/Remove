package com.jdp.hls.page.rosterdetail;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.activity.LocationActivity;
import com.jdp.hls.adapter.BaseRvPositionAdapter;
import com.jdp.hls.adapter.ImgAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.fragment.LngLatFragment;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.RosterDetail;
import com.jdp.hls.page.modify.ModifyActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.RvItemDecoration;
import com.zhihu.matisse.Matisse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import lib.kingja.switchbutton.SwitchMultiButton;

/**
 * Description:TODO
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
    @BindView(R.id.smb_roster_type)
    SwitchMultiButton smbRosterType;
    @BindView(R.id.smb_roster_measured)
    SwitchMultiButton smbRosterMeasured;
    @BindView(R.id.smb_roster_evaluated)
    SwitchMultiButton smbRosterEvaluated;
    private boolean isAssessed;
    private boolean isMeasured;
    private static final int REQUEST_CODE_LOCATION = 1;
    private List<ImgInfo> imgInfos = new ArrayList<>();
    private Roster roster;
    private LngLatFragment lngLatFragment;
    private ImgAdapter imgAdapter;
    List<Uri> mSelectedUris;
    @Inject
    RosterDetailPresenter rosterDetailPresenter;

    @OnClick({R.id.ll_roster_name, R.id.ll_roster_address, R.id.ll_roster_idcard, R.id.ll_roster_phone, R.id
            .ll_roster_img, R.id.ll_roster_location, R.id.fragment_lnglat})
    public void click(View view) {
        switch (view.getId()) {
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
            case R.id.ll_roster_img:
                break;
            case R.id.ll_roster_location:
            case R.id.fragment_lnglat:
                GoUtil.goActivityForResult(this, LocationActivity.class, REQUEST_CODE_LOCATION);
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            String newVaule = data.getStringExtra("newVaule");
            switch (requestCode) {
                case Constants.ModifyCode.MODIFY_OWNER_NAME:
                    tvRosterName.setText(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_ADDRESS:
                    tvRosterAddress.setText(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_PHONE:
                    tvRosterPhone.setText(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_IDCARD:
                    tvRosterIdcard.setText(newVaule);
                    break;
                case REQUEST_CODE_LOCATION:
                    double lng = data.getDoubleExtra("lng", -1);
                    double lat = data.getDoubleExtra("lat", -1);
                    Log.e(TAG, "lng: " + lng + " lat:" + lat);
                    lngLatFragment.setLnglat(lng, lat);
                    break;
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    mSelectedUris = Matisse.obtainResult(data);
                    imgAdapter.addData(getImgInfoFromUri(mSelectedUris));
                    break;
                default:
                    break;

            }
        }
    }

    private List<ImgInfo> getImgInfoFromUri(List<Uri> uris) {
        List<ImgInfo>imgInfos=new ArrayList<>();
        for (Uri uri : uris) {
            ImgInfo imgInfo = new ImgInfo();
            imgInfo.setUri(uri);
            imgInfos.add(imgInfo);
        }
        return imgInfos;
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
                12, 0x00ffffff));
    }

    private NoDoubleClickListener noDoubleClickListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {

        }
    };

    @Override
    protected void initData() {
//        setRightClick("保存", noDoubleClickListener );
        lngLatFragment = (LngLatFragment) getSupportFragmentManager().findFragmentById(R.id
                .fragment_lnglat);
        imgAdapter.setOnItemClickListener(new BaseRvPositionAdapter.OnItemClickListener<ImgInfo>() {
            @Override
            public void onItemClick(List<ImgInfo> list, int position) {
                if (imgAdapter.isLastItem(position)) {
                    ToastUtil.showText("添加图片" + position);
                    MatisseUtil.openCamera(RosterDetailActivity.this);
                } else {
                    ToastUtil.showText("点击放大" + position);
                }
            }
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
        tvRosterName.setText(rosterDetail.getRealName());
        tvRosterAddress.setText(rosterDetail.getHouseAddress());
        tvRosterPhone.setText(rosterDetail.getMobilePhone());
        tvRosterIdcard.setText(rosterDetail.getIdcard());
        tvRosterRemark.setText(rosterDetail.getRemark());
        smbRosterGender.setSelectedTab(rosterDetail.isGender() ? 0 : 1);
        smbRosterMeasured.setSelectedTab(rosterDetail.isMeasured() ? 0 : 1);
        smbRosterEvaluated.setSelectedTab(rosterDetail.isEvaluated() ? 0 : 1);
        lngLatFragment.setLnglat(rosterDetail.getLongitude(), rosterDetail.getLatitude());
        List<ImgInfo> houseFiles = rosterDetail.getHouseFiles();
        if (houseFiles != null && houseFiles.size() > 0) {
            imgAdapter.addData(houseFiles);
        }

    }

    @Override
    public void showLoading() {
        setProgressShow(true);
    }

    @Override
    public void hideLoading() {
        setProgressShow(false);
    }

}
