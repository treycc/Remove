package com.jdp.hls.page.admin.manager;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.jdp.hls.R;
import com.jdp.hls.adapter.ManagerAdapter;
import com.jdp.hls.adapter.ManagerSelectedAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.page.admin.employee.list.EmployeeListContract;
import com.jdp.hls.page.admin.employee.list.EmployeeListPresenter;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.StringTextView;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;

/**
 * Description:TODO
 * Create Time:2018/11/19 0019 下午 1:31
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ManagerListActivity extends BaseTitleActivity implements EmployeeListContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.lv)
    ListView lv;
    @BindView(R.id.bottomSheetLayout)
    BottomSheetLayout bottomSheetLayout;
    @BindView(R.id.tv_count)
    StringTextView tvCount;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @Inject
    EmployeeListPresenter employeeListPresenter;
    private ManagerAdapter managerAdapter;
    private View bottomSheetView;
    private ManagerSelectedAdapter selectedAdapter;
    private List<Employee> selectedEmployees;

    @OnItemClick({R.id.lv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Employee employee = (Employee) adapterView.getItemAtPosition(position);
        if (!managerAdapter.isSelected(employee)) {
            managerAdapter.addSelected(employee);
            tvCount.setString(selectedAdapter.getCount());
        }
    }

    @OnClick({R.id.ll_bottom, R.id.tv_confirm,R.id.iv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_bottom:
                if (selectedAdapter.getCount() == 0) {
                    return;
                }
                if (bottomSheetLayout.isSheetShowing()) {
                    bottomSheetLayout.dismissSheet();
                } else {
                    bottomSheetLayout.showWithSheetView(bottomSheetView);
                }
                break;

            case R.id.tv_confirm:
                if (selectedAdapter.getCount() == 0) {
                    ToastUtil.showText("请选择负责人");
                    return;
                } else {
                    String ids = selectedAdapter.getIds();
                    String names = selectedAdapter.getNames();
                    Intent intent = new Intent();
                    intent.putExtra(Constants.Extra.Ids, ids);
                    intent.putExtra(Constants.Extra.Names, names);
                    intent.putExtra(Constants.Extra.Employees, (Serializable) selectedAdapter.getData());
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }

                break;
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    public void initVariable() {
        selectedEmployees = (List<Employee>) getIntent().getSerializableExtra(Constants.Extra.Employees);
        LogUtil.e(TAG, "带去数量:" + selectedEmployees.size());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_manager_list;
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
        return "选择负责人";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        bottomSheetView = View.inflate(this, R.layout.layout_manager_list, null);
        FixedListView selectedLv = bottomSheetView.findViewById(R.id.flv_selected);
        selectedAdapter = new ManagerSelectedAdapter(this, selectedEmployees);
        managerAdapter = new ManagerAdapter(this, null, selectedEmployees);
        lv.setAdapter(this.managerAdapter);
        selectedLv.setAdapter(selectedAdapter);
        selectedLv.setOnItemClickListener((parent, view, position, id) -> {
            Employee employee = (Employee) parent.getItemAtPosition(position);
            managerAdapter.removeSelected(employee);
            selectedAdapter.notifyDataSetChanged();
            tvCount.setString(selectedAdapter.getCount());
        });
        tvCount.setString(selectedAdapter.getCount());
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                managerAdapter.onSearch(s.toString());
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
        setSearchListView(employeeDetail.getResultList(), managerAdapter, keyword);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    public static void goActivity(Activity activity, List<Employee> employees) {
        Intent intent = new Intent(activity, ManagerListActivity.class);
        intent.putExtra(Constants.Extra.Employees, (Serializable) employees);
        activity.startActivityForResult(intent, Constants.RequestCode.MANAGER_LIST);
    }
}
