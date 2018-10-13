package com.jdp.hls.page.familyrelation.list;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.FamilyMemberAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.event.AddFamilyMememberEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AddDecorationEvent;
import com.jdp.hls.model.entiy.FamilyMember;
import com.jdp.hls.model.entiy.FamilyRelation;
import com.jdp.hls.model.entiy.ModifyDecorationEvent;
import com.jdp.hls.page.familyrelation.FamilyRememberActivity;
import com.jdp.hls.page.familyrelation.detail.FamilyMememberDetailActivity;
import com.jdp.hls.page.otherarea.list.OtherAreaListActivity;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PreviewRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:家庭关系
 * Create Time:2018/9/14 0014 下午 2:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FamilyRelationActivity extends BaseTitleActivity implements FamilyRelationContract.View {
    @BindView(R.id.tv_familyRelation_num)
    TextView tvFamilyRelationNum;
    @BindView(R.id.lv_familyRelation)
    ListView lvFamilyRelation;
    @BindView(R.id.rv_photo_preview)
    PreviewRecyclerView rvPhotoPreview;
    @BindView(R.id.ll_photo_preview)
    LinearLayout llPhotoPreview;

    private List<FamilyMember> familyMembers = new ArrayList<>();
    private String bookletId;

    @Inject
    FamilyRelationPresenter familyRelationPresenter;
    private FamilyMemberAdapter familyMemberAdapter;

    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        bookletId = getIntent().getStringExtra(Constants.Extra.BOOKLETID);
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
        tvFamilyRelationNum.setText(bookletId);
        setRightClick("增加", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                FamilyMememberDetailActivity.goActivity(FamilyRelationActivity.this, bookletId);
            }
        });
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
                FamilyMememberDetailActivity.goActivity(FamilyRelationActivity.this, familyMember);
            }
        });
    }

    @Override
    protected boolean ifRegisterLoadSir() {
        return true;
    }

    @Override
    protected void initNet() {
        familyRelationPresenter.getFamilyRelation(bookletId);
    }

    @Override
    public void onGetFamilyRelationSuccess(FamilyRelation familyRelation) {
        List<FamilyMember> familyMemberList = familyRelation.getLstPerons();
        if (familyMemberList != null && familyMemberList.size() > 0) {
            familyMemberAdapter.setData(familyMemberList);
        }
    }

    @Override
    public void onDeleteFamilyRememberSuccess(int position) {
        familyMemberAdapter.removeItem(position);
    }

    public static void goActivity(Context context, String bookletId) {
        Intent intent = new Intent(context, FamilyRelationActivity.class);
        intent.putExtra(Constants.Extra.BOOKLETID, bookletId);
        context.startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addItem(AddFamilyMememberEvent event) {
        familyMemberAdapter.addFirst(event.getFamilyMember());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyItem(AddFamilyMememberEvent event) {
        familyMemberAdapter.modify(event.getFamilyMember());
    }
}
