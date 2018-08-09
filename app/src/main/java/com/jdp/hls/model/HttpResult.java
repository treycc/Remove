package com.jdp.hls.model;

/**
 * Description：TODO
 * Create Time：2016/10/620:32
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class HttpResult<T> {

    private int Code;
    private String Message;
    private T Data;

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        this.Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        this.Data = data;
    }

}
