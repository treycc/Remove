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


}
