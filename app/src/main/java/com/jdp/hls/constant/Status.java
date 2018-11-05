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

    public interface BuildingType {
        int PERSONAL = 0;
        int COMPANY = 1;
    }

    public interface BuildingTypeStr {
        String PERSONAL = "0";
        String COMPANY = "1";
    }

    public interface PublicityType {
        int SURVEY = 0;
        int AFFIRM = 1;
    }

    public interface ConfigType {
        int LAND_USE = 1;//土地用途
        int LAND_TYPE = 2;//土地性质
        int PROPERTY_STRUCTURE = 3;//产权结构
        int PROPERTY_USE = 4;//产权用途
        int FAMILY_RELATION = 5;//产权用途
        int FLOW_NODE = 7;//节点
        int SOCIAL_RELATION = 8;//社会关系
        int PAY_TYPE = 9;//补偿方式
        int DECORATION_ITEM = 10;//装饰项目
        int GRADLE = 11;//等级
        int AIRPHOTOTYPE = 12;//航拍阶段
        int PUBLICITYTYPE = 13;//航拍阶段
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
        int NODE_EVALUATE = 16;
        int NODE_PROTOCOL = 17;
        int NODE_PROTOCOL_SIGN = 18;
        int BUSINESS_PUBLICITY = 30;
        int BUSINESS_AIRPHOTO = 31;
        int BUSINESS_IDCARD = 32;
        int PROCEDURE = 33;
        int BANK = 34;
        int OTHER = 100;
    }

//    文件类型 基础 = 0, 个人不动产证 = 1, 个人房屋现状 = 2, 个人土地证 = 3, 个人房产证 = 4, 企业证件 = 5, 企业现状 = 6, 企业不动产证 = 7, 企业土地证 = 8, 企业房产证 = 9,
//            意见和建议文件 = 10, 赔偿案例 = 11, 赔偿 = 12, 入户丈量 = 13, 测绘出图 = 14, 年限鉴定 = 15, 入户评估 = 16, 协议生成 = 17, 协议签约 = 18, 公示 =
//            30, 航拍复查 = 31,相关审批手续 = 33, 银行/开户 = 33, 其它 = 100
}
