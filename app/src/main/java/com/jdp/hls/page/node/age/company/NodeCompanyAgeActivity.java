package com.jdp.hls.page.node.age.company;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.NodeCompanyAge;
import com.jdp.hls.page.node.BaseNodeActivity;
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
    @BindView(R.id.et_age_totalNotRecordArea)
    EnableEditText etAgeTotalNotRecordArea;
    @BindView(R.id.et_age_shedArea)
    EnableEditText etAgeShedArea;
    @BindView(R.id.et_age_)
    EnableEditText etAge;
    @BindView(R.id.et_age_Address)
    StringTextView etAgeAddress;
    @BindView(R.id.et_age_companyName)
    StringTextView etAgeCompanyName;
    @BindView(R.id.tv_age_date)
    StringTextView tvAgeDate;
    @BindView(R.id.iv_dateSelector)
    ImageView ivDateSelector;
    @BindView(R.id.tv_age_totalLegalArea)
    TextView tvAgeTotalLegalArea;
    @BindView(R.id.et_age_before90Area)
    EnableEditText etAgeBefore90Area;
    @BindView(R.id.et_age_asLegitimateArea)
    EnableEditText etAgeAsLegitimateArea;
    @BindView(R.id.tv_age_totalNoLegalArea)
    TextView tvAgeTotalNoLegalArea;
    @BindView(R.id.et_age_after90Area)
    EnableEditText etAgeAfter90Area;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private String identifierId;


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
        rvPhotoPreview.create();
    }

    @Override
    protected void initNet() {
        nodeCompanyAgePresenter.getCompanyAge(mBuildingId);
    }

    @Override
    protected void onUiEditable(boolean allowEdit) {
        setDateSelector(ivDateSelector, tvAgeDate, allowEdit);
        etAgeTotalNotRecordArea.setEnabled(allowEdit);
    }

    @Override
    protected void onSaveDate() {
        String remark = etRemark.getText().toString().trim();

        nodeCompanyAgePresenter.modifyCompanyAge(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("HouseId", mBuildingId)
                .build());
    }

    @Override
    public void onGetCompanyAgeSuccess(NodeCompanyAge nodeCompanyAge) {
        setEditable(true);
    }

    @Override
    public void onModifyCompanyAgeSuccess() {
        showSuccessAndFinish();
    }

}
