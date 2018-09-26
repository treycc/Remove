package com.jdp.hls.page.publicity.detail;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.publicity.object.PublicityObjectActivity;
import com.jdp.hls.util.DateUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:公示详情
 * Create Time:2018/9/18 0018 下午 1:40
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class PublicityDetailActivity extends BaseTitleActivity {
    @BindView(R.id.ll_publicity_select)
    LinearLayout llPublicitySelect;
    @BindView(R.id.tv_publicity_startDate)
    TextView tvPublicityStartDate;
    @BindView(R.id.ll_publicity_startDate)
    LinearLayout llPublicityStartDate;
    @BindView(R.id.tv_publicity_endDate)
    TextView tvPublicityEndDate;
    @BindView(R.id.ll_publicity_endDate)
    LinearLayout llPublicityEndDate;
    private TimePickerDialog startDateSelector;
    private TimePickerDialog endDateSelector;

    @OnClick({R.id.ll_publicity_select, R.id.ll_publicity_startDate, R.id.ll_publicity_endDate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_publicity_select:
                GoUtil.goActivity(this, PublicityObjectActivity.class);
                break;
            case R.id.ll_publicity_startDate:
                startDateSelector.show(getSupportFragmentManager(), "startDate");
                break;
            case R.id.ll_publicity_endDate:
                endDateSelector.show(getSupportFragmentManager(), "endDate");
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
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                ToastUtil.showText("保存");
            }
        });
        startDateSelector = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setThemeColor(ContextCompat.getColor(this,R.color.main))
                .setMinMillseconds(System.currentTimeMillis())
                .setWheelItemTextSize(15)
                .setTitleStringId("开始时间")
                .setCallBack((timePickerView, millseconds) -> {
                    tvPublicityStartDate.setText(DateUtil.getDateString(millseconds));
                })
                .build();
        endDateSelector = new TimePickerDialog.Builder()
                .setType(Type.YEAR_MONTH_DAY)
                .setWheelItemTextSize(15)
                .setTitleStringId("结束时间")
                .setThemeColor(ContextCompat.getColor(this,R.color.main))
                .setCallBack((timePickerView, millseconds) -> {
                    tvPublicityEndDate.setText(DateUtil.getDateString(millseconds));
                })
                .build();
    }

    @Override
    protected void initNet() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
