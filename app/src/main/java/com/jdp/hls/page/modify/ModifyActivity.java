package com.jdp.hls.page.modify;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 4:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyActivity extends BaseTitleActivity implements ModifyContract.View {

    @BindView(R.id.iv_modify_clear)
    ImageView ivModifyClear;
    @BindView(R.id.et_modify_value)
    EditText etModifyValue;
    @BindView(R.id.tv_modify_title)
    TextView tvModifyTitle;
    private String title;
    private String oldValue;
    private int requesCode;

    @Inject
    ModifyPresenter modifyPresenter;

    @OnClick({R.id.iv_modify_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_modify_clear:
                etModifyValue.setText("");
                break;
        }
    }

    @Override
    public void initVariable() {
        title = getIntent().getStringExtra("title");
        oldValue = getIntent().getStringExtra("oldValue");
        requesCode = getIntent().getIntExtra("requesCode", 0);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_modify;
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
        return "修改";
    }

    @Override
    protected void initView() {
        modifyPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        setRightClick("确定", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                checkDateFormat();
            }
        });
        etModifyValue.setText(oldValue);
        tvModifyTitle.setText(title);
        etModifyValue.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                ivModifyClear.setVisibility(editable.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
        etModifyValue.setSelection(etModifyValue.getText().toString().trim().length());
    }

    private void checkDateFormat() {
        String newVaule = etModifyValue.getText().toString().trim();
        switch (requesCode) {
            case Constants.ModifyCode.MODIFY_PHONE:
                if (!TextUtils.isEmpty(newVaule)) {
                    if (CheckUtil.checkPhoneFormat(newVaule)) {
                        modifyPresenter.modifyMobile(SpSir.getInstance().getEmployeeId(),newVaule);
                    }
                } else {
                    modifyPresenter.modifyMobile(SpSir.getInstance().getEmployeeId(),newVaule);
                }
                break;
            case Constants.ModifyCode.MODIFY_OWNER_NAME:
                if (CheckUtil.checkEmpty(newVaule, "请输入" + title)) {
                    saveValue(newVaule);
                }
                break;
            case Constants.ModifyCode.MODIFY_ALIAS:
                if (CheckUtil.checkEmpty(newVaule, "请输入" + title)) {
                    modifyPresenter.modifyAlias(SpSir.getInstance().getEmployeeId(),newVaule);
                }
                break;
            case Constants.ModifyCode.MODIFY_ADDRESS:
                if (CheckUtil.checkEmpty(newVaule, "请输入" + title)) {
                    saveValue(newVaule);
                }
                break;
            case Constants.ModifyCode.MODIFY_IDCARD:
                if (!TextUtils.isEmpty(newVaule)) {
                    if (CheckUtil.checkIdCard(newVaule, "身份证格式有误")) {
                        saveValue(newVaule);
                    }
                } else {
                    saveValue(newVaule);
                }
                break;
            default:
                break;

        }
    }

    private void saveValue(String newValue) {
        Intent intent = new Intent();
        intent.putExtra("newVaule", newValue);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void initNet() {

    }

    public static void goActivityInActivity(Activity activity, int requesCode, String title, String oldValue) {
        Intent intent = new Intent(activity, ModifyActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("oldValue", oldValue);
        intent.putExtra("requesCode", requesCode);
        activity.startActivityForResult(intent, requesCode);
    }

    public static void goActivityInFragment(Fragment fragment, int requesCode, String title, String oldValue) {
        Intent intent = new Intent(fragment.getActivity(), ModifyActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("oldValue", oldValue);
        intent.putExtra("requesCode", requesCode);
        fragment.startActivityForResult(intent, requesCode);
    }

    @Override
    public void onModifyAliasSuccess(String aliasName) {
        SpSir.getInstance().setAccountAlias(aliasName);
        saveValue(aliasName);
    }

    @Override
    public void onModifyMobileSuccess(String mobile) {
        SpSir.getInstance().setMobilePhone(mobile);
        saveValue(mobile);
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
