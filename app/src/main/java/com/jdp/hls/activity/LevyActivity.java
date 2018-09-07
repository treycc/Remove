package com.jdp.hls.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.page.businesslist.BusinessListActivity;
import com.jdp.hls.page.map.RosterActivity;
import com.jdp.hls.util.GoUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Description:TODO
 * Create Time:2018/9/5 0005 上午 9:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LevyActivity extends BaseTitleActivity {
    @BindView(R.id.ll_business_todo)
    LinearLayout llBusinessTodo;
    @BindView(R.id.ll_business_done)
    LinearLayout llBusinessDone;
    @BindView(R.id.ll_business_finish)
    LinearLayout llBusinessFinish;
    @BindView(R.id.ll_business_back)
    LinearLayout llBusinessBack;
    @BindView(R.id.ll_business_discard)
    LinearLayout llBusinessDiscard;
    @BindView(R.id.ll_operate_roster)
    LinearLayout llOperateRoster;
    @BindView(R.id.ll_operate_publicity)
    LinearLayout llOperatePublicity;
    @BindView(R.id.ll_operate_plane)
    LinearLayout llOperatePlane;
    @BindView(R.id.ll_operate_detail)
    LinearLayout llOperateDetail;
    @BindView(R.id.ll_statistics_sign)
    LinearLayout llStatisticsSign;
    @BindView(R.id.ll_statistics_measure)
    LinearLayout llStatisticsMeasure;
    @BindView(R.id.ll_statistics_measureImg)
    LinearLayout llStatisticsMeasureImg;
    @BindView(R.id.ll_statistics_age)
    LinearLayout llStatisticsAge;
    @BindView(R.id.ll_statistics_evaluate)
    LinearLayout llStatisticsEvaluate;
    @BindView(R.id.ll_statistics_protocol)
    LinearLayout llStatisticsProtocol;
    @BindView(R.id.ll_statistics_plane)
    LinearLayout llStatisticsPlane;
    @BindView(R.id.ll_statistics_publicity)
    LinearLayout llStatisticsPublicity;

    @OnClick({R.id.ll_business_todo, R.id.ll_business_done, R.id.ll_business_finish, R.id.ll_business_back, R.id
            .ll_business_discard, R.id.ll_operate_roster, R.id.ll_operate_publicity, R.id.ll_operate_plane, R.id
            .ll_operate_detail, R.id.ll_statistics_sign, R.id.ll_statistics_measure, R.id.ll_statistics_measureImg, R.id
            .ll_statistics_age, R.id.ll_statistics_evaluate, R.id.ll_statistics_protocol, R.id.ll_statistics_plane, R.id
            .ll_statistics_publicity})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.ll_business_todo:
                /*待办业务*/
                GoUtil.goActivity(this, BusinessListActivity.class);
                break;
            case R.id.ll_business_done:
                /*已办业务*/
                break;
            case R.id.ll_business_finish:
                /*办结业务*/
                break;
            case R.id.ll_business_back:
                /*退回业务*/
                break;
            case R.id.ll_business_discard:
                /*废弃业务*/
                break;
            case R.id.ll_operate_roster:
                /*花名册*/
                GoUtil.goActivity(this, RosterActivity.class);
                break;
            case R.id.ll_operate_publicity:
                /*公示管理*/
                break;
            case R.id.ll_operate_plane:
                /*航拍复查*/
                break;
            case R.id.ll_operate_detail:
                /*一览表*/
                break;
            case R.id.ll_statistics_sign:
                /*签约统计*/
                GoUtil.goActivity(this, StatisticsActivity.class);
                break;
            case R.id.ll_statistics_measure:
                /*入户丈量*/
                break;
            case R.id.ll_statistics_measureImg:
                /*测绘出图*/
                break;
            case R.id.ll_statistics_age:
                /*年限鉴定*/
                break;
            case R.id.ll_statistics_evaluate:
                /*入户评估*/
                break;
            case R.id.ll_statistics_protocol:
                /*协议生成*/
                break;
            case R.id.ll_statistics_plane:
                /*航拍统计*/
                break;
            case R.id.ll_statistics_publicity:
                /*公示统计*/
                break;
        }
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.levy_activity;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "征收业务系统";
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
