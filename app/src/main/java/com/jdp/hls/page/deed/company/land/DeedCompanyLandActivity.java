package com.jdp.hls.page.deed.company.land;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseDeedMulActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.DeedCompanyLand;
import com.jdp.hls.model.entiy.DeedItem;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.MathUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.StringTextView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:土地证-企业
 * Create Time:2018/9/10 0010 上午 10:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DeedCompanyLandActivity extends BaseDeedMulActivity implements DeedCompanyLandContract.View {
    @BindView(R.id.spinner_landType)
    KSpinner spinnerLandType;
    @BindView(R.id.et_land_certNum)
    EnableEditText etLandCertNum;
    @BindView(R.id.et_land_area)
    EnableEditText etLandArea;
    @BindView(R.id.tv_land_mu)
    StringTextView tvLandMu;
    @BindView(R.id.et_land_address)
    EnableEditText etLandAddress;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.et_land_landUse)
    EnableEditText etLandLandUse;
    @BindView(R.id.tv_landOutExpiryDate)
    TextView tvLandOutExpiryDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    private List<TDict> landTypeList;
    private int landTypeId;
    @Inject
    DeedCompanyLandPresenter deedCompanyLandPresenter;
    private String certNum;
    private String area;
    private String address;
    private String remark;
    private String landUse;
    private String landOutExpiryDate;

    @Override
    public void initVariable() {
        super.initVariable();
        landTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.LAND_TYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_deed_company_land;
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
        return "土地证";
    }

    @Override
    protected void initView() {
        deedCompanyLandPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
        spinnerLandType.setDicts(landTypeList, typeId -> {
            landTypeId = typeId;
        });
        landTypeId = spinnerLandType.getDefaultTypeId();
        etLandArea.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                calculate();

            }
        });
    }

    private void calculate() {
        area = etLandArea.getText().toString().trim();
        double areaStr = TextUtils.isEmpty(area) ? 0d : Double.valueOf(area);
        tvLandMu.setString(String.format("%.4f", MathUtil.div(areaStr, 666.66d)));
    }

    @Override
    public void initNet() {
        if (mIsAdd) {
            setRightClick("保存", saveListener);
            setDateSelector(ivDateSelector, tvLandOutExpiryDate, mIsAdd);
        } else {
            deedCompanyLandPresenter.getDeedCompanyLandDetail(mCertId);
        }

    }
    private NoDoubleClickListener saveListener = new NoDoubleClickListener() {
        @Override
        public void onNoDoubleClick(View v) {
            if (checkDataVaildable()) {
                deedCompanyLandPresenter.saveDeedCompanyLand(getRequestBody());
            }
        }
    };

    public boolean checkDataVaildable() {
        certNum = etLandCertNum.getText().toString().trim();
        area = etLandArea.getText().toString().trim();
        remark = etRemark.getText().toString().trim();
        address = etLandAddress.getText().toString().trim();
        landUse = etLandLandUse.getText().toString().trim();
        landOutExpiryDate = tvLandOutExpiryDate.getText().toString().trim();
        return CheckUtil.checkEmpty(certNum, "请输入证件号")
                && CheckUtil.checkEmpty(area, "请输入土地面积")
                && CheckUtil.checkEmpty(address, "请输入地址");
    }

    @NonNull
    private RequestBody getRequestBody() {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("CertNum", certNum)
                .addFormDataPart("Id", String.valueOf(mCertId))
                .addFormDataPart("LandNatureTypeId", String.valueOf(landTypeId))
                .addFormDataPart("LandUse", landUse)
                .addFormDataPart("Area", area)
                .addFormDataPart("LandOutExpiryDate", landOutExpiryDate)
                .addFormDataPart("Remark", remark)
                .addFormDataPart("Address", address)
                .addFormDataPart("deleteFileIDs", TextUtils.isEmpty
                        (rvAddablePhotoPreview.getDeleteImgIds()) ? "" :
                        rvAddablePhotoPreview.getDeleteImgIds());
        return rvAddablePhotoPreview.getValidData(builder).build();
    }

    private void setEditable(boolean allowEdit) {
        if (allowEdit) {
            setRightClick("保存", saveListener);
        }
        etLandCertNum.setEnabled(allowEdit);
        etLandArea.setEnabled(allowEdit);
        etLandAddress.setEnabled(allowEdit);
        spinnerLandType.enable(allowEdit);
        etRemark.setEnabled(allowEdit);
        etLandLandUse.setEnabled(allowEdit);
        setDateSelector(ivDateSelector, tvLandOutExpiryDate, allowEdit);
    }

    protected void setDateSelector(ImageView ivDate, TextView tvDate, boolean allowEdit) {
        ivDate.setVisibility(allowEdit ? View.VISIBLE : View.GONE);
        if (!allowEdit) {
            return;
        }
        ivDate.setOnClickListener(new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(DeedCompanyLandActivity.this, R.color.main))
                        .setWheelItemTextSize(15)
                        .setTitleStringId("请选择时间")
                        .setCallBack((timePickerView, millseconds) -> {
                            tvDate.setText(DateUtil.getDateString(millseconds));
                        })
                        .build().show(getSupportFragmentManager(), String.valueOf(ivDate.hashCode()));
            }
        });
    }

    public static void goActivity(Context context, String enterpriseId, boolean isAdd) {
        Intent intent = new Intent(context, DeedCompanyLandActivity.class);
        intent.putExtra("enterpriseId", enterpriseId);
        intent.putExtra("isAdd", isAdd);
        context.startActivity(intent);
    }

    @Override
    public void onGetDeedCompanyLandSuccess(DeedCompanyLand deedCompanyLand) {
        etLandCertNum.setText(deedCompanyLand.getCertNum());
        etLandArea.setText(String.valueOf(deedCompanyLand.getArea()));
        etLandAddress.setText(deedCompanyLand.getAddress());
        etRemark.setText(deedCompanyLand.getRemark());
        etLandLandUse.setText(deedCompanyLand.getLandUse());
        tvLandOutExpiryDate.setText(deedCompanyLand.getLandOutExpiryDate());
        landTypeId = deedCompanyLand.getLandNatureTypeId();
        spinnerLandType.setSelectItem(landTypeId);
        boolean allowEdit = deedCompanyLand.isAllowEdit();
        setEditable(allowEdit);
        rvAddablePhotoPreview.setDate(deedCompanyLand.getFiles(), deedCompanyLand.isAllowEdit());
        calculate();
    }

    @Override
    public void onSaveDeedCompanyLandSuccess(DeedItem deedItem) {
        refreshDeedList(deedItem);
    }

}
