package com.jdp.hls.page.business.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CountPageAdapter;
import com.jdp.hls.base.BaseBasicActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.i.OnBusinessItemSelectedListener;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Auth;
import com.jdp.hls.model.entiy.Business;
import com.jdp.hls.model.entiy.TaskInfo;
import com.jdp.hls.page.operate.OperateNodeContract;
import com.jdp.hls.page.operate.OperateNodePresenter;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;
import okhttp3.RequestBody;

/**
 * Description:TODO
 * Create Time:2018/9/6 0006 下午 4:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class BusinessListActivity extends BaseBasicActivity implements BussinessContract.View, OperateNodeContract
        .View, OnBusinessItemSelectedListener {
    @BindView(R.id.et_business_keyword)
    EditText etBusinessKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.tab_business)
    TabLayout tabBusiness;
    @BindView(R.id.vp_business)
    ViewPager vpBusiness;
    @BindView(R.id.cb_selectAll)
    CheckBox cbSelectAll;
    private String[] tabBusinessTitles = {"个人业务", "企业业务"};
    private String[] tabBusinessCounts = {"0", "0"};
    private int[] tabBusinessIcons = {R.drawable.selector_tab_personal, R.drawable.selector_tab_company};
    private BusinessListFragment mFragmentArr[] = new BusinessListFragment[2];
    private List<Business> personalBusiness = new ArrayList<>();
    private List<Business> companyBusiness = new ArrayList<>();
    @Inject
    BusinessPresenter businessPresenter;
    @Inject
    OperateNodePresenter operateNodePresenter;
    private String taskTypeName;
    private int taskType;
    private BusinessListFragment personalListFragment;
    private BusinessListFragment companyListFragment;
    private List<Business> businesses;
    private CountPageAdapter mCountPageAdapter;

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etBusinessKeyword.setText("");
                break;
        }
    }

    @Override
    public void initVariable() {
        taskTypeName = getIntent().getStringExtra("taskTypeName");
        taskType = getIntent().getIntExtra("taskType", 0);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_business;
    }

    @OnCheckedChanged({R.id.cb_selectAll})
    public void checkedChanged(CompoundButton buttonView, boolean isChecked) {
        mFragmentArr[0].checkAll(isChecked);
        mFragmentArr[1].checkAll(isChecked);
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected String getContentTitle() {
        return taskTypeName != null ? taskTypeName : "";
    }

    @Override
    protected void initView() {
        businessPresenter.attachView(this);
        operateNodePresenter.attachView(this);
    }

    @Override
    protected void initData() {
        etBusinessKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                checkData(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
    }

    private void checkData(String keyword) {
        doSearch(keyword);
    }

    private void doSearch(String keyword) {
        if (businesses == null && businesses.size() == 0) {
            ToastUtil.showText("暂无信息");
            return;
        }
        refreshData(keyword);
    }

    private void refreshData(String keyword) {
        List<Business> selectBusiness = new ArrayList<>();
        for (Business roster : businesses) {
            if (roster.getRealName().contains(keyword) || roster.getAddress().contains(keyword) || roster
                    .getMobilePhone().contains(keyword)) {
                selectBusiness.add(roster);
            }
        }
        List<Business> personalBusiness = new ArrayList<>();
        List<Business> companyBusiness = new ArrayList<>();
        for (Business business : selectBusiness) {
            if (business.getBuildingType() == 0) {
                personalBusiness.add(business);
            } else {
                companyBusiness.add(business);
            }
        }
        mFragmentArr[0].refreshData(personalBusiness);
        mFragmentArr[1].refreshData(companyBusiness);

        refreshTitles(personalBusiness.size(), companyBusiness.size());
    }

    private void refreshTitles(int personalRosters, int companyRosters) {
        tabBusinessCounts[0] = personalRosters + "户";
        tabBusinessCounts[1] = companyRosters + "家";

        mCountPageAdapter.refreshrosterCount(tabBusinessCounts);

        for (int i = 0; i < tabBusiness.getTabCount(); i++) {
            TabLayout.Tab tab = tabBusiness.getTabAt(i);
            tab.setCustomView(mCountPageAdapter.getTabView(i));
        }
    }

    @Override
    protected void initNet() {
        businessPresenter.getBusinessList(SpSir.getInstance().getProjectId(), -1, taskType);
    }

    @Override
    protected void onSendNode(RequestBody requestBody) {
        operateNodePresenter.sendNode(requestBody);
    }

    @Override
    protected void onBackNode(RequestBody requestBody) {
        operateNodePresenter.backNode(requestBody);
    }

    @Override
    protected void onReviewNode(RequestBody requestBody) {
        operateNodePresenter.reviewNode(requestBody);
    }

    @Override
    protected void onDeleteNode(RequestBody requestBody) {
        operateNodePresenter.deleteNode(requestBody);
    }

    @Override
    public void onGetBusinessSuccess(TaskInfo taskInfo) {
        businesses = taskInfo.getMyTaskList();
        boolean checkable = getCheckable(taskInfo.getAuth());
        personalBusiness.clear();
        companyBusiness.clear();
        if (businesses == null) {
            return;
        }
        for (Business business : businesses) {
            if (business.getBuildingType() == 1) {
                companyBusiness.add(business);
            } else {
                personalBusiness.add(business);
            }
        }
        tabBusinessCounts[0] = String.valueOf(personalBusiness.size());
        tabBusinessCounts[1] = String.valueOf(companyBusiness.size());
        tabBusiness.setTabMode(TabLayout.MODE_FIXED);
        tabBusiness.addTab(tabBusiness.newTab().setText(tabBusinessTitles[0]));
        tabBusiness.addTab(tabBusiness.newTab().setText(tabBusinessTitles[1]));
        personalListFragment = BusinessListFragment.newInstance(personalBusiness, taskType,
                Status.BuildingType.PERSONAL, checkable);
        companyListFragment = BusinessListFragment.newInstance(companyBusiness, taskType,
                Status.BuildingType.COMPANY, checkable);
        personalListFragment.setOnBusinessSelectedListener(this);
        companyListFragment.setOnBusinessSelectedListener(this);
        mFragmentArr[0] = personalListFragment;
        mFragmentArr[1] = companyListFragment;
        mCountPageAdapter = new CountPageAdapter(this, getSupportFragmentManager(),
                mFragmentArr, tabBusinessTitles, tabBusinessCounts, tabBusinessIcons);
        vpBusiness.setAdapter(mCountPageAdapter);
        vpBusiness.setOffscreenPageLimit(2);
        tabBusiness.setupWithViewPager(vpBusiness);
        for (int i = 0; i < tabBusiness.getTabCount(); i++) {
            TabLayout.Tab tab = tabBusiness.getTabAt(i);
            tab.setCustomView(mCountPageAdapter.getTabView(i));
        }
        initAuthLayout(taskInfo.getAuth());
    }

    public boolean getCheckable(Auth auth) {
        return auth.isAllowReview() || auth.isAllowSend() || auth.isAllowFlowBack() || auth.isAllowBanned();
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }

    public static void GoActivity(Context context, int taskType, String taskTypeName) {
        Intent intent = new Intent(context, BusinessListActivity.class);
        intent.putExtra("taskType", taskType);
        intent.putExtra("taskTypeName", taskTypeName);
        context.startActivity(intent);
    }

    @Override
    public void onDeleteNodeSuccess() {
        showSuccessAndFinish("废弃成功");
    }

    @Override
    public void onSendNodeSuccess() {
        showSuccessAndFinish("发送成功");
    }

    @Override
    public void onReviewNodeSuccess() {
        showSuccessAndFinish("复查成功");
    }

    @Override
    public void onBackNodeSuccess() {
        showSuccessAndFinish("退回成功");
    }

    private Set<Business> selectedBusinessList = new HashSet<>();


    public void setRefreshDialogDate() {
        StringBuilder buildingIdsSb = new StringBuilder();
        StringBuilder buildingTypesSb = new StringBuilder();
        StringBuilder buildingStatusIdsSb = new StringBuilder();
        StringBuilder gruopIdsSb = new StringBuilder();
//        for (int i = 0; i < selectedBusinessList.size(); i++) {
//            if (i != selectedBusinessList.size() - 1) {
//                buildingIdsSb.append(selectedBusinessList.get(i).getBuildingId());
//                buildingIdsSb.append("#");
//                buildingTypesSb.append(selectedBusinessList.get(i).getBuildingType());
//                buildingTypesSb.append("#");
//                buildingStatusIdsSb.append(selectedBusinessList.get(i).getStatusId());
//                buildingStatusIdsSb.append("#");
//                gruopIdsSb.append(selectedBusinessList.get(i).getGroupId());
//                gruopIdsSb.append("#");
//            } else {
//                buildingIdsSb.append(selectedBusinessList.get(i).getBuildingId());
//                buildingTypesSb.append(selectedBusinessList.get(i).getBuildingType());
//                buildingStatusIdsSb.append(selectedBusinessList.get(i).getStatusId());
//                gruopIdsSb.append(selectedBusinessList.get(i).getGroupId());
//            }
//        }
        for (Business business : selectedBusinessList) {
            buildingIdsSb.append(business.getBuildingId());
            buildingIdsSb.append("#");
            buildingTypesSb.append(business.getBuildingType());
            buildingTypesSb.append("#");
            buildingStatusIdsSb.append(business.getStatusId());
            buildingStatusIdsSb.append("#");
            gruopIdsSb.append(business.getGroupId());
            gruopIdsSb.append("#");
        }
        fillDialogDate(buildingIdsSb.toString(), buildingTypesSb.toString(), buildingStatusIdsSb.toString(),
                gruopIdsSb.toString());
    }

    @Override
    public void onBusinessRemove(Business business) {
        selectedBusinessList.remove(business);
        LogUtil.e(TAG, getClass().getSimpleName() + "减少 数量:" + selectedBusinessList.size());
        setRefreshDialogDate();
    }

    @Override
    public void onBusinessAdd(Business business) {
        selectedBusinessList.add(business);
        LogUtil.e(TAG, getClass().getSimpleName() + "增加 数量:" + selectedBusinessList.size());
        setRefreshDialogDate();
    }

}
