package com.jdp.hls.page.node.age.company;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyAge;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;
import com.jdp.hls.view.StringTextView;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:年限鉴定-企业
 * Create Time:2018/9/11 0011 下午 3:20
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeCompanyAgeActivity extends BaseNodeActivity implements NodeCompanyAgeContract.View {
    @Inject
    NodeCompanyAgePresenter nodeCompanyAgePresenter;
    @BindView(R.id.tv_age_realName)
    StringTextView tvAgeRealName;
    @BindView(R.id.tv_age_companyName)
    StringTextView tvAgeCompanyName;
    @BindView(R.id.tv_age_date)
    StringTextView tvAgeDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.tv_age_totalLegalArea)
    StringTextView tvAgeTotalLegalArea;
    @BindView(R.id.et_age_before90Area)
    EnableEditText etAgeBefore90Area;
    @BindView(R.id.et_age_asLegitimateArea)
    EnableEditText etAgeAsLegitimateArea;
    @BindView(R.id.tv_age_totalNoLegalArea)
    StringTextView tvAgeTotalNoLegalArea;
    @BindView(R.id.et_age_after90Area)
    EnableEditText etAgeAfter90Area;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    @BindView(R.id.tv_age_totalNotRecordArea)
    StringTextView tvAgeTotalNotRecordArea;
    @BindView(R.id.tv_age_shedArea)
    StringTextView tvAgeShedArea;
    @BindView(R.id.tv_age_simpleHouseArea)
    StringTextView tvAgeSimpleHouseArea;
    @BindView(R.id.tv_age_Address)
    StringTextView tvAgeAddress;


    @Override
    protected int getContentView() {
        return R.layout.activity_node_company_age;
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
        return "年限鉴定";
    }

    @Override
    protected void initView() {
        nodeCompanyAgePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        super.initData();
        etAgeBefore90Area.addTextChangedListener(calculateTextWatcher);
        etAgeAsLegitimateArea.addTextChangedListener(calculateTextWatcher);
        etAgeAfter90Area.addTextChangedListener(sameTextWatcher);
    }

    @Override
    protected void initNet() {
        nodeCompanyAgePresenter.getCompanyAge(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        setDateSelector(ivDateSelector, tvAgeDate, allowEdit);
        etAgeAfter90Area.setEnabled(allowEdit);
        etAgeBefore90Area.setEnabled(allowEdit);
        etAgeAsLegitimateArea.setEnabled(allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etRemark.getText().toString().trim();
        String after90Area = etAgeAfter90Area.getText().toString().trim();
        String before90Area = etAgeBefore90Area.getText().toString().trim();
        String asLegitimateArea = etAgeAsLegitimateArea.getText().toString().trim();
        String idenDate = tvAgeDate.getText().toString().trim();
        nodeCompanyAgePresenter.modifyCompanyAge(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("EnterpriseId", mBuildingId)
                .addFormDataPart("Before90Area", before90Area)
                .addFormDataPart("After90Area", after90Area)
                .addFormDataPart("AsLegitimateArea", asLegitimateArea)
                .addFormDataPart("IdenDate", idenDate)
                .addFormDataPart("Remark", remark)
                .build());
    }

    @Override
    public void onGetCompanyAgeSuccess(NodeCompanyAge nodeCompanyAge) {
        allowEdit = nodeCompanyAge.isAllowEdit();
        setEditable(allowEdit);
        tvAgeTotalNotRecordArea.setString(nodeCompanyAge.getTotalNotRecordArea());
        tvAgeShedArea.setString(nodeCompanyAge.getShedArea());
        tvAgeSimpleHouseArea.setString(nodeCompanyAge.getSimpleHouseArea());
        tvAgeAddress.setString(nodeCompanyAge.getAddress());
        tvAgeRealName.setString(nodeCompanyAge.getRealName());
        tvAgeCompanyName.setString(nodeCompanyAge.getCompanyName());
        tvAgeDate.setString(nodeCompanyAge.getIdenDate());
        etAgeBefore90Area.setText(nodeCompanyAge.getBefore90Area());
        etAgeAsLegitimateArea.setText(nodeCompanyAge.getAsLegitimateArea());
        tvAgeTotalNoLegalArea.setText(nodeCompanyAge.getAfter90Area());
        etAgeAfter90Area.setText(nodeCompanyAge.getAfter90Area());
        calculateArea();
        rvPhotoPreview.setData(nodeCompanyAge.getFiles(), getFileConfig(), allowEdit);
    }

    @Override
    public void onModifyCompanyAgeSuccess() {
        showSuccessAndFinish();
    }

    private TextWatcher calculateTextWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            calculateArea();
        }
    };

    private TextWatcher sameTextWatcher = new SimpleTextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            String after90AreaStr = etAgeAfter90Area.getText().toString().trim();
            double after90Area = TextUtils.isEmpty(after90AreaStr) ? 0d : Double.valueOf(after90AreaStr);
            tvAgeTotalNoLegalArea.setText(String.valueOf(after90Area));
        }
    };

    private void calculateArea() {
        String before90AreaStr = etAgeBefore90Area.getText().toString().trim();
        String legitimateAreaStr = etAgeAsLegitimateArea.getText().toString().trim();
        double before90Area = TextUtils.isEmpty(before90AreaStr) ? 0d : Double.valueOf(before90AreaStr);
        double legitimateArea = TextUtils.isEmpty(legitimateAreaStr) ? 0d : Double.valueOf(legitimateAreaStr);
        tvAgeTotalLegalArea.setText(String.valueOf(before90Area + legitimateArea));
    }
}
