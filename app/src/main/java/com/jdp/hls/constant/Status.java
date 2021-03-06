package com.jdp.hls.constant;

/**
 * Description:TODO
 * Create Time:2018/8/16 0016 下午 4:13
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Status {
    public interface ResultCode {
        //成功
        int SUCCESS = 0;
        //服务器错误
        int ERROR_SERVER = 10000;
        //登录失效
        int ERROR_LOGIN_FAIL = 1004;
        //身份证存在
        int ERROR_IDCARD_EXIST = 1011;
    }

    public interface DateType {
        int DAY = 1;
        int WEEK = 2;
        int MONTH = 3;
        int DATE = 0;
    }

    public interface InterfaceId {
        int PAY_DETAIL = 1;
        int TAOTYPE_DETAIL = 2;
    }

    public interface BuildingType {
        int PERSONAL = 0;
        int COMPANY = 1;
    }

    public interface AddType {
        int IMPORT = 0;
        int ADD = 1;
    }

    //    string,路由ID 1.普通账号  2:后台管理,
    public interface RouteId {
        int SYSTEM_LEVY = 1;
        int SYSTEM_ADMIN = 2;
    }

    public interface BuildingTypeStr {
        String PERSONAL = "0";
        String COMPANY = "1";
    }

    public interface AreaType {
        String BUILDING_AREA = "总安置建筑面积";
        String TAOTYPE_AREA = "总安置套内面积";
        String TAOTYPE = "套型";
    }

    public interface PublicityType {
        int SURVEY = 0;
        int AFFIRM = 1;
    }

    public interface ModuleId {
        /*账号管理*/
        int SYSTEM_ACCOUNT = 1;
        /*项目管理*/
        int SYSTEM_PROJECT_MANAGER = 2;
        /*消息管理*/
        int SYSTEM_MESSAGE = 3;
        /*数据查询*/
        int SYSTEM_QUERY = 4;
        /*征收业务*/
        int SYSTEM_LEVY = 5;
        /*监管系统*/
        int SYSTEM_PROJECT_SUPERVISE = 6;
        /*地理信息*/
        int SYSTEM_LOCATION = 7;
        /*项目审批*/
        int SYSTEM_PROJECRT_CHECK = 8;
        /*财务管理*/
        int SYSTEM_MONEY_MANAGE = 9;
    }

    public interface ConfigType {
        int LAND_USE = 1;//土地用途
        int LAND_TYPE = 2;//土地性质
        int PROPERTY_STRUCTURE = 3;//产权结构
        int PROPERTY_USE = 4;//产权用途
        int FAMILY_RELATION = 5;//产权用途
        int FLOW_NODE = 7;//节点
        int SOCIAL_RELATION = 8;//社会关系
        int COMPENSATION_TYPE = 9;//补偿方式
        int DECORATION_ITEM = 10;//装饰项目
        int GRADLE = 11;//等级
        int AIRPHOTOTYPE = 12;//航拍阶段
        int PUBLICITYTYPE = 13;//航拍阶段
        int PAY_TYPE = 15;//支付类型
    }

    public interface BuildingId {
        int PERSONAL = 0;
        int COMPANY = 1;
    }


    public interface UIPermission {
        int VIEW = 0;
        int MODIFY = 1;
        int ADD = 2;
    }

    public interface CompensationType {
        String DECORATION = "1";
        String APPENDANT = "2";
    }

    public interface AccountType {
        int OPERATEMAN = 10;
    }

    public interface BusinessActionType {
        int ROSTER = 1;
        int PUBLICITY = 2;
        int AIRPHOTO = 3;
        int TABLE = 4;
    }

    public interface DataStatisticsType {
        int PROGRESS_STATISTICS = 30;
        int TOTAL_STATISTICS = 31;
    }

    public interface AirPhotoType {
        int FIRST = 0;
        int MIDDLE = 1;
        int LAST = 2;
    }

    public interface AirPhotoFinishType {
        int TODO = 0;
        int DONE = 1;
        int FINISH = 2;
    }

    public interface StatisticsType {
        int AIRPHOTO = 7;
    }

    public interface FileType {
        int BASIC = 0;
        int PERSONAL_CURRENT = 2;
        int PERSONAL_DEED_IMMOVABLE = 1;
        int PERSONAL_DEED_LAND = 3;
        int PERSONAL_DEED_PROPERTY = 4;
        int COMPANY_DEED_BUSINESS = 5;
        int COMPANY_CURRENT = 6;
        int COMPANY_DEED_IMMOVABLE = 7;
        int COMPANY_DEED_LAND = 8;
        int COMPANY_DEED_PROPERTY = 9;
        int SUGGEST = 10;
        int COMPENSATE_CASE = 11;
        int COMPENSATE = 12;
        int NODE_MEASURE = 13;
        int NODE_MAPPING = 14;
        int NODE_AGE = 15;
        int NODE_PROTOCOL = 17;
        int NODE_PROTOCOL_SIGN = 18;
        int BUSINESS_PUBLICITY = 30;
        int BUSINESS_AIRPHOTO = 31;
        int BUSINESS_IDCARD = 32;
        int PROCEDURE = 33;
        int BANK = 34;
        int HOUSE_EVALUATE = 16;
        int MONEY_EVALUATE = 35;
        int PROJECT_OLDLOOK = 36;
        int PROJECT_NEWLOOK = 37;
        int PAY_DETAIL = 38;
        int HOUSE_ICHNOGRAPHY = 39;
        int OTHER = 100;
    }

    public interface PayTypeItem {
        int TempPlacementFee = 2;
    }

    public interface AreaLevel {
        int PROVINCE = 1;
        int CITY = 2;
        int AREA = 3;
        int STREET = 4;
    }

//    文件类型 基础 = 0, 住宅不动产证 = 1, 住宅房屋现状 = 2, 住宅土地证 = 3, 住宅房产证 = 4, 企业证件 = 5, 企业现状 = 6, 企业不动产证 = 7, 企业土地证 = 8, 企业房产证 = 9,
//            意见和建议文件 = 10, 赔偿案例 = 11, 赔偿 = 12, 入户丈量 = 13, 测绘出图 = 14, 年限鉴定 = 15, 入户评估 = 16, 协议生成 = 17, 协议签约 = 18, 公示 =
//            30, 航拍复查 = 31,相关审批手续 = 33, 银行/开户 = 34,资产评估 = 35, 其它 = 100
}
