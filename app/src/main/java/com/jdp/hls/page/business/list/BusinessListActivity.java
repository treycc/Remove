package com.jdp.hls.page.business.list;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
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
import com.jdp.hls.event.RefreshBusinessListEvent;
import com.jdp.hls.event.RefreshReminderEvent;
import com.jdp.hls.event.RefreshTaskEvent;
import com.jdp.hls.i.OnBusinessItemSelectedListener;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Auth;
import com.jdp.hls.model.entiy.Business;
import com.jdp.hls.model.entiy.TaskInfo;
import com.jdp.hls.page.operate.OperateNodeContract;
import com.jdp.hls.page.operate.OperateNodePresenter;
import com.jdp.hls.util.CollectionUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
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
    private String[] tabBusinessTitles = {"住宅业务", "企业业务"};
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
    private String keyword = "";

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etBusinessKeyword.setText("");
                keyword = "";
                break;
        }
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
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
        this.keyword = keyword;
        if (businesses == null || businesses.size() == 0) {
            ToastUtil.showText("暂无信息");
            return;
        }
        refreshData();
    }

    private void refreshData() {
        List<Business> selectBusiness = new ArrayList<>();
        for (Business roster : businesses) {
            if (roster.getRealName().contains(keyword) || roster.getAddress().contains(keyword) || roster
                    .getMobilePhone().contains(keyword) || roster.getCusCode().contains(keyword)) {
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
        LogUtil.e(TAG, "personalBusiness.size:" + personalBusiness.size());
        LogUtil.e(TAG, "companyBusiness.size:" + companyBusiness.size());
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
    public void initNet() {
        businessPresenter.getBusinessList(SpSir.getInstance().getProjectId(), -1, taskType);
    }

    @Override
    protected void onSendNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.sendNode(requestBody, buildingIds);
    }

    @Override
    protected void onBackNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.backNode(requestBody, buildingIds);
    }

    @Override
    protected void onReviewNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.reviewNode(requestBody, buildingIds);
    }

    @Override
    protected void onDeleteNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.deleteNode(requestBody, buildingIds);
    }

    @Override
    protected void onRecoverNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.recoverNode(requestBody, buildingIds);
    }

    @Override
    protected void onReminderNode(RequestBody requestBody, String buildingIds) {
        operateNodePresenter.reminderNode(requestBody, buildingIds);
    }

    @Override
    public void onGetBusinessSuccess(TaskInfo taskInfo) {
        businesses = taskInfo.getMyTaskList();
        boolean checkable = getCheckable(taskInfo.getAuth());
        cbSelectAll.setVisibility(checkable ? View.VISIBLE : View.GONE);
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
        return auth.isAllowReview() || auth.isAllowSend() || auth.isAllowFlowBack() || auth.isAllowBanned() || auth
                .isAllowRecover();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    public static void GoActivity(Context context, int taskType, String taskTypeName) {
        Intent intent = new Intent(context, BusinessListActivity.class);
        intent.putExtra("taskType", taskType);
        intent.putExtra("taskTypeName", taskTypeName);
        context.startActivity(intent);
    }

    @Override
    public void onDeleteNodeSuccess(String buildingIds) {
        onOperateSuccess("废弃成功", buildingIds);
    }

    @Override
    public void onSendNodeSuccess(String buildingIds) {
        onOperateSuccess("发送成功", buildingIds);
    }

    @Override
    public void onReviewNodeSuccess(String buildingIds) {
        onOperateSuccess("复查成功", buildingIds);
    }

    @Override
    public void onBackNodeSuccess(String buildingIds) {
        onOperateSuccess("退回成功", buildingIds);
    }

    @Override
    public void onRecoverNodeSuccess(String buildingIds) {
        onOperateSuccess("恢复成功", buildingIds);
    }

    @Override
    public void onReminderNodeSuccess(String buildingIds) {
        ToastUtil.showText("催办成功");
        //TODO 列表体现催办UI
        LogUtil.e(TAG,"buildingIds:"+buildingIds);
        EventBus.getDefault().post(new RefreshReminderEvent(buildingIds));
    }

    @Override
    protected void onOperateSuccess(String msg, String buildingIds) {
        ToastUtil.showText(msg);
        refreshBusiness(buildingIds);
        EventBus.getDefault().post(new RefreshTaskEvent());
    }

    private Set<Business> selectedBusinessList = new HashSet<>();

    public void setRefreshDialogDate() {
        StringBuilder buildingIdsSb = new StringBuilder();
        StringBuilder buildingTypesSb = new StringBuilder();
        StringBuilder buildingStatusIdsSb = new StringBuilder();
        StringBuilder gruopIdsSb = new StringBuilder();
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshBusinessEvent(RefreshBusinessListEvent event) {
        refreshBusiness(event.getBuildingIds());
    }

    public void refreshBusiness(String buildingIds) {
        if (TextUtils.isEmpty(buildingIds)) {
            return;
        }
        List<String> buildingIdList = CollectionUtil.getBuildingIdList(buildingIds);
        if (buildingIdList.size() == 0) {
            return;
        }
        Iterator<Business> it = businesses.iterator();
        while (it.hasNext()) {
            Business next = it.next();
            if (buildingIdList.contains(next.getBuildingId())) {
                it.remove();
            }
        }
        refreshData();
    }

}
