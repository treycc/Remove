package com.jdp.hls.page.publicity.detail;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.publicity.object.PublicityObjectActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.PreviewRecyclerView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:公示详情
 * Create Time:2018/9/18 0018 下午 1:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityDetailActivity extends BaseTitleActivity {

    @BindView(R.id.et_publicity_number)
    EnableEditText etPublicityNumber;
    @BindView(R.id.et_publicity_count)
    TextView etPublicityCount;
    @BindView(R.id.ll_publicity_select)
    LinearLayout llPublicitySelect;
    @BindView(R.id.tv_publicity_realName)
    TextView tvPublicityRealName;
    @BindView(R.id.tv_publicity_startDate)
    TextView tvPublicityStartDate;
    @BindView(R.id.ll_publicity_startDate)
    LinearLayout llPublicityStartDate;
    @BindView(R.id.tv_publicity_endDate)
    TextView tvPublicityEndDate;
    @BindView(R.id.ll_publicity_endDate)
    LinearLayout llPublicityEndDate;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;

    @OnClick({R.id.ll_publicity_select, R.id.ll_publicity_startDate, R.id.ll_publicity_endDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_publicity_select:
                GoUtil.goActivity(this, PublicityObjectActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_publicity_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "公示信息";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initNet() {

    }

}
