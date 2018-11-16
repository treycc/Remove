package com.jdp.hls.page.table;

import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BaseSpinnerAdapter;
import com.jdp.hls.adapter.TableAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Table;
import com.jdp.hls.page.business.basic.company.BasicCompanyActivity;
import com.jdp.hls.page.business.basic.personla.BasicPersonalActivity;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.DatePop;
import com.jdp.hls.view.PullToBottomListView;
import com.kingja.popwindowsir.PopHelper;
import com.kingja.popwindowsir.PopSpinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:一览表搜索
 * Create Time:2018/9/19 0019 上午 9:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class TableSearchActivity extends BaseTitleActivity {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.spiner_buildingType)
    PopSpinner spinerBuildingType;
    @BindView(R.id.spiner_node)
    PopSpinner spinerNode;
    @BindView(R.id.spiner_date)
    PopSpinner spinerDate;
    @BindView(R.id.ll_spinner_root)
    LinearLayout llSpinnerRoot;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    private List<Table> tables = new ArrayList<>();
    private TableAdapter adapter;
    private DatePop datePop;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Table table = (Table) adapterView.getItemAtPosition(position);
        if (table.getBuildingType() == Status.BuildingType.PERSONAL) {
            BasicPersonalActivity.goActivity(this, table.getBuildingId());
        } else {
            BasicCompanyActivity.goActivity(this, table.getBuildingId());
        }
    }

    @Override
    public void initVariable() {
        tables = (List<Table>) getIntent().getSerializableExtra("tables");
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_table_search;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "一览表搜索";
    }

    @Override
    protected void initView() {
        plv.setAdapter(adapter = new TableAdapter(this, tables, R.layout.item_table));
    }

    @Override
    protected void initData() {
        initFlowNodePop();
        initFlowTypePop();
        initDatePop();
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                adapter.searchPerson(s.toString());
            }
        });
        datePop.setOnDateSelectedListener((startDate, endDate) -> {
            adapter.filterDate(startDate, endDate);
        });
    }


    @Override
    public void initNet() {

    }


    public static void goActivity(Activity activity, List<Table> tables) {
        Intent intent = new Intent(activity, TableSearchActivity.class);
        intent.putExtra("tables", (Serializable) tables);
        activity.startActivity(intent);
    }

    private void initFlowNodePop() {
        List<TDict> flowNodes = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.FLOW_NODE);
        if (flowNodes != null && flowNodes.size() > 0) {
            TDict firstDict = new TDict();
            firstDict.setTypeId(-1);
            firstDict.setTypeName("不限");
            flowNodes.add(0, firstDict);
            BaseSpinnerAdapter<TDict> flowNodeAdapter = new BaseSpinnerAdapter<TDict>(this, flowNodes) {
                @Override
                protected String setSpinnerText(TDict item) {
                    return item.getTypeName();
                }
            };
            new PopHelper.Builder(this)
                    .setPopSpinner(spinerNode)
                    .setAdapter(flowNodeAdapter)
                    .setOnItemClickListener((PopHelper.OnItemClickListener<TDict>) (item, position, popSpinner)
                            -> {
                        flowNodeAdapter.selectItem(position);
                        spinerNode.setSelectText(item.getTypeName());
                        adapter.filterStatusId(item.getTypeId());
                    })
                    .build();
        }
    }

    private void initFlowTypePop() {
        BaseSpinnerAdapter<String> buildingTypeAdapter = new BaseSpinnerAdapter<String>(this, Arrays.asList("不限",
                "个人", "企业")) {
            @Override
            protected String setSpinnerText(String item) {
                return item;
            }
        };
        new PopHelper.Builder(this)
                .setPopSpinner(spinerBuildingType)
                .setAdapter(buildingTypeAdapter)
                .setOnItemClickListener((PopHelper.OnItemClickListener<String>) (item, position, popSpinner)
                        -> {
                    adapter.filterBuildingType(position - 1);
                    buildingTypeAdapter.selectItem(position);
                    spinerBuildingType.setSelectText(item);
                })
                .build();
    }

    private void initDatePop() {
        datePop = new DatePop(this);
        datePop.setOnDismissListener(() -> {
            spinerDate.close();
        });
        spinerDate.setOnSpinnerStatusChangedListener(opened -> {
            if (opened) {
                datePop.showAsDropDown(llSpinnerRoot);
            } else {
                datePop.dismiss();
            }
        });
    }

}
