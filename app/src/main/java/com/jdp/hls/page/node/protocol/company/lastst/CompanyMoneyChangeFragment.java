package com.jdp.hls.page.node.protocol.company.lastst;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jdp.hls.R;
import com.jdp.hls.adapter.ProtocolAdapter;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProtocolItem;
import com.jdp.hls.util.LogUtil;
import com.jdp.hls.view.FixedListView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/14 0014 上午 11:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CompanyMoneyChangeFragment extends BaseFragment {
    @BindView(R.id.flv)
    FixedListView flv;
    private List<ProtocolItem> protocolItemList;
    private int payType;
    private ProtocolAdapter protocolAdapter;
    private boolean isAlllowEdit;

    public static CompanyMoneyChangeFragment newInstance(List<ProtocolItem> protocolItemList, int payType, boolean
            isAlllowEdit) {
        CompanyMoneyChangeFragment fragment = new CompanyMoneyChangeFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Extra.Object, (Serializable) protocolItemList);
        args.putInt(Constants.Extra.PAYTYPE, payType);
        args.putBoolean(Constants.Extra.EDITABLE, isAlllowEdit);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            protocolItemList = (List<ProtocolItem>) getArguments().getSerializable(Constants.Extra.Object);
            payType = getArguments().getInt(Constants.Extra.PAYTYPE);
            isAlllowEdit = getArguments().getBoolean(Constants.Extra.EDITABLE);
        }
    }

    @Override
    protected void initComponent(AppComponent appComponent) {
        DaggerBaseCompnent.builder()
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected void initView() {
        protocolAdapter = new ProtocolAdapter(getActivity(), protocolItemList, isAlllowEdit);
        flv.setAdapter(protocolAdapter);
    }

    @Override
    protected void initData() {
        switchPayType(payType);
    }


    @Override
    public void initNet() {

    }

    private Map<Integer, List<ProtocolItem>> dataMap = new HashMap<>();

    public void switchPayType(int payType) {
        this.payType = payType;
        List<ProtocolItem> protocolItems = dataMap.get(payType);
        if (protocolItems == null) {
            //过滤
            protocolItems = new ArrayList<>();
            //保存
            for (ProtocolItem protocolItem : this.protocolItemList) {
                if (protocolItem.getPayType() == payType) {
                    protocolItems.add(protocolItem);
                }
            }
            dataMap.put(payType, protocolItems);
        }
        //刷新数据
        LogUtil.e(TAG, "protocolItems:" + protocolItems.size());
        protocolAdapter.setData(protocolItems);

    }

    public MultipartBody.Builder getRequestBuilder() {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        List<ProtocolItem> protocolItems = dataMap.get(payType);
        if (protocolItems != null) {
            for (ProtocolItem protocolItem : protocolItems) {
                if (protocolItem.isAllowEdit()) {
                    builder.addFormDataPart(protocolItem.getParamName(), protocolItem.getValue());
                }
            }
        }

        return builder;
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_company_money_change;
    }
}
