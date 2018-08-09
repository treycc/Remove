package com.jdp.hls.util;

import android.util.Log;

/**
 * Description:TODO
 * Create Time:2018/7/20 9:10
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class LogUtil {
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int NOTHING = 6;
    public static final int level = VERBOSE;

    private LogUtil() {
        throw new UnsupportedOperationException("本日志工具类不能被实例化,直接调用静态方法就好");
    }

    /**
     * 打印Verbose级别日志
     * @param tag
     * @param msg
     */
    public static void v(String tag, String msg){
        if(level <= VERBOSE){
            Log.v(tag, msg);
        }
    }

    /**
     * 打印Debug级别日志
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg){
        if(level <= DEBUG){
            Log.d(tag, msg);
        }
    }

    /**
     * 打印Info级别日志
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg){
        if(level <= INFO){
            Log.i(tag, msg);
        }
    }

    /**
     * 打印Warn级别日志
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg){
        if(level <= WARN){
            Log.w(tag, msg);
        }
    }

    /**
     * 打印Error级别的日志
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg){
        if(level <= ERROR){
            Log.e(tag, msg);
        }
    }
}
