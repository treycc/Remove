package com.jdp.hls.page.admin.project.detail.payscheme;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.PaySchemeInfo;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2019/1/4 0004 上午 9:08
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PaySchemeActivity extends BaseTitleActivity implements PaySchemeContract.View {
    @BindView(R.id.et_inPolicyBuyMoreAmount)
    EnableEditText etInPolicyBuyMoreAmount;
    @BindView(R.id.et_newHouseValuationPice)
    EnableEditText etNewHouseValuationPrice;
    @BindView(R.id.et_collectBuyBackPrice)
    EnableEditText etCollectBuyBackPrice;
    private String projectId;
    @Inject
    PaySchemePresenter paySchemePresenter;

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_pay_scheme;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        paySchemePresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "补偿方案";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                String inPolicyBuyMoreAmount = etInPolicyBuyMoreAmount.getText().toString().trim();
                String newHouseValuationPice = etNewHouseValuationPrice.getText().toString().trim();
                String collectBuyBackPrice = etCollectBuyBackPrice.getText().toString().trim();
                paySchemePresenter.savePayScheme(new MultipartBody.Builder().setType(MultipartBody.FORM)
                        .addFormDataPart("ProjectId", projectId)
                        .addFormDataPart("InPolicyBuyMoreAmount", inPolicyBuyMoreAmount)
                        .addFormDataPart("NewHouseValuationPrice", newHouseValuationPice)
                        .addFormDataPart("CollectBuyBackPrice", collectBuyBackPrice)
                        .build());
            }
        });

    }

    @Override
    public void initNet() {
        paySchemePresenter.getPayScheme(projectId);
    }

    public static void goActivity(Context context, String projectId) {
        Intent intent = new Intent(context, PaySchemeActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        context.startActivity(intent);
    }

    @Override
    public void onGetPaySchemeSuccess(PaySchemeInfo paySchemeInfo) {
        etInPolicyBuyMoreAmount.setString(paySchemeInfo.getInPolicyBuyMoreAmount());
        etNewHouseValuationPrice.setString(paySchemeInfo.getNewHouseValuationPrice());
        etCollectBuyBackPrice.setString(paySchemeInfo.getCollectBuyBackPrice());
    }

    @Override
    public void onSavePaySchemeSuccess() {
        showSuccessDialogAndFinish();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
