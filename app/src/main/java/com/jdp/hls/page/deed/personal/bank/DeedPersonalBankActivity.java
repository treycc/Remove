package com.jdp.hls.page.deed.personal.bank;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.RefreshCertNumEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedPersonalBank;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.page.business.detail.personal.branklist.BrankListActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/11/5 0005 下午 2:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedPersonalBankActivity extends BaseTitleActivity implements DeedPersonalBankContract.View {
    @BindView(R.id.et_bankAccountName)
    EnableEditText etBankAccountName;
    @BindView(R.id.et_bankName)
    EnableEditText etBankName;
    @BindView(R.id.et_bankAccount)
    EnableEditText etBankAccount;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @Inject
    DeedPersonalBankPresenter deedPersonalBankPresenter;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    private String remark;
    private String bankAccount;
    private String bankName;
    private String bankAccountName;
    private String id;

    @Override
    public void initVariable() {
        id = getIntent().getStringExtra(Constants.Extra.ID);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_personal_bank;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        deedPersonalBankPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "银行信息";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void initNet() {
        if (TextUtils.isEmpty(id)) {
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedPersonalBankPresenter.getDeedPersonalBank(id);
        }
    }

    @NonNull
    private RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", "")
                .addFormDataPart("BankAccountName", bankAccountName)
                .addFormDataPart("BankName", bankName)
                .addFormDataPart("BankAccount", bankAccount)
                .addFormDataPart("Remark", remark)
                .build();
    }

    public boolean checkDataVaildable() {
        bankAccountName = etBankAccountName.getText().toString().trim();
        bankName = etBankName.getText().toString().trim();
        bankAccount = etBankAccount.getText().toString().trim();
        remark = etRemark.getText().toString().trim();
        return CheckUtil.checkEmpty(bankAccountName, "请输入姓名") && CheckUtil.checkEmpty(bankName, "请输入开户银行") &&
                CheckUtil.checkEmpty(bankAccount, "请输入银行账号");
    }


    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        }
        etBankAccount.setEnabled(allowEdit);
        etBankName.setEnabled(allowEdit);
        etBankAccountName.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);

    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedPersonalBankPresenter.modifyDeedPersonalBank(getRequestBody());
            }

        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedPersonalBankPresenter.addDeedPersonalBank(getRequestBody());
            }
        }
    };

    @Override
    public void onGetDeedPersonalBankSuccess(DeedPersonalBank deedPersonalBank) {
        etBankAccount.setText(deedPersonalBank.getBankAccount());
        etBankName.setText(deedPersonalBank.getBankName());
        etBankAccountName.setText(deedPersonalBank.getBankAccountName());
        etRemark.setText(deedPersonalBank.getRemark());
        boolean allowEdit = deedPersonalBank.isAllowEdit();
        setEditable(allowEdit);
//        rvPhotoPreview.setData(deedPersonalBank.getFiles(), new FileConfig(Status.FileType.BANK,), allowEdit);
    }

    @Override
    public void onAddDeedPersonalBankSuccess() {

    }

    @Override
    public void onModifyDeedPersonalBankSuccess() {

    }

    public static void goActivity(Context context, String id) {
        Intent intent = new Intent(context, DeedPersonalBankActivity.class);
        intent.putExtra(Constants.Extra.ID, id);
        context.startActivity(intent);
    }
}
