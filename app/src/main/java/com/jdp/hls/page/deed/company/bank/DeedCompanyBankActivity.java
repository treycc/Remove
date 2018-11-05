package com.jdp.hls.page.deed.company.bank;

import android.support.annotation.NonNull;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseDeedActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.RefreshCertNumEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedCompanyOpenAccountCert;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/11/5 0005 下午 2:52
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyBankActivity extends BaseDeedActivity implements DeedCompanyOpenAccountCertContract.View {
    @Inject
    DeedCompanyOpenAccountCertPresenter deedCompanyOpenAccountCertPresenter;
    @BindView(R.id.tv_EnterpriseName)
    StringTextView tvEnterpriseName;
    @BindView(R.id.tv_land_juridicalPersonName)
    StringTextView tvLandJuridicalPersonName;
    @BindView(R.id.et_BankName)
    EnableEditText etBankName;
    @BindView(R.id.et_bankAccount)
    EnableEditText etBankAccount;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private String bankAccount;
    private String bankName;
    private String remark;

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_company_bank;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        deedCompanyOpenAccountCertPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "开户许可证";
    }

    @Override
    protected void initView() {

    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        }
        etBankName.setEnabled(allowEdit);
        etBankAccount.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
    }

    @Override
    protected void initNet() {
        if (mIsAdd) {
            setRightClick("保存", addListener);
        } else {
            deedCompanyOpenAccountCertPresenter.getDeedCompanyOpenAccountCert(mBuildingId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyOpenAccountCertPresenter.modifyDeedCompanyOpenAccountCert(getRequestBody());
            }
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyOpenAccountCertPresenter.addDeedCompanyOpenAccountCert(getRequestBody());
            }
        }
    };

    public boolean checkDataVaildable() {
        bankAccount = etBankAccount.getText().toString().trim();
        bankName = etBankName.getText().toString().trim();
        remark = etRemark.getText().toString().trim();
        return CheckUtil.checkEmpty(bankName, "请输入开户银行") && CheckUtil.checkEmpty(bankAccount, "请输入银行账号");
    }

    @NonNull
    private RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("BankName", bankName)
                .addFormDataPart("BankAccount", bankAccount)
                .addFormDataPart("Remark", remark)
                .build();
    }

    @Override
    public void onGetDeedCompanyOpenAccountCertSuccess(DeedCompanyOpenAccountCert deedCompanyOpenAccountCert) {
        tvEnterpriseName.setText(deedCompanyOpenAccountCert.getEnterpriseName());
        tvLandJuridicalPersonName.setText(deedCompanyOpenAccountCert.getJuridicalPersonName());
        etBankName.setText(deedCompanyOpenAccountCert.getBankName());
        etBankAccount.setText(deedCompanyOpenAccountCert.getBankAccount());
        etRemark.setText(deedCompanyOpenAccountCert.getRemark());
        boolean allowEdit = deedCompanyOpenAccountCert.isAllowEdit();
        setEditable(allowEdit);
        rvPhotoPreview.setData(deedCompanyOpenAccountCert.getFiles(), getFileConfig(), allowEdit);
    }

    @Override
    public void onAddDeedCompanyOpenAccountCertSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(bankAccount, Status.FileType.BANK, Status.BuildingType.COMPANY));
    }

    @Override
    public void onModifyDeedCompanyOpenAccountCertSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(bankAccount, Status.FileType.BANK, Status.BuildingType.COMPANY));
    }

}
