package com.jdp.hls.page.rosterdetail.contacts.list;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.adapter.BaseLvAdapter;
import com.jdp.hls.adapter.ContactsAdapter;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.event.AddContactsEvent;
import com.jdp.hls.event.ModifyContactsEvent;
import com.jdp.hls.event.ModifyMainContactsEvent;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ContactsItem;
import com.jdp.hls.model.entiy.ContactsListDetail;
import com.jdp.hls.model.entiy.Person;
import com.jdp.hls.model.entiy.Roster;
import com.jdp.hls.model.entiy.resultdata.ContactsResult;
import com.jdp.hls.page.personsearch.PersonSearchActivity;
import com.jdp.hls.page.rosterdetail.contacts.detail.ContactsDetailActivity;
import com.jdp.hls.util.BaseListFactory;
import com.jdp.hls.util.DialogUtil;
import com.jdp.hls.util.GoUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.view.PullToBottomListView;
import com.jdp.hls.view.dialog.BaseListDialog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Description:TODO
 * Create Time:2019/1/28 0028 下午 2:15
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ContactsListActivity extends BaseTitleActivity implements ContactsListContract.View {

    @BindView(R.id.plv)
    PullToBottomListView plv;
    private String buildingId;
    private int buildingType;
    @Inject
    ContactsListPresenter contactsListPresenter;
    private ContactsAdapter contactsAdapter;
    private boolean allowEdit;


    @Override
    public void initVariable() {
        EventBus.getDefault().register(this);
        allowEdit = getIntent().getBooleanExtra(Constants.Extra.EDITABLE, false);
        buildingId = getIntent().getStringExtra(Constants.Extra.BUILDING_ID);
        buildingType = getIntent().getIntExtra(Constants.Extra.BUILDING_TYPE, 0);
    }

    @Override
    protected int getContentView() {
        return R.layout.common_lv_8;
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
        contactsListPresenter.attachView(this);
    }

    @Override
    protected String getContentTitle() {
        return "产权人列表";
    }

    @Override
    protected void initView() {
        plv.setAdapter(contactsAdapter = new ContactsAdapter(this, null,allowEdit));
        contactsAdapter.setOnItemOperListener(new BaseLvAdapter.OnItemOperListener<ContactsItem>() {
            @Override
            public void onItemDelete(ContactsItem contacts, int position) {
                DialogUtil.showDoubleDialog(ContactsListActivity.this, "是否确定删除该项?", (dialog, which) -> {
                    contactsListPresenter.deleteContacts(buildingId, contacts.getPersonId(), buildingType, position);
                });
            }

            @Override
            public void onItemClick(ContactsItem item) {
                ContactsDetailActivity.goActivity(ContactsListActivity.this, buildingId, item.getPersonId(),
                        buildingType);
            }

            @Override
            public void onAction1(ContactsItem contactsItem, int position) {
                // 设为主联系人
                if (!allowEdit) {
                    return;
                }

                if (contactsItem.getIsMainContact() == 1) {
                    return;
                }
                contactsListPresenter.setMainContacts(buildingId, contactsItem.getPersonId(), buildingType, position);
                postModifyMainContacts(contactsItem);
            }
        });
    }

    private void postModifyMainContacts(ContactsItem contactsItem) {
        Roster roster = new Roster();
        roster.setHouseId(buildingId);
        roster.setMobilePhone(contactsItem.getMobilePhone());
        roster.setRealName(contactsItem.getRealName());
        roster.setEnterprise(buildingType == 1);
        EventBus.getDefault().post(new ModifyMainContactsEvent(roster));
    }


    @Override
    protected void initData() {
        initAddDialog();
    }

    private void initAddDialog() {
        BaseListDialog createTypeDialog = new BaseListDialog(this, BaseListFactory.getCreateTypeList(), "添加方式");
        createTypeDialog.setOnDisPlayItemClickListener(displayItem -> {
            switch (displayItem.getCode()) {
                case Status.AddType.IMPORT:
                    GoUtil.goActivityForResult(this, PersonSearchActivity.class, Constants.RequestCode.IMPORT_PERSON);
                    break;
                case Status.AddType.ADD:
                    ContactsDetailActivity.goActivity(this, buildingId, "", buildingType);
                    break;
            }
        });
        if (allowEdit) {
            setRightClick("添加", new NoDoubleClickListener() {
                @Override
                public void onNoDoubleClick(View v) {
                    createTypeDialog.show();
                }
            });
        }

    }

    @Override
    public void initNet() {
        contactsListPresenter.getMainPersonList(buildingId, buildingType);
    }

    public static void goActivity(Activity context, String buildingId, int buildingType, boolean allowEdit) {
        Intent intent = new Intent(context, ContactsListActivity.class);
        intent.putExtra(Constants.Extra.BUILDING_ID, buildingId);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.EDITABLE, allowEdit);
        context.startActivityForResult(intent, Constants.RequestCode.ContactsList);
    }

    @Override
    public void onGetMainPersonListSuccess(ContactsListDetail contactsDetail) {
        boolean allowEdit = contactsDetail.isAllowEdit();
        if (allowEdit) {
            initAddDialog();
        }
        setListView(contactsDetail.getPersonList(), contactsAdapter, allowEdit);
    }

    @Override
    public void onDeleteContactsSuccess(int position) {
        //如果删除主联系人则刷新列表为空
        contactsAdapter.removeItem(position);
        if (contactsAdapter.getCount() == 0) {
            showEmptyCallback();
        }
    }

    @Override
    public void onSetMainContactsSuccess(int position) {
        contactsAdapter.setMainContacts(position);
        ContactsItem contactsItem = (ContactsItem) contactsAdapter.getItem(position);
        EventBus.getDefault().post(new ModifyContactsEvent(contactsItem));
    }

    @Override
    public void onImportMainContactsSuccess(ContactsResult contactsResult, ContactsItem contactsItem) {
        showSuccessCallback();
        contactsItem.setIsMainContact(contactsResult.getIsMainContact());
        if (contactsAdapter.getCount() == 0 && contactsItem.getIsMainContact() == 1) {
            //TODO 如果是第一住宅，则是主联系人，要刷新列表
            Roster roster = new Roster();
            roster.setHouseId(buildingId);
            roster.setEnterprise(buildingType==1);
            roster.setRealName(contactsItem.getRealName());
            roster.setMobilePhone(contactsItem.getMobilePhone());
            EventBus.getDefault().post(new ModifyMainContactsEvent(roster));
        }
        contactsAdapter.addFirst(contactsItem);

    }

    @Override
    public boolean ifRegisterLoadSir() {
        return true;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void addContacts(AddContactsEvent event) {
        showSuccessCallback();
        contactsAdapter.addFirst(event.getContactsItem());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void modifyContacts(ModifyContactsEvent event) {
        contactsAdapter.modifyItem(event.getContactsItem());
    }

    private void setResultData() {
        Intent intent = new Intent();
        intent.putExtra(Constants.Extra.Number, contactsAdapter.getCount());
        setResult(Activity.RESULT_OK, intent);
    }

    @Override
    public void onBackPressed() {
        onBack();
    }

    @Override
    protected void onBack() {
        setResultData();
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            switch (requestCode) {
                case Constants.RequestCode.IMPORT_PERSON:
                    Person person = (Person) data.getSerializableExtra("person");
                    ContactsItem contactsItem = new ContactsItem();
                    contactsItem.setPersonId(person.getPersonId());
                    contactsItem.setMobilePhone(person.getMobilePhone());
                    contactsItem.setRealName(person.getRealName());
                    contactsListPresenter.importMainContacts(buildingId, contactsItem.getPersonId(), buildingType,
                            contactsItem);
                    break;
            }
        }
    }
}
