package com.jdp.hls.page.node.protocol.personal.lastst.pay.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AddPayEvent;
import com.jdp.hls.model.entiy.ModifyPayEvent;
import com.jdp.hls.model.entiy.PayItem;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.page.node.protocol.personal.lastst.pay.banklist.ReceiveAccountListActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import lib.kingja.switchbutton.SwitchMultiButton;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/17 0017 上午 10:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PayDetailActivity extends BaseTitleActivity implements PayDetailContract.View {

    @BindView(R.id.spinner_type)
    KSpinner spinnerType;
    @BindView(R.id.tv_payDate)
    StringTextView tvPayDate;
    @BindView(R.id.et_amount)
    EnableEditText etAmount;
    @BindView(R.id.tv_limitStartDate)
    StringTextView tvLimitStartDate;
    @BindView(R.id.tv_limitEndDate)
    StringTextView tvLimitEndDate;
    @BindView(R.id.smb_isDouble)
    Switch smbIsDouble;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.rl_photo_preview)
    RelativeLayout rlPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.iv_payDate)
    ImageView ivPayDate;
    @BindView(R.id.iv_limitStartDate)
    ImageView ivLimitStartDate;
    @BindView(R.id.iv_limitEndDate)
    ImageView ivLimitEndDate;
    @BindView(R.id.ll_tempPlacementFee)
    LinearLayout llTempPlacementFee;
    @BindView(R.id.tv_receiveAccount)
    StringTextView tvReceiveAccount;
    @BindView(R.id.iv_arrow_receiveAccount)
    ImageView ivArrowReceiveAccount;
    @BindView(R.id.ll_receiveAccount)
    LinearLayout llReceiveAccount;
    //id为0为增加，否则为修改
    private int id;
    @Inject
    PayDetailPresenter payDetailPresenter;
    private List<TDict> payTypeList;
    private int payType;
    private boolean isAllowEdit;
    private String buildingId;
    private String buildingType;
    private boolean isDouble = true;
    private TimePickerDialog startDateSelector;
    private TimePickerDialog endDateSelector;
    private TimePickerDialog payDateSelector;
    private String limitStartDate;
    private String limitEndDate;
    private String payDate;
    private int recBankAccountId = -1;

    @OnClick({R.id.iv_payDate, R.id.iv_limitStartDate, R.id.iv_limitEndDate, R.id.ll_receiveAccount})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_payDate:
                payDateSelector = new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(this, R.color.main))
                        .setWheelItemTextSize(15)
                        .setCurrentMillseconds(!TextUtils.isEmpty(payDate) ? DateUtil.getMillSeconds
                                (payDate, "yyyy-MM-dd") : System.currentTimeMillis())
                        .setTitleStringId("请选择支付日期")
                        .setCallBack((timePickerView, millseconds) -> {
                            payDate = DateUtil.getDateString(millseconds);
                            tvPayDate.setText(payDate);
                        })
                        .build();
                payDateSelector.show(this.getSupportFragmentManager(), String.valueOf(ivPayDate.hashCode
                        ()));
                break;
            case R.id.iv_limitStartDate:
                startDateSelector = new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(this, R.color.main))
                        .setWheelItemTextSize(15)
                        .setCurrentMillseconds(!TextUtils.isEmpty(limitStartDate) ? DateUtil.getMillSeconds
                                (limitStartDate,
                                        "yyyy-MM-dd") : System.currentTimeMillis())
                        .setMaxMillseconds(!TextUtils.isEmpty(limitEndDate) ? DateUtil.getMillSeconds(limitEndDate,
                                "yyyy-MM-dd") : System.currentTimeMillis())
                        .setTitleStringId("请选择开始时限")
                        .setCallBack((timePickerView, millseconds) -> {
                            limitStartDate = DateUtil.getDateString(millseconds);
                            tvLimitStartDate.setText(limitStartDate);
                        })
                        .build();
                startDateSelector.show(this.getSupportFragmentManager(), String.valueOf(ivLimitStartDate.hashCode
                        ()));
                break;
            case R.id.iv_limitEndDate:
                endDateSelector = new TimePickerDialog.Builder()
                        .setType(Type.YEAR_MONTH_DAY)
                        .setThemeColor(ContextCompat.getColor(this, R.color.main))
                        .setWheelItemTextSize(15)
                        .setTitleStringId("请选择结束时限")
                        .setMinMillseconds(!TextUtils.isEmpty(limitStartDate) ? DateUtil.getMillSeconds(limitStartDate,
                                "yyyy-MM-dd") : System.currentTimeMillis())
                        .setCurrentMillseconds(!TextUtils.isEmpty(limitEndDate) ? DateUtil.getMillSeconds(limitEndDate,
                                "yyyy-MM-dd") : (!TextUtils.isEmpty(limitStartDate) ? DateUtil.getMillSeconds
                                (limitStartDate, "yyyy-MM-dd") : System.currentTimeMillis()))
                        .setCallBack((timePickerView, millseconds) -> {
                            limitEndDate = DateUtil.getDateString(millseconds);
                            tvLimitEndDate.setText(limitEndDate);
                        })
                        .build();
                endDateSelector.show(this.getSupportFragmentManager(), String.valueOf(ivLimitEndDate.hashCode()));
                break;
            case R.id.ll_receiveAccount:
                ReceiveAccountListActivity.goActivity(this, buildingId);
                break;
        }
    }

    @Override
    public void initVariable() {
        id = getIntent().getIntExtra(Constants.Extra.ID, 0);
        isAllowEdit = getIntent().getBooleanExtra(Constants.Extra.EDITABLE, false);
        payTypeList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.PAY_TYPE);
        buildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
        buildingType = getIntent().getStringExtra(Constants.Extra.BUILDING_TYPE);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_pay_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        payDetailPresenter.attachView(this);

    }

    @Override
    protected String getContentTitle() {
        return "明细";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        rvPhotoPreview.setData(null, new FileConfig(Status.FileType.PAY_DETAIL, buildingId,
                buildingType), isAllowEdit);
        smbIsDouble.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isDouble = isChecked;
        });
        spinnerType.setDicts(payTypeList, typeId -> {
            payType = typeId;
            llTempPlacementFee.setVisibility(payType == Status.PayTypeItem.TempPlacementFee ? View.VISIBLE : View.GONE);
        });
        payType = spinnerType.getDefaultTypeId();
        llTempPlacementFee.setVisibility(payType == Status.PayTypeItem.TempPlacementFee ? View.VISIBLE : View.GONE);
        if (isAllowEdit) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    doSave();
                }
            });
        }


    }

    private void doSave() {
        String amount = etAmount.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        payDate = tvPayDate.getText().toString().trim();
        limitStartDate = tvLimitStartDate.getText().toString().trim();
        limitEndDate = tvLimitEndDate.getText().toString().trim();
        if (CheckUtil.checkEmpty(payDate, "请选择支付日期") && CheckUtil.checkEmpty(amount, "请输入支付金额")) {
            payDetailPresenter.savePay(new MultipartBody.Builder().setType(MultipartBody.FORM)
                    .addFormDataPart("Id", String.valueOf(id))
                    .addFormDataPart("RecBankAccountId", String.valueOf(recBankAccountId))
                    .addFormDataPart("BuildingId", buildingId)
                    .addFormDataPart("BuildingType", buildingType)
                    .addFormDataPart("PayDate", payDate)
                    .addFormDataPart("Amount", amount)
                    .addFormDataPart("Remark", remark)
                    .addFormDataPart("UseItemId", String.valueOf(payType))
                    .addFormDataPart("IsDouble", String.valueOf(isDouble))
                    .addFormDataPart("LimitStartDate", limitStartDate)
                    .addFormDataPart("LimitEndDate", limitEndDate)
                    .build());
        }
    }

    @Override
    public void initNet() {
        if (id > 0) {
            //修改
            payDetailPresenter.getPayDetail(id);
        } else {
            //创建
            showSuccessCallback();
        }
    }

    public static void goActivity(Context context, int id, String buildingId, String buildingType, boolean
            isAllowEdit) {
        Intent intent = new Intent(context, PayDetailActivity.class);
        intent.putExtra(Constants.Extra.ID, id);
        intent.putExtra(Constants.Extra.EDITABLE, isAllowEdit);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        context.startActivity(intent);
    }

    @Override
    public void onGetPayDetailSuccess(PayItem payItem) {
        payType = payItem.getUseItemId();
        llTempPlacementFee.setVisibility(payType == Status.PayTypeItem.TempPlacementFee ? View.VISIBLE : View.GONE);
        spinnerType.setSelectItem(payType);
        payDate = payItem.getPayDate();
        limitStartDate = payItem.getLimitStartDate();
        limitEndDate = payItem.getLimitEndDate();
        tvPayDate.setString(payDate);
        etAmount.setString(payItem.getAmount());
        tvLimitStartDate.setString(limitStartDate);
        tvLimitEndDate.setString(limitEndDate);
        smbIsDouble.setChecked(payItem.isIsDouble());
        etRemark.setString(payItem.getRemark());
        recBankAccountId = payItem.getRecBankAccountId();
        tvReceiveAccount.setString(payItem.getBankAccountName() + "" + payItem.getBankAccount());

        rvPhotoPreview.setData(payItem.getFiles(), new FileConfig(Status.FileType.PAY_DETAIL, buildingId,
                buildingType,String.valueOf(payItem.getId())), isAllowEdit);
        etAmount.setEnabled(isAllowEdit);
        etRemark.setEnabled(isAllowEdit);
        spinnerType.enable(isAllowEdit);
        smbIsDouble.setEnabled(isAllowEdit);

        llReceiveAccount.setClickable(isAllowEdit);
        ivArrowReceiveAccount.setVisibility(isAllowEdit ? View.VISIBLE : View.GONE);

        ivPayDate.setVisibility(isAllowEdit ? View.VISIBLE : View.GONE);
        ivLimitStartDate.setVisibility(isAllowEdit ? View.VISIBLE : View.GONE);
        ivLimitEndDate.setVisibility(isAllowEdit ? View.VISIBLE : View.GONE);


    }

    @Override
    public void onSavePaySuccess(PayItem payItem) {
        if (id > 0) {
            //修改
            EventBus.getDefault().post(new ModifyPayEvent(payItem));
        } else {
            //添加
            EventBus.getDefault().post(new AddPayEvent(payItem));
        }
        showSuccessDialogAndFinish();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        rvPhotoPreview.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.ReceiveAccount:
                    String receiveAccount = data.getStringExtra(Constants.Extra.ReceiveAccount);
                    recBankAccountId = data.getIntExtra(Constants.Extra.RecBankAccountId, -1);
                    tvReceiveAccount.setString(receiveAccount);
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
