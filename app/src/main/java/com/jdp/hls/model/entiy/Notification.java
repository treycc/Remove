package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/11/15 0015 下午 5:38
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Notification {
    private int MessageType;
    private String MessageTypeName;
    private int Count;
    private String LastTime;
    private String IconUrl;

    public int getMessageType() {
        return MessageType;
    }

    public void setMessageType(int messageType) {
        MessageType = messageType;
    }

    public String getMessageTypeName() {
        return MessageTypeName;
    }

    public void setMessageTypeName(String messageTypeName) {
        MessageTypeName = messageTypeName;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public String getLastTime() {
        return LastTime;
    }

    public void setLastTime(String lastTime) {
        LastTime = lastTime;
    }

    public String getIconUrl() {
        return IconUrl;
    }

    public void setIconUrl(String iconUrl) {
        IconUrl = iconUrl;
    }
}
