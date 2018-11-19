package com.jdp.hls.constant;

/**
 * 项目名称：
 * 类描述：TODO
 * 创建人：KingJA
 * 创建时间：2016/6/13 11:10
 * 修改备注：
 */
public class Constants {
        public static final String BASE_URL = "http://192.168.0.2:8080/";//本地
//    public static final String BASE_URL = "http://192.168.0.5:8081/";//服务器
//    public static final String BASE_URL = "http://47.100.213.104:8081/";//外网

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
        int LOCATION = 77;
        int PHOTO_PREVIEW = 11;
        int IMPORT_PERSON = 12;
        int PUBLICITY_DETAIL = 13;
        int PUBLICITY_OBJECT = 14;
        int UNRECORDBUILDING = 15;
        int MANAGER_LIST = 16;
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
        int PERSONAL_MEASURE = 110;
        int PERSONAL_MAPPING = 111;
        int PERSONAL_AGE = 102;
        int PERSONAL_EVALUATE = 103;
        int PERSONAL_PROTOCOL = 104;

        int COMPANY_MEASURE = 10;
        int COMPANY_MAPPING = 11;
        int COMPANY_AGE = 2;
        int COMPANY_EVALUATE_MONEY = 31;
        int COMPANY_EVALUATE_HOUSE = 30;
        int COMPANY_PROTOCOL = 4;
    }

    public interface Extra {
        String Employees = "Employees";
        String Ids = "Ids";
        String Names = "Names";
        String EmployeeId = "employeeId";
        String AIRPHOTO_TYPE = "airPhotoType";
        String CERTNUM = "certNum";
        String FILETYPE = "fileType";
        String ISADD = "ISADD";
        String PHOTOLIST = "photoList";
        String EDITABLE = "editable";
        String DELETE_IDS = "DELETE_IDS";
        String DTO_IMGS = "DTO_IMGS";
        String FILE_CONFIG = "fileConfig";
        String BUILDING_ID = "BUILDING_ID";
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
        String CHECKTYPE = "CHECKTYPE";
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
        String TITLE = "title";
        String HOUSEID = "houseId";
        String STATIS_ID = "statisId";
        String RouteId = "RouteId";
        String PROJECTID = "projectId";
    }

    public interface AirPhotoType {
        /*待办*/
        String TODO = "0";
        /*已办*/
        String DONE = "1";
        /*办结*/
        String FINISH = "2";
    }
}
