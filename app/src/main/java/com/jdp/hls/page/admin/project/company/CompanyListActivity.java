package com.jdp.hls.page.admin.project.company;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.jdp.hls.R;
import com.jdp.hls.adapter.CompanyAdapter;
import com.jdp.hls.adapter.CompanySelectedAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Company;
import com.jdp.hls.model.entiy.ConfigCompany;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.StringTextView;

import java.io.Serializable;
import java.util.ArrayList;
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
public class CompanyListActivity extends BaseTitleActivity implements CompanyListContract.View {
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    SwipeRefreshLayout srl;
    @BindView(R.id.bottomSheetLayout)
    BottomSheetLayout bottomSheetLayout;
    @BindView(R.id.tv_count)
    StringTextView tvCount;
    @Inject
    CompanyListPresenter companyListPresenter;
    private CompanyAdapter companyAdapter;
    private View bottomSheetView;
    private CompanySelectedAdapter companySelectedAdapter;
    private List<Company> selectedCompanies = new ArrayList<>();
    private int companyTypeId;

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Company company = (Company) adapterView.getItemAtPosition(position);
        if (!companyAdapter.isSelected(company)) {
            companyAdapter.addSelected(company);
            tvCount.setString(companySelectedAdapter.getCount());
        }
    }

    @OnClick({R.id.ll_bottom, R.id.tv_confirm, R.id.iv_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_bottom:
                if (companySelectedAdapter.getCount() == 0) {
                    return;
                }
                if (bottomSheetLayout.isSheetShowing()) {
                    bottomSheetLayout.dismissSheet();
                } else {
                    bottomSheetLayout.showWithSheetView(bottomSheetView);
                }
                break;

            case R.id.tv_confirm:
                if (companySelectedAdapter.getCount() == 0) {
                    ToastUtil.showText("请选择公司");
                    return;
                } else {
                    List<Company> selectCompanies = companySelectedAdapter.getData();
                    StringBuilder companyIdsSb = new StringBuilder();
                    StringBuilder companyNamesSb = new StringBuilder();
                    StringBuilder companyTypeIdsSb = new StringBuilder();
                    ConfigCompany configCompany = new ConfigCompany();
                    for (int i = 0; i < selectCompanies.size(); i++) {
                        if (i != selectCompanies.size() - 1) {
                            companyIdsSb.append(selectCompanies.get(i).getCompanyId());
                            companyIdsSb.append("#");
                            companyNamesSb.append(selectCompanies.get(i).getCompanyName());
                            companyNamesSb.append("\n");
                            companyTypeIdsSb.append(selectCompanies.get(i).getCompanyTypeID());
                            companyTypeIdsSb.append("#");
                        } else {
                            companyIdsSb.append(selectCompanies.get(i).getCompanyId());
                            companyNamesSb.append(selectCompanies.get(i).getCompanyName());
                            companyTypeIdsSb.append(selectCompanies.get(i).getCompanyTypeID());
                        }
                    }
                    configCompany.setCompanyTypeIDS(companyTypeIdsSb.toString());
                    configCompany.setCompanyId(companyIdsSb.toString());
                    configCompany.setCompanyName(companyNamesSb.toString());
                    configCompany.setCompanyTypeID(companyTypeId);
                    Intent intent = new Intent();
                    intent.putExtra(Constants.Extra.ConfigCompany, configCompany);
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
        ConfigCompany configCompany = (ConfigCompany) getIntent().getSerializableExtra(Constants.Extra.ConfigCompany);
        companyTypeId = configCompany.getCompanyTypeID();
        String companyIds = configCompany.getCompanyId();
        String companyNames = configCompany.getCompanyName();
        if (TextUtils.isEmpty(companyIds)) {
            return;
        }
        String[] companyIdsAttr = companyIds.split("#");
        String[] companyNamesAttr = companyNames.split("，");
        for (int i = 0; i < companyIdsAttr.length; i++) {
            Company company = new Company();
            company.setCompanyId(Integer.valueOf(companyIdsAttr[i]));
            company.setCompanyName(companyNamesAttr[i]);
            company.setCompanyTypeID(configCompany.getCompanyTypeID());
            selectedCompanies.add(company);
        }
        LogUtil.e(TAG, "带去数量:" + selectedCompanies.size());
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_company_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        companyListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "选择公司";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        bottomSheetView = View.inflate(this, R.layout.layout_manager_list, null);
        FixedListView selectedLv = bottomSheetView.findViewById(R.id.flv_selected);
        companySelectedAdapter = new CompanySelectedAdapter(this, selectedCompanies);
        companyAdapter = new CompanyAdapter(this, null, selectedCompanies);
        plv.setAdapter(this.companyAdapter);
        selectedLv.setAdapter(companySelectedAdapter);
        selectedLv.setOnItemClickListener((parent, view, position, id) -> {
            Company company = (Company) parent.getItemAtPosition(position);
            companyAdapter.removeSelected(company);
            companySelectedAdapter.notifyDataSetChanged();
            tvCount.setString(companySelectedAdapter.getCount());
        });
        tvCount.setString(companySelectedAdapter.getCount());
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                companyAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public void initNet() {
        companyListPresenter.getCompanyList(companyTypeId);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    public static void goActivity(Activity activity,  ConfigCompany company) {
        Intent intent = new Intent(activity, CompanyListActivity.class);
        intent.putExtra(Constants.Extra.ConfigCompany, company);
        activity.startActivityForResult(intent, Constants.RequestCode.COMPANY_LIST);
    }

    @Override
    public void onGetCompanyListSuccess(List<Company> companyList) {
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(companyList, companyAdapter, keyword);
    }
}
