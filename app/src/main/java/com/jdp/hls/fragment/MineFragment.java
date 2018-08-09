package com.jdp.hls.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.activity.ModifyActivity;
import com.jdp.hls.activity.ProjectListActivity;
import com.jdp.hls.activity.SettingActivity;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.GoUtil;
import com.kingja.supershapeview.view.SuperShapeImageView;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment {
    @BindView(R.id.iv_personal_head)
    SuperShapeImageView ivPersonalHead;
    @BindView(R.id.tv_mine_account)
    TextView tvMineAccount;
    @BindView(R.id.rl_mine_account)
    LinearLayout rlMineAccount;
    @BindView(R.id.tv_mine_alias)
    TextView tvMineAlias;
    @BindView(R.id.rl_mine_alias)
    LinearLayout rlMineAlias;
    @BindView(R.id.tv_mine_project)
    TextView tvMineProject;
    @BindView(R.id.rl_mine_project)
    LinearLayout rlMineProject;
    @BindView(R.id.tv_mine_phone)
    TextView tvMinePhone;
    @BindView(R.id.rl_mine_phone)
    LinearLayout rlMinePhone;
    @BindView(R.id.rl_mine_setting)
    RelativeLayout rlMineSetting;
    @BindView(R.id.rl_mine_service)
    RelativeLayout rlMineService;
    Unbinder unbinder;

    @OnClick({R.id.rl_mine_account, R.id.rl_mine_alias, R.id.rl_mine_project, R.id.rl_mine_phone, R.id
            .rl_mine_setting, R.id.rl_mine_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_account:
                break;
            case R.id.rl_mine_alias:
                String alias = tvMineAlias.getText().toString().trim();
                ModifyActivity.goActivityInFragment(this, Constants.ModifyCode.MODIFY_ALIAS, "别名", alias);
                break;
            case R.id.rl_mine_project:
                GoUtil.goActivity(getActivity(), ProjectListActivity.class);
                break;
            case R.id.rl_mine_phone:
                String phone = tvMinePhone.getText().toString().trim();
                ModifyActivity.goActivityInFragment(this, Constants.ModifyCode.MODIFY_PHONE, "手机号", phone);
                break;
            case R.id.rl_mine_setting:
                GoUtil.goActivity(getActivity(), SettingActivity.class);

                break;
            case R.id.rl_mine_service:
                break;

            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            String newVaule = data.getStringExtra("newVaule");
            switch (requestCode) {
                case Constants.ModifyCode.MODIFY_ALIAS:
                    tvMineAlias.setText(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_PHONE:
                    tvMinePhone.setText(newVaule);
                    break;
                default:
                    break;

            }
        }

    }

    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        Bundle args = new Bundle();
        args.putString("param", null);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            String param = getArguments().getString("param");
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initDate() {

    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_mine;
    }

}
