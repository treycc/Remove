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
    //        public static final String BASE_URL = "http://192.168.0.5:8081/";
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

    public static final String LOG_FILENAME = "Logs";
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
        int UNRECORDBUILDING = 15;
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
        String ID = "ID";
        String OTHER_AREA = "OTHER_AREA";
        String AIRCHECK_ID = "AIRCHECK_ID";
        String AIRCHECKPRO_ID = "AIRCHECKPRO_ID";
        String AIRPHOTO_BUILDING = "AirPhotoBuilding";
        String UNRECORD_BUILDING_LIST = "unRecordBuildingList";
        String UNRECORD_BUILDING = "UnRecordBuilding";
        String DELETEIDS = "deleteIds";
        String EDITEDBASE64 = "editedBase64";
        String BOOKLETID = "bookletId";
        String BOOKLETNUM = "bookletNum";
        String FAMILYMEMBER = "familyMember";
        String COMPENSATION_TYPE = "compensationType";
        String DECORATION_ITEM = "decorationItem";
        String ITEM_TYPE = "itemType";
        String STATIS_TYPE = "statisType";
        String HOUSEID = "houseId";
        String STATIS_ID = "statisId";
    }

    public interface AirPhotoType {
        /*待办*/
        String TODO = "0";
        /*已办*/
        String DONE = "1";
        /*办结*/
        String FINISH = "2";
    }

    public interface FileType {
        String BUILD = "2";
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
        String ID = "ID";
        String OTHER_AREA = "OTHER_AREA";
    }

//    文件类型 基础 = 0, 不动产证 = 1, 房屋现状 = 2, 个人土地证 = 3, 个人房产证 = 4, 企业证件 = 5, 企业现状 = 6, 企业不动产证 = 7, 企业土地证 = 8, 企业房产证 = 9,
//            意见和建议文件 = 10, 赔偿案例 = 11, 赔偿 = 12, 入户丈量 = 13, 测绘出图 = 14, 年限鉴定 = 15, 入户评估 = 16, 协议生成 = 17, 协议签约 = 18, 公示 =
//            30, 航拍复查 = 31, 其它 = 100
}
