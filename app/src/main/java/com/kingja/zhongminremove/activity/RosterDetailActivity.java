package com.kingja.zhongminremove.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.adapter.BaseRvAdaper;
import com.kingja.zhongminremove.adapter.ImgAdapter;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.constant.Constants;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.model.entiy.ImgInfo;
import com.kingja.zhongminremove.model.entiy.Roster;
import com.kingja.zhongminremove.util.NoDoubleClickListener;
import com.kingja.zhongminremove.view.RvItemDecoration;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/30 0030 上午 10:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class RosterDetailActivity extends BaseTitleActivity {
    @BindView(R.id.tv_roster_name)
    TextView tvRosterName;
    @BindView(R.id.ll_roster_name)
    LinearLayout llRosterName;
    @BindView(R.id.tv_roster_address)
    TextView tvRosterAddress;
    @BindView(R.id.ll_roster_address)
    LinearLayout llRosterAddress;
    @BindView(R.id.tv_roster_gender)
    TextView tvRosterGender;
    @BindView(R.id.ll_roster_gender)
    LinearLayout llRosterGender;
    @BindView(R.id.tv_roster_phone)
    TextView tvRosterPhone;
    @BindView(R.id.ll_roster_phone)
    LinearLayout llRosterPhone;
    @BindView(R.id.tv_roster_idcard)
    TextView tvRosterIdcard;
    @BindView(R.id.ll_roster_idcard)
    LinearLayout llRosterIdcard;
    @BindView(R.id.tv_roster_type)
    TextView tvRosterType;
    @BindView(R.id.ll_roster_type)
    LinearLayout llRosterType;
    @BindView(R.id.switch_roster_measure)
    Switch switchRosterMeasure;
    @BindView(R.id.switch_roster_assess)
    Switch switchRosterAssess;
    @BindView(R.id.rv_roster_img)
    RecyclerView rvRosterImg;
    @BindView(R.id.tv_roster_remark)
    TextView tvRosterRemark;
    @BindView(R.id.ll_roster_remark)
    LinearLayout llRosterRemark;
    @BindView(R.id.switch_roster_gender)
    Switch switchRosterGender;
    @BindView(R.id.ll_roster_img)
    LinearLayout llRosterImg;

    private List<ImgInfo> imgInfos = new ArrayList<>();

    @OnClick({R.id.ll_roster_name, R.id.ll_roster_address, R.id.ll_roster_idcard, R.id.ll_roster_phone, R.id
            .ll_roster_img})
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
                default:
                    break;

            }
        }

    }

    private boolean isAssessed;
    private boolean isMeasured;

    @OnCheckedChanged({R.id.switch_roster_gender, R.id.switch_roster_measure, R.id.switch_roster_assess})
    public void checkedChanged(CompoundButton compoundButton, boolean checked) {
        switch (compoundButton.getId()) {
            case R.id.switch_roster_gender:
                tvRosterGender.setText(checked ? "男" : "女");
                break;
            case R.id.switch_roster_measure:
                Logger.d("已丈量:" + checked);
                break;
            case R.id.switch_roster_assess:
                Logger.d("已评估:" + checked);
                break;
        }
    }

    @Override
    public void initVariable() {
        for (int i = 0; i < 10; i++) {
            imgInfos.add(new ImgInfo());
        }

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_roster_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "详情";
    }

    @Override
    protected void initView() {
        ImgAdapter visitorTabAdapter = new ImgAdapter(this, imgInfos);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        rvRosterImg.setLayoutManager(layoutManager);
        rvRosterImg.setAdapter(visitorTabAdapter);
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
    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Activity context, Roster roster) {
        Intent intent = new Intent(context, RosterDetailActivity.class);
        intent.putExtra("roster", roster);
        context.startActivity(intent);
        context.finish();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
