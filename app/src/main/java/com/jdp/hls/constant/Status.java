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
}
