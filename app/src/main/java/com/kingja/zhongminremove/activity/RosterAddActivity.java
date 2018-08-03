package com.kingja.zhongminremove.activity;

import android.net.Uri;
import android.os.Bundle;
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

import com.kingja.supershapeview.view.SuperShapeEditText;
import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.adapter.BaseRvAdaper;
import com.kingja.zhongminremove.adapter.BaseRvPositionAdapter;
import com.kingja.zhongminremove.adapter.ImgAdapter;
import com.kingja.zhongminremove.adapter.ImgUriAdapter;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.model.entiy.ImgInfo;
import com.kingja.zhongminremove.util.CheckUtil;
import com.kingja.zhongminremove.util.NoDoubleClickListener;
import com.kingja.zhongminremove.util.ToastUtil;
import com.kingja.zhongminremove.view.RvItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:TODO
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
    @BindView(R.id.switch_roster_measure)
    Switch switchRosterMeasure;
    @BindView(R.id.switch_roster_assess)
    Switch switchRosterAssess;
    @BindView(R.id.rv_roster_img)
    RecyclerView rvRosterImg;
    @BindView(R.id.ll_roster_img)
    LinearLayout llRosterImg;
    @BindView(R.id.set_roster_remark)
    SuperShapeEditText setRosterRemark;
    @BindView(R.id.ll_roster_remark)
    LinearLayout llRosterRemark;
    private List<Uri> photoUris = new ArrayList<>();

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
        ImgUriAdapter imgUriAdapter = new ImgUriAdapter(this, photoUris);
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

    @Override
    protected void initNet() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
