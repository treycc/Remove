package com.jdp.hls.fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.page.modify.ModifyActivity;
import com.jdp.hls.page.projects.ProjectListActivity;
import com.jdp.hls.activity.SettingActivity;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SpSir;
import com.kingja.supershapeview.view.SuperShapeImageView;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.tv_mine_realName)
    TextView tvMineRealName;
    @BindView(R.id.tv_mine_companyName)
    TextView tvMineCompanyName;
    @BindView(R.id.tv_mine_service)
    TextView tvMineService;
    public static final int REQUST_PROJECTS=8;

    @OnClick({R.id.rl_mine_account, R.id.rl_mine_alias, R.id.rl_mine_project, R.id.rl_mine_phone, R.id
            .rl_mine_setting, R.id.ll_mine_service})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_account:
                break;
            case R.id.rl_mine_alias:
                String alias = tvMineAlias.getText().toString().trim();
                ModifyActivity.goActivityInFragment(this, Constants.ModifyCode.MODIFY_ALIAS, "别名", alias);
                break;
            case R.id.rl_mine_project:
                GoUtil.goActivityForResultInFragment(this, ProjectListActivity.class,REQUST_PROJECTS);
                break;
            case R.id.rl_mine_phone:
                String phone = tvMinePhone.getText().toString().trim();
                ModifyActivity.goActivityInFragment(this, Constants.ModifyCode.MODIFY_PHONE, "手机号", phone);
                break;
            case R.id.rl_mine_setting:
                GoUtil.goActivity(getActivity(), SettingActivity.class);
                break;
            case R.id.ll_mine_service:
                callPhone(tvMineService.getText().toString().trim());
                break;
            default:
                break;
        }
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        LogUtil.e(TAG,"onActivityResult requestCode:"+requestCode);
        if (resultCode == Activity.RESULT_OK && data != null) {
            String newVaule = data.getStringExtra("newVaule");
            switch (requestCode) {
                case Constants.ModifyCode.MODIFY_ALIAS:
                    tvMineAlias.setText(newVaule);
                    break;
                case Constants.ModifyCode.MODIFY_PHONE:
                    tvMinePhone.setText(newVaule);
                    break;
                case REQUST_PROJECTS:
                    LogUtil.e(TAG,"projectName:"+data.getStringExtra("projectName"));
                    tvMineProject.setText(data.getStringExtra("projectName"));
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
        tvMineRealName.setText(SpSir.getInstance().getRealName());
        tvMineCompanyName.setText(SpSir.getInstance().getCompanyName());
        tvMinePhone.setText(SpSir.getInstance().getMobilePhone());
        tvMineAccount.setText(String.valueOf(SpSir.getInstance().getAccountName()));
        tvMineAlias.setText(SpSir.getInstance().getAccountAlias());
        tvMineProject.setText(SpSir.getInstance().getProjectName());
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_mine;
    }



}
