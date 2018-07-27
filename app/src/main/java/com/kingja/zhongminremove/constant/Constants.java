package com.kingja.zhongminremove.constant;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 11:10
 * 修改备注：
 */
public class Constants {
    public static final String BASE_URL = "http://116.62.220.255";
    public static final int PAGE_SIZE = 20;
    public static final int PAGE_FIRST = 1;
    public static final int PAGE_SIZE_100 = 100;
    public static final int ORDER_STATUS_UNUSED = 1;
    public static final int ORDER_STATUS_ALL = 3;
    public static final String APPLICATION_NAME = "KingJA_SP";
    public static final String EXTRA_QUESTION = "EXTRA_QUESTION";
    public static final int GRIDVIEW_IMG_COUNT = 3;
    public static final int GRIDVIEW_GIFT_COUNT = 4;


    /*Extra*/

    public static final String EXTRA_OTHER_ACCOUNT_ID = "EXTRA_OTHER_ACCOUNT_ID";

    public interface ModifyCode {
        int MODIFY_PHONE = 0;
        int MODIFY_ALIAS = 1;
    }

}
