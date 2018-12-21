package com.jdp.hls.util;

import android.text.TextUtils;
import android.widget.TextView;

/**
 * Description:TODO
 * Create Time:2018/12/18 0018 下午 3:30
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class CalculateUtil {

    public static double getNumber(TextView tv) {
        String strValue = tv.getText().toString().trim();
        return strValue.startsWith(".") || TextUtils.isEmpty(strValue) ? 0d : Double.valueOf(strValue);
    }

    public static int  getTotalValue(TextView... tvs) {
        double totalValue = 0d;
        for (TextView tv : tvs) {
            totalValue += getNumber(tv);
        }
        return (int) totalValue;
    }

    public static int  getLeftValue(TextView firstTv,TextView secondTv) {
        return (int) (getNumber(firstTv)-getNumber(secondTv));
    }
}
