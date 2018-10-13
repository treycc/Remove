package com.jdp.hls.page.familyrelation.detail;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.event.AddFamilyMememberEvent;
import com.jdp.hls.event.ModifyFamilyMememberEvent;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.FamilyMember;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;

/**
 * Description:家庭成员-详情
 * Create Time:2018/10/9 0009 上午 10:54
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class FamilyMememberDetailActivity extends BaseTitleActivity implements FamilyMememberDetailContract.View {
    @BindView(R.id.et_familyRelation_name)
    EnableEditText etFamilyRelationName;
    @BindView(R.id.spinner_familyMemember_title)
    KSpinner spinnerFamilyMememberTitle;
    @BindView(R.id.spinner_familyMemember_gender)
    KSpinner spinnerFamilyMememberGender;
    @BindView(R.id.spinner_familyMemember_type)
    KSpinner spinnerFamilyMememberType;
    @BindView(R.id.et_familyRelation_idcard)
    EnableEditText etFamilyRelationIdcard;
    private FamilyMember familyMember;
    private String bookletId;
    private boolean gender = true;
    private boolean isFarmer = true;
    private int titleTypeId;

    @Inject
    FamilyMememberDetailPresenter familyMememberDetailPresenter;
    private List<TDict> familyRelationTitles;
    private String realName;
    private String idcard;

    @Override
    public void initVariable() {
        familyMember = (FamilyMember) getIntent().getSerializableExtra(Constants.Extra.FAMILYMEMBER);
        bookletId = getIntent().getStringExtra(Constants.Extra.BOOKLETID);
        familyRelationTitles = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.FAMILY_RELATION);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_family_memember_detail;
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
        return "详情";
    }

    @Override
    protected void initView() {
        familyMememberDetailPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                modifyOtherArea();
            }
        });
        spinnerFamilyMememberGender.setBooleanDate(Arrays.asList("男", "女"), selected -> {
            gender = selected;
        });
        spinnerFamilyMememberType.setBooleanDate(Arrays.asList("农业", "非农业"), selected -> {
            isFarmer = selected;
        });
        spinnerFamilyMememberTitle.setDicts(familyRelationTitles, typeId -> {
            titleTypeId = typeId;
        });
        if (familyMember != null) {
            etFamilyRelationName.setString(familyMember.getRealName());
            etFamilyRelationIdcard.setString(familyMember.getIdcard());
            spinnerFamilyMememberGender.setSelectedIndex(familyMember.isGender() ? 0 : 1);
            spinnerFamilyMememberType.setSelectedIndex(familyMember.getIsFarming() ? 0 : 1);
            spinnerFamilyMememberTitle.setSelectItem(familyMember.getTypeId());
            titleTypeId = familyMember.getTypeId();
            gender = familyMember.isGender();
            isFarmer = familyMember.getIsFarming();
        }
    }

    private void modifyOtherArea() {
        realName = etFamilyRelationName.getText().toString().trim();
        idcard = etFamilyRelationIdcard.getText().toString().trim();
        familyMememberDetailPresenter.saveFamilyRemember(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("PersonId", familyMember == null ? "" : familyMember.getPersonId())
                .addFormDataPart("BookletId", familyMember == null ? bookletId : String.valueOf(familyMember
                        .getBookletId()))
                .addFormDataPart("RealName", realName)
                .addFormDataPart("TypeId", String.valueOf(titleTypeId))
                .addFormDataPart("Gender", String.valueOf(gender))
                .addFormDataPart("IsFarming", String.valueOf(isFarmer))
                .addFormDataPart("Idcard", idcard)
                .build());
    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, FamilyMember familyMember) {
        Intent intent = new Intent(context, FamilyMememberDetailActivity.class);
        intent.putExtra(Constants.Extra.FAMILYMEMBER, familyMember);
        context.startActivity(intent);
    }

    public static void goActivity(Context context, String bookletId) {
        Intent intent = new Intent(context, FamilyMememberDetailActivity.class);
        intent.putExtra(Constants.Extra.BOOKLETID, bookletId);
        context.startActivity(intent);
    }

    @Override
    public void onSaveFamilyRememberSuccess() {
        FamilyMember eventObj = new FamilyMember();
        eventObj.setPersonId(familyMember == null ? "" : familyMember.getPersonId());
        eventObj.setBookletId(familyMember == null ? Integer.valueOf(bookletId) : familyMember.getBookletId());
        eventObj.setRealName(realName);
        eventObj.setIdcard(idcard);
        eventObj.setTypeId(titleTypeId);
        eventObj.setIsFarming(isFarmer);
        eventObj.setGender(gender);
        if (familyMember != null) {
            EventBus.getDefault().post(new ModifyFamilyMememberEvent(eventObj));
        } else {
            EventBus.getDefault().post(new AddFamilyMememberEvent(eventObj));
        }
        showSuccessAndFinish();
    }
}
