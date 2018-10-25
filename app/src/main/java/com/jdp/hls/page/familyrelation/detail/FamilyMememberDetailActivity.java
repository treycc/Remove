package com.jdp.hls.page.familyrelation.detail;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
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
import com.jdp.hls.util.CheckUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;

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
    @BindView(R.id.et_familyRelation_bookletNum)
    EnableEditText etFamilyRelationBookletNum;
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
    private String houseId;
    private String typeName;
    private String bookletNum;
    private boolean editable;

    @Override
    public void initVariable() {
        familyMember = (FamilyMember) getIntent().getSerializableExtra(Constants.Extra.FAMILYMEMBER);
        bookletId = getIntent().getStringExtra(Constants.Extra.BOOKLETID);
        houseId = getIntent().getStringExtra(Constants.Extra.HOUSEID);
        bookletNum = getIntent().getStringExtra(Constants.Extra.BOOKLETNUM);
        editable = getIntent().getBooleanExtra(Constants.Extra.EDITABLE, true);
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
        if (editable) {
            setRightClick("保存", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    modifyOtherArea();
                }
            });
        }

        spinnerFamilyMememberGender.setBooleanDate(Arrays.asList("男", "女"), selected -> {
            gender = selected;
        });
        spinnerFamilyMememberType.setBooleanDate(Arrays.asList("农业", "非农业"), selected -> {
            isFarmer = selected;
        });
        spinnerFamilyMememberTitle.setDictsItem(familyRelationTitles, dict -> {
            titleTypeId = dict.getTypeId();
            typeName = dict.getTypeName();
        });
        TDict defaultDict = spinnerFamilyMememberTitle.getDefaultDict();
        titleTypeId = defaultDict.getTypeId();
        typeName = defaultDict.getTypeName();
        etFamilyRelationBookletNum.setString(bookletNum == null ? "" : bookletNum);
        if (familyMember != null) {
            etFamilyRelationBookletNum.setString(familyMember.getBookletNum());
            etFamilyRelationName.setString(familyMember.getRealName());
            etFamilyRelationIdcard.setString(familyMember.getIdcard());
            etFamilyRelationBookletNum.setString(familyMember.getBookletNum());
            spinnerFamilyMememberGender.setSelectedIndex(familyMember.isGender() ? 0 : 1);
            spinnerFamilyMememberType.setSelectedIndex(familyMember.getIsFarming() ? 0 : 1);
            spinnerFamilyMememberTitle.setSelectItem(familyMember.getTypeId());
            titleTypeId = familyMember.getTypeId();
            typeName = familyMember.getTypeName();
            gender = familyMember.isGender();
            isFarmer = familyMember.getIsFarming();

            etFamilyRelationBookletNum.setEnabled(editable);
            etFamilyRelationName.setEnabled(editable);
            etFamilyRelationIdcard.setEnabled(editable);
            etFamilyRelationBookletNum.setEnabled(editable);
            spinnerFamilyMememberGender.enable(editable);
            spinnerFamilyMememberType.enable(editable);
            spinnerFamilyMememberTitle.enable(editable);

        }
    }

    private void modifyOtherArea() {
        realName = etFamilyRelationName.getText().toString().trim();
        idcard = etFamilyRelationIdcard.getText().toString().trim();
        bookletNum = etFamilyRelationBookletNum.getText().toString().trim();
        if (!CheckUtil.checkEmpty(realName, "请输入姓名")) {
            return;
        }
        familyMememberDetailPresenter.saveFamilyRemember(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("PersonId", familyMember == null ? "" : familyMember.getPersonId())
                .addFormDataPart("BookletId", familyMember == null ? bookletId : String.valueOf(familyMember
                        .getBookletId()))
                .addFormDataPart("HouseId", houseId)
                .addFormDataPart("RealName", realName)
                .addFormDataPart("TypeId", String.valueOf(titleTypeId))
                .addFormDataPart("Gender", String.valueOf(gender))
                .addFormDataPart("IsFarming", String.valueOf(isFarmer))
                .addFormDataPart("Idcard", idcard)
                .addFormDataPart("BookletNum", bookletNum)
                .build());
    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, FamilyMember familyMember, String houseId, boolean editable) {
        Intent intent = new Intent(context, FamilyMememberDetailActivity.class);
        intent.putExtra(Constants.Extra.FAMILYMEMBER, familyMember);
        intent.putExtra(Constants.Extra.HOUSEID, houseId);
        intent.putExtra(Constants.Extra.EDITABLE, editable);
        context.startActivity(intent);
    }

    public static void goActivity(Context context, String bookletId, String bookletNum, String houseId) {
        Intent intent = new Intent(context, FamilyMememberDetailActivity.class);
        intent.putExtra(Constants.Extra.BOOKLETID, bookletId);
        intent.putExtra(Constants.Extra.BOOKLETNUM, bookletNum);
        intent.putExtra(Constants.Extra.HOUSEID, houseId);
        context.startActivity(intent);
    }

    @Override
    public void onSaveFamilyRememberSuccess(String personId) {
        FamilyMember eventObj = new FamilyMember();
        eventObj.setPersonId(familyMember == null ? personId : familyMember.getPersonId());
        eventObj.setBookletId(familyMember == null ? Integer.valueOf(bookletId) : familyMember.getBookletId());
        eventObj.setRealName(realName);
        eventObj.setIdcard(idcard);
        eventObj.setTypeId(titleTypeId);
        eventObj.setTypeName(typeName);
        eventObj.setIsFarming(isFarmer);
        eventObj.setGender(gender);
        eventObj.setBookletNum(bookletNum);
        if (familyMember != null) {
            EventBus.getDefault().post(new ModifyFamilyMememberEvent(eventObj));
        } else {
            EventBus.getDefault().post(new AddFamilyMememberEvent(eventObj));
        }
        showSuccessAndFinish();
    }
}
