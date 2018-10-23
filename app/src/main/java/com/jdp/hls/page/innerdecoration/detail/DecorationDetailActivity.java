package com.jdp.hls.page.innerdecoration.detail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseTitleActivity;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.dao.DBManager;
import com.jdp.hls.greendaobean.TDict;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.AddDecorationEvent;
import com.jdp.hls.model.entiy.DecorationItem;
import com.jdp.hls.model.entiy.ModifyDecorationEvent;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.util.MathUtil;
import com.jdp.hls.util.NoDoubleClickListener;
import com.jdp.hls.util.SimpleTextWatcher;
import com.jdp.hls.view.EnableEditText;
import com.jdp.hls.view.KSpinner;
import com.jdp.hls.view.StringTextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Description:增加/保存
 * Create Time:2018/9/12 0012 上午 9:35
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class DecorationDetailActivity extends BaseTitleActivity implements DecorationDetailContract.View {
    @BindView(R.id.spinner_compensation_item)
    KSpinner spinnerCompensationItem;
    @BindView(R.id.spinner_compensation_grade)
    KSpinner spinnerCompensationGrade;
    @BindView(R.id.tv_compensation_price)
    StringTextView tvCompensationPrice;
    @BindView(R.id.et_compensation_quantity)
    EnableEditText etCompensationQuantity;
    @BindView(R.id.tv_compensation_totalMoney)
    StringTextView tvCompensationTotalMoney;
    @BindView(R.id.et_compensation_remark)
    EnableEditText etCompensationRemark;
    private String evalId;
    private String buildingType;
    private List<TDict> decorationItemList;
    private List<TDict> gradleList;
    private int itemId;
    private int dpscId;
    private int itemType;
    @Inject
    DecorationDetailPresenter decorationDetailPresenter;
    private DecorationItem decorationItem;

    @Override
    public void initVariable() {
        evalId = getIntent().getStringExtra(Constants.Extra.ID);
        buildingType = getIntent().getStringExtra(Constants.Extra.BUILDING_TYPE);
        decorationItem = (DecorationItem) getIntent().getSerializableExtra(Constants.Extra.DECORATION_ITEM);
        itemType = getIntent().getIntExtra(Constants.Extra.ITEM_TYPE, Integer.valueOf(Status.CompensationType
                .DECORATION));

        decorationItemList = getDecorationItemList();
        gradleList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.GRADLE);

    }

    public List<TDict> getDecorationItemList() {
        List<TDict> list = new ArrayList<>();
        List<TDict> totalList = DBManager.getInstance().getDictsByConfigType(Status.ConfigType.DECORATION_ITEM);
        for (TDict dict : totalList) {
            if (dict.getParentId() == itemType) {
                list.add(0, dict);
            }
        }
        return list;
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_decoration_detail;
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
        return "保存";
    }

    @Override
    protected void initView() {
        decorationDetailPresenter.attachView(this);
    }

    @Override
    protected void initData() {
        setRightClick("保存", new NoDoubleClickListener() {
            @Override
            public void onNoDoubleClick(View v) {
                saveData();
            }
        });
        spinnerCompensationItem.setDictsItem(decorationItemList, dict -> {
            itemId = dict.getTypeId();
            setGradleData(getGradleListByItemId(itemId));
        });
        setGradleData(getGradleListByItemId(decorationItemList.get(0).getTypeId()));

        etCompensationQuantity.addTextChangedListener(new SimpleTextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                calculateTotalMoney();
            }
        });

        if (decorationItem != null) {
            dpscId = decorationItem.getDPSCId();
            setGradleData(getGradleListByItemId(decorationItem.getDPSId()));
            spinnerCompensationItem.setSelectItem(decorationItem.getDPSId());
            spinnerCompensationGrade.setSelectItem(decorationItem.getDPSCId());
            tvCompensationPrice.setString(decorationItem.getPrice());
            etCompensationQuantity.setString(decorationItem.getQuantity());
            etCompensationRemark.setString(decorationItem.getRemark());
            calculateTotalMoney();
        }

    }

    private void calculateTotalMoney() {
        String quantityStr = etCompensationQuantity.getText().toString().trim();
        String priceStr = tvCompensationPrice.getText().toString().trim();
        double quantity = TextUtils.isEmpty(quantityStr) ? 0d : Double.valueOf
                (quantityStr);
        double price = TextUtils.isEmpty(priceStr) ? 0d : Double.valueOf
                (priceStr);
        tvCompensationTotalMoney.setString(MathUtil.mul(quantity, price));
    }

    public void setGradleData(List<TDict> datas) {
        spinnerCompensationGrade.setDictsItem(datas, dicts -> {
            dpscId = dicts.getTypeId();
            tvCompensationPrice.setString(dicts.getClassValue());
        });
        tvCompensationPrice.setString(datas.get(0).getClassValue());
        dpscId = datas.get(0).getTypeId();
    }

    public List<TDict> getGradleListByItemId(int typeId) {
        List<TDict> result = new ArrayList<>();
        for (TDict dict : gradleList) {
            if (dict.getParentId() == typeId) {
                result.add(dict);
            }
        }
        LogUtil.e(TAG, "result:" + result.size());
        return result;
    }

    private void saveData() {
        if (decorationItem == null) {
            decorationDetailPresenter.addDecoration(getRequestBody());
        } else {
            decorationDetailPresenter.modifyDecoration(getRequestBody());
        }
    }

    @NonNull
    private RequestBody getRequestBody() {
        String quantity = etCompensationQuantity.getText().toString().trim();
        String remark = etCompensationRemark.getText().toString().trim();
        return new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addFormDataPart("id", decorationItem == null ? "" : String.valueOf(decorationItem.getId()))
                .addFormDataPart("EvalId", decorationItem == null ? evalId : String.valueOf(decorationItem.getEvalId()))
                .addFormDataPart("DPSCId", String.valueOf(dpscId))
                .addFormDataPart("Quantity", quantity)
                .addFormDataPart("BuildingType", buildingType)
                .addFormDataPart("Remark", remark)
                .build();
    }

    @Override
    protected void initNet() {

    }

    public static void goActivity(Context context, String evalId, String buildingType, int itemType) {
        Intent intent = new Intent(context, DecorationDetailActivity.class);
        intent.putExtra(Constants.Extra.ID, evalId);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.ITEM_TYPE, itemType);
        context.startActivity(intent);
    }

    public static void goActivity(Context context, DecorationItem decorationItem, String buildingType, int itemType) {
        Intent intent = new Intent(context, DecorationDetailActivity.class);
        intent.putExtra(Constants.Extra.DECORATION_ITEM, decorationItem);
        intent.putExtra(Constants.Extra.BUILDING_TYPE, buildingType);
        intent.putExtra(Constants.Extra.ITEM_TYPE, itemType);
        context.startActivity(intent);
    }

    @Override
    public void onModifyDecorationSuccess(DecorationItem decorationItem) {
        EventBus.getDefault().post(new ModifyDecorationEvent(decorationItem));
        showSuccessAndFinish();
    }

    @Override
    public void onAddDecorationSuccess(DecorationItem decorationItem) {
        EventBus.getDefault().post(new AddDecorationEvent(decorationItem));
        showSuccessAndFinish();
    }
}
