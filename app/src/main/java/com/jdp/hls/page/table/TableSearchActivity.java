package com.jdp.hls.page.table;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jdp.hls.R;
import com.jdp.hls.activity.RosterListActivity;
import com.jdp.hls.adapter.SpinnerAdapter;
import com.jdp.hls.adapter.TableAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.Table;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;
import com.kingja.popwindowsir.PopHelper;
import com.kingja.popwindowsir.PopSpinner;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    private List<Table> tables=new ArrayList<>();
    private TableAdapter adapter;

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
        setRightClick("搜索", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {

            }
        });
        initFlowNodePop();

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

    public static void goActivity(Activity activity, List<Table> tables) {
        Intent intent = new Intent(activity, TableSearchActivity.class);
        intent.putExtra("tables", (Serializable) tables);
        activity.startActivity(intent);
    }

    private void initFlowNodePop() {
        List<TDict> flowNodes = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.FLOW_NODE);

        if (flowNodes != null && flowNodes.size() > 0) {
            SpinnerAdapter spinnerAdapter = new SpinnerAdapter(this, flowNodes);
            new PopHelper.Builder(this)
                    .setPopSpinner(spinerNode)
                    .setAdapter(spinnerAdapter)
                    .setOnItemClickListener((PopHelper.OnItemClickListener<TDict>) (item, position, popSpinner)
                            -> {
                        spinnerAdapter.selectItem(position);
                        spinerNode.setSelectText(item.getTypeName());
                    })
                    .build();
        }
    }
}
