package com.jdp.hls.activity;

import android.content.Context;
import android.view.View;
import android.widget.ListView;

import com.jdp.hls.R;
import com.jdp.hls.adapter.CommonAdapter;
import com.jdp.hls.adapter.ViewHolder;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.FamilyMember;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Description:家庭关系
 * Create Time:2018/9/14 0014 下午 2:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FamilyRelationActivity extends BaseTitleActivity {
    @BindView(R.id.lv_family_relation)
    ListView lvFamilyRelation;

    private List<FamilyMember> familyMembers = new ArrayList<>();

    @Override
    public void initVariable() {
        for (int i = 0; i < 10; i++) {
            familyMembers.add(new FamilyMember());
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_family_relation;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {

    }

    @Override
    protected String getContentTitle() {
        return "家庭关系";
    }

    @Override
    protected void initView() {
        FamilyMemberAdapter familyMemberAdapter = new FamilyMemberAdapter(this, familyMembers, R.layout
                .item_family_relation);
        lvFamilyRelation.setAdapter(familyMemberAdapter);
    }

    @Override
    protected void initData() {
        setRightClick("增加", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                GoUtil.goActivity(FamilyRelationActivity.this,FamilyRememberActivity.class);
            }
        });
    }

    @Override
    protected void initNet() {

    }

    class FamilyMemberAdapter extends CommonAdapter<FamilyMember> {
        public FamilyMemberAdapter(Context context, List<FamilyMember> datas, int itemLayoutId) {
            super(context, datas, itemLayoutId);
        }

        @Override
        public void convert(ViewHolder helper, FamilyMember item) {
//            helper.setText(R.id.tv_roster_address, item.getHouseAddress());
//            helper.setText(R.id.tv_roster_name, item.getRealName());
//            helper.setText(R.id.tv_roster_phone, item.getMobilePhone());
        }

    }
}
