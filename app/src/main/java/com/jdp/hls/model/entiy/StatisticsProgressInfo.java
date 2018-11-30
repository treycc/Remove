package com.jdp.hls.model.entiy;

import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/11/30 0030 下午 4:42
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class StatisticsProgressInfo {
//    {
//        "BuildingType": "integer,0：个人，1：企业",
//            "Series": [
//        {
//            "Name": "string,名称（如：总已拆迁户数：450户）",
//                "Quantity": "integer,数量"
//        }
//    ],
//        "Ratio": "string,总签约比例（如：总签约比例37.50%）",
//            "TotalQuantity": "integer,预计总数量(如：1000)",
//            "ProgressItems": [
//        {
//            "ItemType": "integer,子项类别",
//                "ItemTypeName": "string,子项类别名称",
//                "TotalQuantity": "integer,总数量",
//                "Percent": "decimal,占比,完成百分比（数值）",
//                "PercentDesc": "string,占比，完成百分比（文字）",
//                "IconUrl": "string,图片URL",
//                "Sort": "integer,排序"
//        }
//    ]
//    }

    private List<PieChartItem> Series;
    private List<ProgressItem> ProgressItems;
    private int TotalQuantity;
    private int BuildingType;
}
