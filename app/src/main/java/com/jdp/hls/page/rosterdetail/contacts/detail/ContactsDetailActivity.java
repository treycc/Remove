package com.jdp.hls.page.rosterdetail.contacts.detail;

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
import com.jdp.hls.event.AddContactsEvent;
import com.jdp.hls.event.ModifyContactsEvent;
import com.jdp.hls.event.ModifyMainContactsEvent;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ContactsItem;
import com.jdp.hls.model.entiy.ContactsDetail;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.resultdata.ContactsResult;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import lib.kingja.switchbutton.SwitchMultiButton;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2019/1/28 0028 下午 2:04
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsDetailActivity extends BaseTitleActivity implements ContactsDetailContract.View {
    @Inject
    ContactsDetailPresenter contactsDetailPresenter;
    @BindView(R.id.et_name)
    EnableEditText etName;
    @BindView(R.id.et_mobile)
    EnableEditText etMobile;
    @BindView(R.id.et_idcard)
    EnableEditText etIdcard;
    @BindView(R.id.spinner_politicalTitle)
    KSpinner spinnerPoliticalTitle;
    @BindView(R.id.smb_gender)
    SwitchMultiButton smbGender;
    private int buildingType;
    private String personId;
    private String buildingId;
    private boolean gender = true;
    private List<TDict> politicalTitleList;
    private int politicalTitle;
    private String idcard;
    private String mobile;
    private String realName;

    @Override
    public void initVariable() {
        politicalTitleList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.SOCIAL_RELATION);
        buildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
        personId = getIntent().getStringExtra(Constants.Extra.PersonId);
        buildingType = getIntent().getIntExtra(Constants.Extra.BUILDING_TYPE, 0);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_contacts_detail;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        contactsDetailPresenter.attachView(this);

    }

    @Override
    protected String getContentTitle() {
        return "人员信息";
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        smbGender.setOnSwitchListener((position, tabText) -> gender = position == 0);
        spinnerPoliticalTitle.setDicts(politicalTitleList, typeId -> {
            politicalTitle = typeId;
        });
        politicalTitle = spinnerPoliticalTitle.getDefaultTypeId();

    }

    private void saveData() {
        idcard = etIdcard.getText().toString().trim();
        mobile = etMobile.getText().toString().trim();
        realName = etName.getText().toString().trim();
        contactsDetailPresenter.saveContactsDetail(new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("buildingType", String.valueOf(buildingType))
                .addFormDataPart("HouseId", buildingId)
                .addFormDataPart("PersonId", TextUtils.isEmpty(personId) ? "" : personId)
                .addFormDataPart("RealName", realName)
                .addFormDataPart("Gender", String.valueOf(gender))
                .addFormDataPart("MobilePhone", mobile)
                .addFormDataPart("Idcard", idcard)
                .addFormDataPart("PoliticalTitle", String.valueOf(politicalTitle))
                .build());
    }

    @Override
    public void initNet() {
        if (TextUtils.isEmpty(personId)) {
            showSuccessCallback();
            allowSave();
            //增加
        } else {
            //修改
            contactsDetailPresenter.getContactsDetail(personId);
        }
    }

    public static void goActivity(Context context, String buildingId, String personId, int buildingType) {
        Intent intent = new Intent(context, ContactsDetailActivity.class);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        intent.putExtra(Constants.Extra.PersonId, personId);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        context.startActivity(intent);
    }

    @Override
    public void onGetContactsDetailSuccess(ContactsDetail contactsDetail) {
        etName.setString(contactsDetail.getRealName());
        etMobile.setString(contactsDetail.getMobilePhone());
        etIdcard.setString(contactsDetail.getIdcard());
        gender = contactsDetail.isGender();
        smbGender.setSelectedTab(gender ? 0 : 1);
        politicalTitle = contactsDetail.getPoliticalTitle();
        spinnerPoliticalTitle.setSelectItem(politicalTitle);
        boolean allowEdit = contactsDetail.isAllowEdit();
        etName.setEnabled(allowEdit);
        etMobile.setEnabled(allowEdit);
        etIdcard.setEnabled(allowEdit);
        smbGender.setEnabled(allowEdit);
        spinnerPoliticalTitle.enable(allowEdit);

        if (allowEdit) {
            allowSave();
        }
    }

    private void allowSave() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                saveData();
            }
        });
    }

    @Override
    public void onSaveContactsDetailSuccess(ContactsResult contactsResult) {
        ContactsItem contacts = new ContactsItem();
        contacts.setPersonId(contactsResult.getPersonId());
        contacts.setIsMainContact(contactsResult.getIsMainContact());
        contacts.setMobilePhone(mobile);
        contacts.setRealName(realName);
        if (TextUtils.isEmpty(personId)) {
            //增加
            EventBus.getDefault().post(new AddContactsEvent(contacts));
        } else {
            //修改
            EventBus.getDefault().post(new ModifyContactsEvent(contacts));

        }
        if (contactsResult.getIsMainContact() == 1) {
            Roster roster = new Roster();
            roster.setHouseId(buildingId);
            roster.setEnterprise(buildingType==1);
            roster.setRealName(realName);
            roster.setMobilePhone(mobile);
            EventBus.getDefault().post(new ModifyMainContactsEvent(roster));
        }
        showSuccessDialogAndFinish();
    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }
}
