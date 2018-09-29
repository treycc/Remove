package com.jdp.hls.constant;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 11:10
 * 修改备注：
 */
public class Constants {
    public static final String BASE_URL = "http://192.168.0.2:8080/";
//    public static final String BASE_URL = "http://192.168.0.5:8081/";
    public static final int PAGE_SIZE = 20;
    public static final int PAGE_FIRST = 1;
    public static final int PAGE_SIZE_100 = 100;
    public static final int ORDER_STATUS_UNUSED = 1;
    public static final int ORDER_STATUS_ALL = 3;
    public static final String APPLICATION_NAME = "KingJA_SP";
    public static final String EXTRA_QUESTION = "EXTRA_QUESTION";
    public static final int GRIDVIEW_IMG_COUNT = 3;
    public static final int GRIDVIEW_GIFT_COUNT = 4;
    public static final int MAX_IMG_UPLOAD_COUNT = 9;
    public static final int SINGLE_IMG_UPLOAD_COUNT = 1;

    public static final String  LOG_FILENAME = "Logs";
    public static final String DIR_HIL = "HLS";


    /*Extra*/

    public static final String EXTRA_OTHER_ACCOUNT_ID = "EXTRA_OTHER_ACCOUNT_ID";

    public interface ModifyCode {
        int MODIFY_PHONE = 0;
        int MODIFY_ALIAS = 1;
        int MODIFY_OWNER_NAME = 2;
        int MODIFY_ADDRESS = 3;
        int MODIFY_IDCARD = 4;
        int MODIFY_COMPANY_NAME = 5;
        int MODIFY_REMARK = 6;
    }

    public interface RequestCode {
        int LOCATION = 10;
        int PHOTO_PREVIEW = 11;
        int IMPORT_PERSON = 12;
        int PUBLICITY_DETAIL = 13;
        int PUBLICITY_OBJECT = 14;
    }
    public interface MapSetting {
        double Lat = 27.965626136017267;
        double Lng = 120.73433669492793;
        float Zoom = 6;
    }

    public interface NETWORK {
        int CONNECTTIMEOUT = 30;
        int WRITETIMEOUT = 40;
        int READTIMEOUT = 40;
    }

    public interface BusinessNode {
        int MEASURE = 1;
        int MAPPING = 2;
        int AGE = 3;
        int EVALUATE = 4;
        int PROTOCOL = 5;
    }

    public interface Extra {
        String DELETE_IDS = "DELETE_IDS";
        String DTO_IMGS = "DTO_IMGS";
        String BUILDINGID = "BUILDINGID";
        String PUBID = "PUBID";
        String PUB_STATUS = "PUB_STATUS";
        String POSITION = "POSITION";
        String BATCH_NAME = "BATCH_NAME";
        String PUBLICITY_DES = "PUBLICITY_DES";
        String PUBLICITY_TYPE = "PUBLICITY_TYPE";
        String BUILDING_TYPE = "BUILDING_TYPE";
        String BUILDINGIDS = "BUILDINGIDS";
    }

}
