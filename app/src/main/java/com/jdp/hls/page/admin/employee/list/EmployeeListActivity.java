package com.jdp.hls.page.admin.employee.list;

import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.EmployeeAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddEmployeeEvent;
import com.jdp.hls.event.ModifyEmployeeEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.page.admin.employee.add.EmployeeAddActivity;
import com.jdp.hls.page.admin.employee.detail.EmployeeDetailActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:账号管理
 * Create Time:2018/11/15 0015 下午 3:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EmployeeListActivity extends BaseTitleActivity implements EmployeeListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.rsrl)
    RefreshSwipeRefreshLayout rsrl;
    @Inject
    EmployeeListPresenter employeeListPresenter;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    private EmployeeAdapter employeeAdapter;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Employee employee = (Employee) adapterView.getItemAtPosition(position);
        EmployeeDetailActivity.goActivity(this, employee.getEmployeeId());
    }

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
        }
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv_rsl_search;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        employeeListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "账户管理";
    }

    @Override
    protected void initView() {
        employeeAdapter = new EmployeeAdapter(this, null);
        plv.setAdapter(employeeAdapter);
    }

    @Override
    protected void initData() {
        etKeyword.setHint("请输入姓名/手机号/别名");
        setRightClick("增加", v -> {
//            GoUtil.goActivity(EmployeeListActivity.this, EmployeeAddActivity.class);
            EmployeeDetailActivity.goActivity(EmployeeListActivity.this,0);
        });
        rsrl.stepRefresh(this);
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                employeeAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void initNet() {
        employeeListPresenter.getEmployeeList(Constants.PAGE_FIRST, Constants.PAGE_SIZE_100);
    }

    @Override
    public void onGetEmployeeListSuccess(EmployeeDetail employeeDetail) {
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(employeeDetail.getResultList(), employeeAdapter, keyword);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyEmployee(ModifyEmployeeEvent event) {
        employeeAdapter.modifyEmployee(event.getEmployee());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addEmployee(AddEmployeeEvent event) {
        employeeAdapter.addEmployee(event.getEmployee());
    }
}
