package com.jdp.hls.page.admin.group.detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.GroupDetailAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddGroupEvent;
import com.jdp.hls.event.ModifyGroupEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.Group;
import com.jdp.hls.model.entiy.GroupDetail;
import com.jdp.hls.model.entiy.Member;
import com.jdp.hls.page.admin.group.member.MemberSelectActivity;
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.FixedListView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnItemClick;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/11/21 0021 下午 6:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class GroupDetailActivity extends BaseTitleActivity implements GroupDetailContract.View {

    @BindView(R.id.et_groupName)
    EnableEditText etGroupName;
    @BindView(R.id.flv)
    FixedListView flv;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;
    private String projectId;
    private int groupId;
    @Inject
    GroupDetailPresenter groupDetailPresenter;
    private GroupDetailAdapter groupDetailAdapter;

    @OnItemClick({R.id.flv})
    public void itemClick(AdapterView<?> adapterView, View view, int position, long id) {
        Member member = (Member) adapterView.getItemAtPosition(position);
        MemberSelectActivity.goActivity(this, projectId, member.getCompanyTypeId());

    }

    @Override
    public void initVariable() {
        projectId = getIntent().getStringExtra(Constants.Extra.PROJECTID);
        groupId = getIntent().getIntExtra(Constants.Extra.GROUPID, 0);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_group_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        groupDetailPresenter.attachView(this);

    }

    @Override
    protected String getContentTitle() {
        return "小组信息";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                List<Member> memberList = groupDetailAdapter.getData();
                StringBuilder employeeIdSb = new StringBuilder();
                StringBuilder accountTypeSb = new StringBuilder();
                StringBuilder companyTypeIdSb = new StringBuilder();
                for (int i = 0; i < memberList.size(); i++) {
                    if (i != memberList.size() - 1) {
                        employeeIdSb.append(memberList.get(i).getEmployeeId());
                        employeeIdSb.append("#");
                        accountTypeSb.append(memberList.get(i).getAccountType());
                        accountTypeSb.append("#");
                        companyTypeIdSb.append(memberList.get(i).getCompanyTypeId());
                        companyTypeIdSb.append("#");
                    } else {
                        employeeIdSb.append(memberList.get(i).getEmployeeId());
                        accountTypeSb.append(memberList.get(i).getAccountType());
                        companyTypeIdSb.append(memberList.get(i).getCompanyTypeId());
                    }
                }
                String groupName = etGroupName.getText().toString().trim();
                String remark = etRemark.getText().toString().trim();
                if (CheckUtil.checkEmpty(groupName, "请输入小组名称")) {
                    groupDetailPresenter.saveGroupInfo(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("GroupId", String.valueOf(groupId))
                            .addFormDataPart("ProjectId", projectId)
                            .addFormDataPart("GroupName", groupName)
                            .addFormDataPart("Remark", remark)
                            .addFormDataPart("EmployeeId", employeeIdSb.toString())
                            .addFormDataPart("AccountType", accountTypeSb.toString())
                            .addFormDataPart("CompanyTypeId", companyTypeIdSb.toString())
                            .build());
                }
            }
        });
        groupDetailAdapter = new GroupDetailAdapter(this, null);
        flv.setAdapter(groupDetailAdapter);
    }

    @Override
    public void initNet() {
        groupDetailPresenter.getGroupDetail(projectId, groupId);
    }

    public static void goActivity(Context context, String projectId, int groupId) {
        Intent intent = new Intent(context, GroupDetailActivity.class);
        intent.putExtra(Constants.Extra.PROJECTID, projectId);
        intent.putExtra(Constants.Extra.GROUPID, groupId);
        context.startActivity(intent);
    }

    @Override
    public void onGetGroupDetailSuccess(GroupDetail groupDetail) {
        etGroupName.setString(groupDetail.getGroupName());
        etRemark.setString(groupDetail.getRemark());
        List<Member> memberList = groupDetail.getLstMember();
        if (memberList != null && memberList.size() > 0) {
            groupDetailAdapter.setData(memberList);
        }
    }

    @Override
    public void onSaveGroupInfoSuccess(Group group) {
        if (groupId == 0) {
            EventBus.getDefault().post(new AddGroupEvent(group));
        } else {
            EventBus.getDefault().post(new ModifyGroupEvent(group));
        }
        showSuccessToastAndFinish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.MEMBERSELECT:
                    Member member = (Member) data.getSerializableExtra(Constants.Extra.Member);
                    groupDetailAdapter.modifyMember(member);
                    break;
            }
        }
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
