package com.kingja.zhongminremove.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.kingja.zhongminremove.R;
import com.kingja.zhongminremove.base.BaseTitleActivity;
import com.kingja.zhongminremove.constant.Constants;
import com.kingja.zhongminremove.injector.component.AppComponent;
import com.kingja.zhongminremove.util.CheckUtil;
import com.kingja.zhongminremove.util.NoDoubleClickListener;
import com.kingja.zhongminremove.util.SimpleTextWatcher;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 4:41
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ModifyActivity extends BaseTitleActivity {

    @BindView(R.id.iv_modify_clear)
    ImageView ivModifyClear;
    @BindView(R.id.et_modify_value)
    EditText etModifyValue;
    @BindView(R.id.tv_modify_title)
    TextView tvModifyTitle;
    private String title;
    private String oldValue;
    private int requesCode;

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

    }

    @Override
    protected String getContentTitle() {
        return "修改";
    }

    @Override
    protected void initView() {

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
                if (CheckUtil.checkPhoneFormat(newVaule)) {
                    saveValue(newVaule);
                }
                break;
            case Constants.ModifyCode.MODIFY_ALIAS:
                if (CheckUtil.checkEmpty(newVaule, "请输入别名")) {
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

}
