package com.jdp.hls.page.admin.group.member;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.EmployeeCompanyAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Employee;
import com.jdp.hls.model.entiy.EmployeeDetail;
import com.jdp.hls.model.entiy.Member;
import com.jdp.hls.page.admin.group.detail.GroupDetailActivity;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/11/21 0021 下午 6:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class MemberSelectActivity extends BaseTitleActivity implements MemberSelectContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout srl;
    @Inject
    MemberSelectPresenter memberSelectPresenter;
    private String projectId;
    private int companyTypeId;
    private EmployeeCompanyAdapter employeeCompanyAdapter;


    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Employee employee = (Employee) adapterView.getItemAtPosition(position);
        Member member = new Member();
        member.setCompanyTypeId(companyTypeId);
        member.setEmployeeId(employee.getEmployeeId());
        member.setRealName(employee.getRealName());
        Intent intent = new Intent();
        intent.putExtra(Constants.Extra.Member, member);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etKeyword.setText("");
            case R.id.tv_search:
                ToastUtil.showText("搜索");
                break;
        }
    }

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
        companyTypeId = getIntent().getIntExtra(Constants.Extra.CompanyTypeId, 0);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_member_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        memberSelectPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "选择成员";
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        srl.stepRefresh(this);
        employeeCompanyAdapter = new EmployeeCompanyAdapter(this, null);
        plv.setAdapter(employeeCompanyAdapter);
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                employeeCompanyAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void initNet() {
        String keyword = etKeyword.getText().toString().trim();
        memberSelectPresenter.getEmployeeByCompany(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("ProjectId", projectId)
                .addFormDataPart("CompanyTypeId", String.valueOf(companyTypeId))
                .addFormDataPart("PageSize", String.valueOf(Constants.PAGE_SIZE_100))
                .addFormDataPart("PageIndex", String.valueOf(Constants.PAGE_FIRST))
                .addFormDataPart("KeyWords", keyword)
                .build());
    }

    @Override
    public void onGetEmployeeByCompanySuccess(EmployeeDetail employeeDetail) {
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(employeeDetail.getResultList(), employeeCompanyAdapter, keyword);
    }

    public static void goActivity(Activity context, String projectId, int companyTypeId) {
        Intent intent = new Intent(context, MemberSelectActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        intent.putExtra(Constants.Extra.CompanyTypeId, companyTypeId);
        context.startActivityForResult(intent, Constants.RequestCode.MEMBERSELECT);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
