package com.jdp.hls.util;

import android.app.Activity;

import com.jdp.hls.constant.Constants;
import com.jdp.hls.constant.Status;
import com.jdp.hls.page.node.BaseNodeActivity;
import com.jdp.hls.page.node.age.company.NodeCompanyAgeActivity;
import com.jdp.hls.page.node.age.personal.NodePersonalAgeActivity;
import com.jdp.hls.page.node.evaluate.company.houseevaluate.NodeCompanyHouseEvaluateActivity;
import com.jdp.hls.page.node.evaluate.company.moneyevaluate.NodeCompanyMoneyEvaluateActivity;
import com.jdp.hls.page.node.evaluate.personal.NodePersonalEvaluateActivity;
import com.jdp.hls.page.node.mapping.company.NodeCompanyMappingActivity;
import com.jdp.hls.page.node.mapping.personal.NodePersonalMappingActivity;
import com.jdp.hls.page.node.measure.company.NodeCompanyMeasureActivity;
import com.jdp.hls.page.node.measure.personal.NodePersonalMeasureActivity;
import com.jdp.hls.page.node.protocol.company.NodeCompanyProtocolActivity;
import com.jdp.hls.page.node.protocol.personal.NodePersonalProtocolActivity;
import com.jdp.hls.page.node.protocol.personal.lastst.NodePersonalProtocolNewActivity;

/**
 * Description:TODO
 * Create Time:2018/11/6 0006 下午 4:01
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class NodeUtil {
    public static void goNodeActivity(Activity activity, int id, String buildingId) {
        switch (id) {
            case Constants.BusinessNode.COMPANY_MEASURE:
                //企业入户丈量
                goNodeActivity(activity, NodeCompanyMeasureActivity.class, buildingId, Status.FileType.NODE_MEASURE,
                        Status.BuildingTypeStr.COMPANY);
                break;
            case Constants.BusinessNode.COMPANY_MAPPING:
                //企业测绘出图
                goNodeActivity(activity, NodeCompanyMappingActivity.class, buildingId, Status.FileType.NODE_MAPPING,
                        Status.BuildingTypeStr.COMPANY);
                break;
            case Constants.BusinessNode.COMPANY_AGE:
                //企业年限鉴定
                goNodeActivity(activity, NodeCompanyAgeActivity.class, buildingId, Status.FileType.NODE_AGE, Status
                        .BuildingTypeStr.COMPANY);
                break;
            case Constants.BusinessNode.COMPANY_EVALUATE_HOUSE:
                //企业房地产评估
                goNodeActivity(activity, NodeCompanyHouseEvaluateActivity.class, buildingId, Status.FileType
                        .HOUSE_EVALUATE, Status.BuildingTypeStr.COMPANY);
                break;
            case Constants.BusinessNode.COMPANY_EVALUATE_MONEY:
                //企业资产评估
                goNodeActivity(activity, NodeCompanyMoneyEvaluateActivity.class, buildingId, Status.FileType
                        .MONEY_EVALUATE, Status.BuildingTypeStr.COMPANY);
                break;
            case Constants.BusinessNode.COMPANY_PROTOCOL:
                //企业协议生成
                goNodeActivity(activity, NodeCompanyProtocolActivity.class, buildingId, Status.FileType
                        .NODE_PROTOCOL, Status.BuildingTypeStr.COMPANY);
                break;
            case Constants.BusinessNode.PERSONAL_MEASURE:
                //个人入户丈量
                goNodeActivity(activity, NodePersonalMeasureActivity.class, buildingId, Status.FileType.NODE_MEASURE,
                        Status.BuildingTypeStr.PERSONAL);
                break;
            case Constants.BusinessNode.PERSONAL_MAPPING:
                //个人测绘出图
                goNodeActivity(activity, NodePersonalMappingActivity.class, buildingId, Status.FileType.NODE_MAPPING,
                        Status.BuildingTypeStr.PERSONAL);
                break;
            case Constants.BusinessNode.PERSONAL_AGE:
                //个人年限鉴定
                goNodeActivity(activity, NodePersonalAgeActivity.class, buildingId, Status.FileType.NODE_AGE, Status
                        .BuildingTypeStr.PERSONAL);
                break;
            case Constants.BusinessNode.PERSONAL_EVALUATE:
                //个人评估
                goNodeActivity(activity, NodePersonalEvaluateActivity.class, buildingId, Status.FileType
                        .HOUSE_EVALUATE, Status.BuildingTypeStr.PERSONAL);
                break;
            case Constants.BusinessNode.PERSONAL_PROTOCOL:
                //个人协议生成
//                goNodeActivity(activity, NodePersonalProtocolActivity.class, buildingId, Status.FileType
//                        .NODE_PROTOCOL, Status.BuildingTypeStr.PERSONAL);

                goNodeActivity(activity, NodePersonalProtocolNewActivity.class, buildingId, Status.FileType
                        .NODE_PROTOCOL, Status.BuildingTypeStr.PERSONAL);
                break;
        }
    }

    private static void goNodeActivity(Activity activity, Class<? extends BaseNodeActivity> clazz, String buildingId,
                                       int fileType, String buildingType) {
        BaseNodeActivity.goActivity(activity, clazz, String.valueOf(fileType), buildingId, buildingType);
    }
}
