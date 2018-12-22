package com.jdp.hls.page.supervise.statistics.total.taotype;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.TaoType;
import com.jdp.hls.page.supervise.statistics.total.taotype.person.TaoTypePersonListActivity;
import com.jdp.hls.view.StringTextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/12/20 0020 下午 5:59
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsTaotypeListActivity extends BaseTitleActivity implements StatisticsTaotypeListContract.View {
    @Inject
    StatisticsTaotypeListPresenter statisticsTaotypeListPresenter;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.tv_totalArea)
    StringTextView tvTotalArea;
    @BindView(R.id.tv_hasDealTaoArea)
    StringTextView tvHasDealArea;
    @BindView(R.id.tv_leftArea)
    StringTextView tvLeftArea;
    private CommonAdapter adapter;
    private String key;
    private String value;
    private String otherArea;

    @Override
    public void initVariable() {
        key = getIntent().getStringExtra(Constants.Extra.Key);
        value = getIntent().getStringExtra(Constants.Extra.Value);
        otherArea = getIntent().getStringExtra(Constants.Extra.OTHER_AREA);
    }

    @OnItemClick({R.id.lv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        TaoType taoType = (TaoType) adapterView.getItemAtPosition(position);
        TaoTypePersonListActivity.goActivity(this, taoType.getId(), taoType.getPatternName());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_supervise_taotypelist;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        statisticsTaotypeListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "套型列表及统计";
    }

    @Override
    protected void initView() {
        lv.setAdapter(adapter = new CommonAdapter<TaoType>(this, null, R.layout.item_taotype_area) {
            @Override
            public void convert(ViewHolder helper, TaoType item) {
                helper.setText(R.id.tv_patternName, item.getPatternName());
                helper.setText(R.id.tv_quantity, String.valueOf(item.getQuantity()));
                helper.setText(R.id.tv_area, item.getArea());
            }
        });
    }

    private double taoArea = 0d;
    private double totalArea = 0d;
    private double hasDealTaoArea = 0d;

    @Override
    protected void initData() {


    }

    @Override
    public void initNet() {
        statisticsTaotypeListPresenter.getSuperviseTaotypeList();
    }

    @Override
    public void onGetSuperviseTaotypeListSuccess(List<TaoType> taoTypeList) {
        setListView(taoTypeList, adapter);

        hasDealTaoArea = getDealTaoArea(taoTypeList);
        tvHasDealArea.setString(String.format("%.2fm² (建筑:%.2fm²)", hasDealTaoArea, hasDealTaoArea * 1.25f));
        switch (key) {
            case Status.AreaType.BUILDING_AREA:
                taoArea = Double.valueOf(value) / 1.25f;
                totalArea = Double.valueOf(value);
                tvTotalArea.setString(String.format("%.2fm² (建筑:%.2fm²)", taoArea, totalArea));
                break;
            case Status.AreaType.TAOTYPE_AREA:
                taoArea = Double.valueOf(value);
                totalArea = Double.valueOf(value) * 1.25f;
                tvTotalArea.setString(String.format("%.2fm² (建筑:%.2fm²)", taoArea, totalArea));
                break;
            case Status.AreaType.TAOTYPE:
                taoArea = Double.valueOf(otherArea) / 1.25f;
                totalArea = Double.valueOf(otherArea);
                tvTotalArea.setString(String.format("%.2fm² (建筑:%.2fm²)", taoArea, totalArea));
                break;
        }

        tvLeftArea.setString(String.format("%.2fm²  (建筑:%.2fm²)", Math.abs(taoArea - hasDealTaoArea), Math.abs
                (totalArea - hasDealTaoArea)));
    }

    private double getDealTaoArea(List<TaoType> taoTypeList) {
        double result = 0d;
        if (taoTypeList != null && taoTypeList.size() > 0) {
            for (TaoType taoType : taoTypeList) {
                result += Double.valueOf(TextUtils.isEmpty(taoType.getArea()) ? "0" : taoType.getArea());
            }
        }
        return result;
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    public static void GoActivity(Context context, String key, String value, String buildingArea) {
        Intent intent = new Intent(context, StatisticsTaotypeListActivity.class);
        intent.putExtra(Constants.Extra.Key, key);
        intent.putExtra(Constants.Extra.Value, value);
        intent.putExtra(Constants.Extra.OTHER_AREA, buildingArea);
        context.startActivity(intent);
    }
}
