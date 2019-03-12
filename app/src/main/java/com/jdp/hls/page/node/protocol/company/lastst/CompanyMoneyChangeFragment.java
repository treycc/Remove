package com.jdp.hls.page.node.protocol.company.lastst;

import android.os.Bundle;

import com.jdp.hls.R;
import com.jdp.hls.base.BaseFragment;
import com.jdp.hls.base.DaggerBaseCompnent;
import com.jdp.hls.constant.Constants;
import com.jdp.hls.injector.component.AppComponent;
import com.jdp.hls.model.entiy.ProtocolItem;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;

/**
 * Description:TODO
 * Create Time:2018/12/14 0014 上午 11:00
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CompanyMoneyChangeFragment extends BaseFragment {
    private List<ProtocolItem> protocolItemList;
    private int payType;

    public static CompanyMoneyChangeFragment newInstance(List<ProtocolItem> protocolItemList, int payType) {
        CompanyMoneyChangeFragment fragment = new CompanyMoneyChangeFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constants.Extra.Object, (Serializable) protocolItemList);
        args.putInt(Constants.Extra.PAYTYPE, payType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initVariable() {
        if (getArguments() != null) {
            protocolItemList = (List<ProtocolItem>) getArguments().getSerializable(Constants.Extra.Object);
            payType = getArguments().getInt(Constants.Extra.PAYTYPE);
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

    }

    @Override
    protected void initData() {
    }


    @Override
    public void initNet() {

    }

    private Map<Integer, List<ProtocolItem>> dataMap = new HashMap<>();

    public void switchPayType(int payType) {
        List<ProtocolItem> protocolItems = dataMap.get(payType);
        if (protocolItems == null) {
            //过滤
            //保存
            dataMap.put(payType, protocolItems);
        }
        //刷新数据

    }

    public MultipartBody.Builder getRequestBuilder() {
        MultipartBody.Builder builder = null;
        return builder;
    }

    @Override
    protected int getContentId() {
        return R.layout.fragment_pay_change;
    }


}
