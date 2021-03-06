package com.jdp.hls.page.mine;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.imgaeloader.ImageLoader;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.modify.ModifyAndUploadActivity;
import com.jdp.hls.page.server.ServerSelectorActivity;
import com.jdp.hls.page.setting.SettingActivity;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.kingja.supershapeview.view.SuperShapeImageView;
import com.zhihu.matisse.Matisse;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 2:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MineFragment extends BaseFragment implements MineContract.View {
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
    @BindView(R.id.iv_arrow_alias)
    ImageView ivArrowAlias;
    public static final int REQUST_PROJECTS = 8;
    @Inject
    MinePresenter minePresenter;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private long[] mHits = new long[5];
    private boolean debugMode;

    @OnClick({R.id.rl_mine_account, R.id.rl_mine_alias, R.id.rl_mine_phone, R.id
            .rl_mine_setting, R.id.ll_mine_service, R.id.ll_personal_head, R.id.tv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_mine_account:
                break;
            case R.id.rl_mine_alias:
                String alias = tvMineAlias.getText().toString().trim();
                ModifyAndUploadActivity.goActivityInFragment(this, Constants.ModifyCode.MODIFY_ALIAS, "别名", alias);
                break;
            case R.id.rl_mine_phone:
                String phone = tvMinePhone.getText().toString().trim();
                ModifyAndUploadActivity.goActivityInFragment(this, Constants.ModifyCode.MODIFY_PHONE, "手机号", phone);
                break;
            case R.id.rl_mine_setting:
                GoUtil.goActivity(getActivity(), SettingActivity.class);
                break;
            case R.id.ll_mine_service:
                callPhone(tvMineService.getText().toString().trim());
                break;
            case R.id.ll_personal_head:
                MatisseUtil.openCameraInFragment(this, Constants.SINGLE_IMG_UPLOAD_COUNT);
                break;
            case R.id.tv_title:
//                openDebug();
                break;
            default:
                break;
        }
    }

    private void openDebug() {
        System.arraycopy(mHits, 1, mHits, 0, mHits.length - 1);
        mHits[mHits.length - 1] = SystemClock.uptimeMillis();
        if (mHits[0] >= (SystemClock.uptimeMillis() - 1000)&&!debugMode) {
            debugMode=true;
            ToastUtil.showText("开启调试模式");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    GoUtil.goActivity(getActivity(), ServerSelectorActivity.class);
                }
            },300);

        }
    }

    @Override
    public void onStart() {
        super.onStart();
        debugMode=false;
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
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
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    List<Uri> selectedUris = Matisse.obtainResult(data);
                    if (selectedUris != null && selectedUris.size() > 0) {
                        ImageLoader.getInstance().loadImage(getActivity(), selectedUris.get(0), ivPersonalHead);
                        uploadHeadImg(selectedUris.get(0));
                    }
                    break;
                default:
                    break;
            }
        }

    }

    private void uploadHeadImg(Uri uri) {
        File headImgFile = FileUtil.getFileByUri(uri, getActivity());
        RequestBody body = RequestBody.create(MediaType.parse("image/jpg"), headImgFile);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("file", headImgFile.getName(), body);
        minePresenter.uploadHeadImg(photoPart);
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
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        minePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        tvMineRealName.setText(SpSir.getInstance().getRealName());
        tvMineCompanyName.setText(SpSir.getInstance().getCompanyName());
        tvMinePhone.setText(SpSir.getInstance().getMobilePhone());
        tvMineAccount.setText(String.valueOf(SpSir.getInstance().getAccountName()));
        String alias = SpSir.getInstance().getAccountAlias();
        ivArrowAlias.setVisibility(TextUtils.isEmpty(alias) ? View.VISIBLE : View.GONE);
        rlMineAlias.setEnabled(TextUtils.isEmpty(alias));
        tvMineAlias.setText(alias);
        String headUrl = SpSir.getInstance().getHeadUrl();
        if (!TextUtils.isEmpty(headUrl)) {
            ImageLoader.getInstance().loadImage(getActivity(), headUrl, ivPersonalHead);
        }
    }

    @Override
    public void initNet() {

    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_mine;
    }


    @Override
    public void onUploadHeadImgSuccess(String url) {
        SpSir.getInstance().setHeadUrl(url);
        ToastUtil.showText("头像上传成功");
    }

}
