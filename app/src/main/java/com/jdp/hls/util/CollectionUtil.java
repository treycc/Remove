package com.jdp.hls.util;

import java.util.Arrays;
import java.util.List;

/**
 * Description:TODO
 * Create Time:2018/10/23 0023 上午 10:45
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CollectionUtil {

    public static List<String> getBuildingIdList(String buildingIds) {
        String[] buildingIdAttr = buildingIds.split("#");
        return Arrays.asList(buildingIdAttr);
    }

}
