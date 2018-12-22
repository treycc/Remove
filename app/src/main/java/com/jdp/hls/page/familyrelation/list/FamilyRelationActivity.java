package com.jdp.hls.page.familyrelation.list;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.FamilyMemberAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.AddFamilyMememberEvent;
import com.jdp.hls.event.ModifyFamilyMememberEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.FamilyMember;
import com.jdp.hls.model.entiy.FamilyRelation;
import com.jdp.hls.other.file.FileConfig;
import com.jdp.hls.page.familyrelation.detail.FamilyMememberDetailActivity;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.FixedListView;
import com.jdp.hls.view.PreviewRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Description:家庭关系
 * Create Time:2018/9/14 0014 下午 2:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FamilyRelationActivity extends BaseTitleActivity implements FamilyRelationContract.View {
    @BindView(R.id.et_familyRelation_num)
    EnableEditText etFamilyRelationNum;
    @BindView(R.id.lv_familyRelation)
    FixedListView lvFamilyRelation;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.et_remark)
    EnableEditText etRemark;

    private List<FamilyMember> familyMembers = new ArrayList<>();
    private String houseId;
    private String bookletId;
    private boolean editable;

    @Inject
    FamilyRelationPresenter familyRelationPresenter;
    private FamilyMemberAdapter familyMemberAdapter;
    private String bookletNum;

    @OnClick({R.id.tv_save, R.id.tv_add, R.id.ll_back})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.tv_save:
                modifyFamilyRelation();
                break;
            case R.id.tv_add:
                FamilyMememberDetailActivity.goActivity(FamilyRelationActivity.this, bookletId, bookletNum,
                        houseId);
                break;
            case R.id.ll_back:
                finish();
                break;
            default:
                break;
        }
    }

    private void modifyFamilyRelation() {
        String bookletNum = etFamilyRelationNum.getText().toString().trim();
        String remark = etRemark.getText().toString().trim();
        familyRelationPresenter.modifyFamilyRelation(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("BookletId", bookletId)
                .addFormDataPart("BookletNum", bookletNum)
                .addFormDataPart("Remark", remark)
                .build());
    }

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        houseId = getIntent().getStringExtra(Constants.Extra.HOUSEID);
        editable = getIntent().getBooleanExtra(Constants.Extra.EDITABLE, false);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_family_relation;
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
        return "家庭关系";
    }

    @Override
    protected void initView() {
        familyRelationPresenter.attachView(this);
        familyMemberAdapter = new FamilyMemberAdapter(this, familyMembers);
        lvFamilyRelation.setAdapter(familyMemberAdapter);
    }

    @Override
    protected void initData() {
        if (editable) {
            setRightClick("增加", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    FamilyMememberDetailActivity.goActivity(FamilyRelationActivity.this, bookletId, bookletNum,
                            houseId);
                }
            });
        }
        familyMemberAdapter.setOnDeleteFamilyMemberListener(new FamilyMemberAdapter.OnDeleteFamilyMemberListener() {
            @Override
            public void onDeleteFamilyMember(String personId, String bookletId, int position) {
                DialogUtil.showDoubleDialog(FamilyRelationActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    familyRelationPresenter.deleteFamilyRemember(new MultipartBody.Builder().setType(MultipartBody.FORM)
                            .addFormDataPart("PersonId", personId)
                            .addFormDataPart("BookletId", bookletId)
                            .build(), position);
                });
            }

            @Override
            public void onFamilyMemberClick(FamilyMember familyMember) {
                FamilyMememberDetailActivity.goActivity(FamilyRelationActivity.this, familyMember, houseId, editable);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        rvPhotoPreview.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    public void initNet() {
        familyRelationPresenter.getFamilyRelation(houseId);
    }

    @Override
    public void onGetFamilyRelationSuccess(FamilyRelation familyRelation) {
        bookletId = String.valueOf(familyRelation.getBookletId());
        bookletNum = familyRelation.getBookletNum();
        etFamilyRelationNum.setText(bookletNum);
        etRemark.setText(familyRelation.getRemark());
        List<FamilyMember> familyMemberList = familyRelation.getLstPerons();
        if (familyMemberList != null && familyMemberList.size() > 0) {
            familyMemberAdapter.setEditableData(familyMemberList, editable);
        }
        rvPhotoPreview.setData(familyRelation.getFiles(), new FileConfig(Status.FileType.BUSINESS_IDCARD, houseId,
                Status.BuildingTypeStr.PERSONAL), editable);

        etFamilyRelationNum.setEnabled(editable);
        etRemark.setEnabled(editable);
    }

    @Override
    public void onDeleteFamilyRememberSuccess(int position) {
        familyMemberAdapter.removeItem(position);
    }

    @Override
    public void onModifyFamilyRelationSuccess() {
        showSuccessDialogAndFinish();
    }

    public static void goActivity(Context context, String houseId, boolean editable) {
        Intent intent = new Intent(context, FamilyRelationActivity.class);
        intent.putExtra(Constants.Extra.HOUSEID, houseId);
        intent.putExtra(Constants.Extra.EDITABLE, editable);
        context.startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addItem(AddFamilyMememberEvent event) {
        FamilyMember familyMember = event.getFamilyMember();
        familyMemberAdapter.addFirst(familyMember);
        modifyBookletNum(familyMember);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyItem(ModifyFamilyMememberEvent event) {
        FamilyMember familyMember = event.getFamilyMember();
        familyMemberAdapter.modify(familyMember);
        modifyBookletNum(familyMember);

    }

    private void modifyBookletNum(FamilyMember familyMember) {
        bookletNum = familyMember.getBookletNum();
        etFamilyRelationNum.setText(bookletNum);
        familyMemberAdapter.modifyBookletNum(familyMember);
    }

    @Override
    protected boolean ifHideTitle() {
        return true;
    }

}
