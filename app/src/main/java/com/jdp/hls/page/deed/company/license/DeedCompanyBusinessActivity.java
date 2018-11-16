package com.jdp.hls.page.deed.company.license;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseDeedActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.RefreshCertNumEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedCompanyLicense;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.page.personsearch.PersonSearchActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.kingja.supershapeview.view.SuperShapeTextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:营业执照-企业
 * Create Time:2018/9/10 0010 上午 10:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyBusinessActivity extends BaseDeedActivity implements DeedCompanyLicenseContract.View {

    @BindView(R.id.et_license_certNum)
    EnableEditText etLicenseCertNum;
    @BindView(R.id.et_license_realName)
    EnableEditText etLicenseRealName;
    @BindView(R.id.et_license_idcard)
    EnableEditText etLicenseIdcard;
    @BindView(R.id.et_license_mobilePhone)
    EnableEditText etLicenseMobilePhone;
    @Inject
    DeedCompanyLicensePresenter deedCompanyLicensePresenter;
    @BindView(R.id.set_person_import)
    SuperShapeTextView setPersonImport;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private boolean allowEdit;
    private String personId = "";
    private String certNum;
    private String realName;
    private String mobilePhone;
    private String idcard;
    private String remark;


    @OnClick({R.id.set_person_import})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.set_person_import:
                GoUtil.goActivityForResult(this, PersonSearchActivity.class, Constants.RequestCode.IMPORT_PERSON);
                break;
        }
    }

    @Override
    public void initVariable() {
        super.initVariable();
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_company_license;
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
        return "营业执照";
    }

    @Override
    protected void initView() {
        deedCompanyLicensePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    public void initNet() {
        if (mIsAdd) {
            setRightClick("保存", addListener);
        } else {
            //获取接口
            deedCompanyLicensePresenter.getDeedCompanyLicense(mBuildingId);
        }
    }

    private NoDoubleClickListener editListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyLicensePresenter.modifyDeedCompanyLicense(getRequestBody());
            }
        }
    };
    private NoDoubleClickListener addListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyLicensePresenter.addDeedCompanyLicense(getRequestBody());
            }
        }
    };

    @NonNull
    private RequestBody getRequestBody() {
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("LicenseNo", certNum)
                .addFormDataPart("PersonId", personId)
                .addFormDataPart("RealName", realName)
                .addFormDataPart("Idcard", idcard)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("MobilePhone", mobilePhone).build();
    }

    public boolean checkDataVaildable() {
        certNum = etLicenseCertNum.getText().toString().trim();
        realName = etLicenseRealName.getText().toString().trim();
        mobilePhone = etLicenseMobilePhone.getText().toString().trim();
        idcard = etLicenseIdcard.getText().toString().trim();
        remark = etRemark.getText().toString().trim();
        return CheckUtil.checkEmpty(certNum, "请输入证件号");
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", editListener);
        }
        etLicenseCertNum.setEnabled(allowEdit);
        etLicenseRealName.setEnabled(allowEdit);
        etLicenseIdcard.setEnabled(allowEdit);
        etLicenseMobilePhone.setEnabled(allowEdit);
        etRemark.setEnabled(allowEdit);
        setPersonImport.setVisibility(allowEdit ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.IMPORT_PERSON:
                    Person person = (Person) data.getSerializableExtra("person");
                    String realName = person.getRealName();
                    String mobilePhone = person.getMobilePhone().trim();
                    String idcard = person.getIdcard().trim();
                    setModifyEnable(mobilePhone, etLicenseMobilePhone);
                    setModifyEnable(realName, etLicenseRealName);
                    setModifyEnable(idcard, etLicenseIdcard);
                    personId = person.getPersonId();
                    break;
            }
        }
    }

    private void setModifyEnable(String value, EnableEditText editText) {
        editText.setText(value);
        editText.setEnabled(TextUtils.isEmpty(value));
    }

    public static void goActivity(Context context, String enterpriseId, boolean isAdd) {
        Intent intent = new Intent(context, DeedCompanyBusinessActivity.class);
        intent.putExtra("enterpriseId", enterpriseId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

    @Override
    public void onGetDeedCompanyLicenseSuccess(DeedCompanyLicense deedCompanyLicense) {
        etLicenseCertNum.setText(deedCompanyLicense.getLicenseNo());
        etLicenseRealName.setText(deedCompanyLicense.getRealName());
        etLicenseIdcard.setText(String.valueOf(deedCompanyLicense.getIdcard()));
        etLicenseMobilePhone.setText(String.valueOf(deedCompanyLicense.getMobilePhone()));
        etRemark.setText(String.valueOf(deedCompanyLicense.getRemark()));
        allowEdit = deedCompanyLicense.isAllowEdit();
        String personId = deedCompanyLicense.getPersonId();
        setEditable(allowEdit);
        rvPhotoPreview.setData(deedCompanyLicense.getFiles(), getFileConfig(), allowEdit);
    }

    @Override
    public void onAddDeedCompanyLicenseSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.COMPANY_DEED_BUSINESS, Status
                .BuildingType.COMPANY));
    }

    @Override
    public void onModifyDeedCompanyLicenseSuccess() {
        showSaveDeedSuccess(new RefreshCertNumEvent(certNum, Status.FileType.COMPANY_DEED_BUSINESS, Status
                .BuildingType.COMPANY));
    }

}
