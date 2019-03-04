package com.jdp.hls.page.projects;

import android.text.Editable;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.AreaSelectAdapter;
import com.jdp.hls.adapter.ProjectSearchAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.event.RefreshRostersEvent;
import com.jdp.hls.event.SwitchProjectEvent;
import com.jdp.hls.greendaobean.Area;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.ProjectAreaInfo;
import com.jdp.hls.model.entiy.AreaSelectorItem;
import com.jdp.hls.model.entiy.Project;
import com.jdp.hls.page.home.HomeActivity;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.InputMethodManagerUtil;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.util.SpSir;
import com.jdp.hls.util.ToastUtil;
import com.jdp.hls.view.FixedGridView;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.RefreshSwipeRefreshLayout;
import com.jdp.hls.view.dialog.AreaListDialog;
import com.jdp.hls.view.dialog.BaseWheelListDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemClick;
import okhttp3.MultipartBody;

/**
 * Description:项目列表
 * Create Time:2018/7/26 0026 下午 3:34
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ProjectListActivity extends BaseTitleActivity implements ProjectsAreaContract.View {
    @BindView(R.id.plv)
    PullToBottomListView plv;
    @BindView(R.id.srl)
    RefreshSwipeRefreshLayout rsrl;
    @BindView(R.id.et_keyword)
    EditText etKeyword;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @Inject
    ProjectsAreaPresenter projectsPresenter;
    @BindView(R.id.fgv)
    FixedGridView fgv;
    private ProjectSearchAdapter projectSearchAdapter;
    private AreaSelectAdapter areaSelectorAdapter;
    private List<AreaSelectorItem> areaSelectorItemList = new ArrayList<>();
    private AreaListDialog areaListDialog;
    private Map<Integer, List<Area>> authList = new HashMap<>();

    @OnClick({R.id.iv_clear})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.iv_clear:
                etKeyword.setText("");
                break;
        }
    }

    @OnItemClick({R.id.plv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Project project = (Project) adapterView.getItemAtPosition(position);
        projectsPresenter.switchProject(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("ProjectId", project.getProjectId())
                .build(), project);
    }

    @Override
    public void initVariable() {

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_project_search_list;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        projectsPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "项目选择";
    }


    @Override
    protected void initView() {
        projectSearchAdapter = new ProjectSearchAdapter(this, null);
        plv.setAdapter(projectSearchAdapter);
    }

    @Override
    public void showLoading() {
        rsrl.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        rsrl.setRefreshing(false);
    }

    @Override
    protected void initData() {
        etKeyword.setHint("请输入项目名称/负责人/地址");
        etKeyword.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                projectSearchAdapter.onSearch(s.toString());
                ivClear.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });
        rsrl.stepRefresh(this);

        areaListDialog = new AreaListDialog(ProjectListActivity.this);
        fgv.setAdapter(areaSelectorAdapter = new AreaSelectAdapter(this, null));
        fgv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AreaSelectorItem item = (AreaSelectorItem) parent.getItemAtPosition(position);
                if (!item.isVisible()) {
                    return;
                }
//                int parentId = areaSelectorAdapter.getParentId(item.getParentId(), position);
                AreaSelectorItem parentAreaItem = areaSelectorAdapter.getParent(position);
                if (parentAreaItem.getAreaNumber() == 0) {
                    ToastUtil.showText("请先选择上级区域");
                    return;
                }


                switch (item.getAreaLevel()) {
                    case Status.AreaLevel.PROVINCE:
                    case Status.AreaLevel.CITY:
                    case Status.AreaLevel.AREA:
                    case Status.AreaLevel.STREET:
//                        if (areaListDialog.hasData(parentId)) {
//                            areaListDialog.fillData(item.getAreaLevel(), parentId, item.getAreaNumber());
//                            areaListDialog.show();
//                        } else {
//                            ToastUtil.showText("无区域数据");
//                        }

                        List<Area> areaList = authList.get(parentAreaItem.getAreaNumber());
                        if (areaList == null) {
                            //没选过,从网络获取
                            projectsPresenter.getAuthAreaList(parentAreaItem.getAreaNumber(), parentAreaItem
                                    .getAreaLevel(),item);
                        } else if (areaList.size() == 0) {
                            //无区域列表
                            ToastUtil.showText("无区域数据");
                        } else {
                            //有区域列表，填充数据
                            areaListDialog.fillData(areaList, item.getAreaNumber());
                            areaListDialog.show();
                        }


                        LogUtil.e(TAG, String.format("ParentId=%s AreaLevel=%s", parentAreaItem.getAreaNumber(),
                                parentAreaItem.getAreaLevel()));


                        break;
                }
            }
        });

    }


    @Override
    public void initNet() {
        projectsPresenter.getProjects(SpSir.getInstance().getEmployeeId());
    }


    @Override
    public void onGetProjectsSuccess(ProjectAreaInfo projectAreaInfo) {
        String keyword = etKeyword.getText().toString().trim();
        setSearchListView(projectAreaInfo.getProjectList(), projectSearchAdapter, keyword);

        if (projectAreaInfo.isAvailable()) {
            fgv.setVisibility(View.VISIBLE);
            areaSelectorAdapter.setData(projectAreaInfo.getAuthoritys());
            areaListDialog.setOnConfirmListener((BaseWheelListDialog.OnConfirmListener<Area>)
                    area -> {
                        switch (area.getLevel()) {
                            case Status.AreaLevel.PROVINCE:
                            case Status.AreaLevel.CITY:
                            case Status.AreaLevel.AREA:
                            case Status.AreaLevel.STREET:
                                areaSelectorAdapter.resetData(area);
                                projectSearchAdapter.filterArea(area);
                                break;
                        }
                    });
        }
    }

    @Override
    public void onSwitchProjectSuccess(Project project) {
        SpSir.getInstance().setProjectId(project.getProjectId());
        SpSir.getInstance().setProjectName(project.getProjectName());
        EventBus.getDefault().post(new RefreshRostersEvent());
        EventBus.getDefault().post(new SwitchProjectEvent());
        finish();
    }

    @Override
    public void onGetAuthAreaListSuccess(List<AreaSelectorItem> areaSelectorItemList,AreaSelectorItem areaItem) {
        LogUtil.e(TAG, "areaSelectorItemList=" + areaSelectorItemList.size());
        if (areaSelectorItemList.size() > 0) {
            List<Area> areaList = new ArrayList<>();
            for (AreaSelectorItem areaSelectorItem : areaSelectorItemList) {
                Area area = new Area();
                area.setLevel(areaSelectorItem.getAreaLevel());
                area.setRegionId(Long.valueOf(areaSelectorItem.getAreaNumber()));
                area.setRegionName(areaSelectorItem.getAreaName());
                areaList.add(0, area);
            }
            Area noLimitArea = new Area();
            noLimitArea.setLevel(areaSelectorItemList.get(0).getAreaLevel());
            noLimitArea.setRegionId(Long.valueOf(0));
            noLimitArea.setRegionName("不限");
            areaList.add(0, noLimitArea);
            authList.put(areaItem.getParentId(),areaList);
            areaListDialog.fillData(areaList, areaItem.getAreaNumber());
            areaListDialog.show();
        } else {
            authList.put(areaItem.getParentId(),new ArrayList<>());
            ToastUtil.showText("无区域数据");
        }

    }

    @Override
    protected void onDestroy() {
        InputMethodManagerUtil.fixInputMethodManagerLeak(this);
        super.onDestroy();
    }
}
