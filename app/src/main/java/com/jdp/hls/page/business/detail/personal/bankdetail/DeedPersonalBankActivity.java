package com.jdp.hls.page.business.detail.personal.bankdetail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddBankInfoEvent;
import com.jdp.hls.event.ModifyBankInfoEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.BankInfo;
import com.jdp.hls.model.entiy.DeedPersonalBank;
import com.jdp.hls.model.entiy.ImgInfo;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.FileUtil;
import com.jdp.hls.util.MatisseUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.AddableRecyclerView;
import com.jdp.hls.view.EnableEditText;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
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
    @BindView(R.id.rv_addable_photo_preview)
    AddableRecyclerView rvAddablePhotoPreview;
    private String remark;
    private String bankAccount;
    private String bankName;
    private String bankAccountName;
    private String id;
    private String houseId;

    @Override
    public void initVariable() {
        id = getIntent().getStringExtra(Constants.Extra.ID);
        houseId = getIntent().getStringExtra(Constants.Extra.HOUSEID);
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
            showSuccessCallback();
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedPersonalBankPresenter.getDeedPersonalBank(id);
        }
    }

    @NonNull
    private RequestBody getRequestBody() {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("Id", TextUtils.isEmpty(id) ? "" : id)
                .addFormDataPart("HouseId", TextUtils.isEmpty(houseId) ? "" : houseId)
                .addFormDataPart("BankAccountName", bankAccountName)
                .addFormDataPart("BankName", bankName)
                .addFormDataPart("BankAccount", bankAccount)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("DeleteFileIDs", TextUtils.isEmpty(rvAddablePhotoPreview.getDeleteImgIds()) ? "" :
                        rvAddablePhotoPreview.getDeleteImgIds());
        List<ImgInfo> imgInfos = rvAddablePhotoPreview.getDate();
        for (int i = 0; i < imgInfos.size(); i++) {
            Uri uri = imgInfos.get(i).getUri();
            if (uri != null) {
                File photoFile = FileUtil.getFileByUri(uri, this);
                builder.addFormDataPart("Files" + i, photoFile.getName(), RequestBody.create(MediaType
                        .parse("image/*"), photoFile));
            }
        }
        return builder.build();
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
        rvAddablePhotoPreview.setDate(deedPersonalBank.getFiles(), allowEdit);
    }

    @Override
    public void onAddDeedPersonalBankSuccess(BankInfo bankInfo) {
        EventBus.getDefault().post(new AddBankInfoEvent(bankInfo));
        showSuccessDialogAndFinish("保存成功");
    }

    @Override
    public void onModifyDeedPersonalBankSuccess(BankInfo bankInfo) {
        EventBus.getDefault().post(new ModifyBankInfoEvent(bankInfo));
        showSuccessDialogAndFinish("保存成功");
    }

    public static void goActivity(Context context, String id, String houseId) {
        Intent intent = new Intent(context, DeedPersonalBankActivity.class);
        intent.putExtra(Constants.Extra.ID, id);
        intent.putExtra(Constants.Extra.HOUSEID, houseId);
        context.startActivity(intent);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case MatisseUtil.REQUEST_CODE_CHOOSE:
                    rvAddablePhotoPreview.onPhotoCallback(requestCode, resultCode, data);
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
