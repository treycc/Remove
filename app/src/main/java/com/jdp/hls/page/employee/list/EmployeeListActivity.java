package com.jdp.hls.page.employee.list;

import android.view.View;
import android.widget.AdapterView;

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
import com.jdp.hls.page.employee.add.EmployeeAddActivity;
import com.jdp.hls.page.employee.detail.EmployeeDetailActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshableSwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 3:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class EmployeeListActivity extends BaseTitleActivity implements EmployeeListContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshableSwipeRefreshLayout srl;
    @Inject
    EmployeeListPresenter employeeListPresenter;
    private EmployeeAdapter employeeAdapter;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Employee employee = (Employee) adapterView.getItemAtPosition(position);
        EmployeeDetailActivity.goActivity(this, String.valueOf(employee.getEmployeeId()));
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv_sl;
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
        setRightClick("增加", v -> {
            GoUtil.goActivity(EmployeeListActivity.this, EmployeeAddActivity.class);
        });
    }

    @Override
    public void initNet() {
        employeeListPresenter.getEmployeeList(Constants.PAGE_FIRST, Constants.PAGE_SIZE_100);
    }

    @Override
    public void onGetEmployeeListSuccess(EmployeeDetail employeeDetail) {
        if (employeeDetail != null) {
            List<Employee> employeeList = employeeDetail.getResultList();
            if (employeeList != null && employeeList.size() > 0) {
                employeeAdapter.setData(employeeList);
            }
        }
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
