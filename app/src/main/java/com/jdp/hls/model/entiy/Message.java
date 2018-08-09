package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2018/7/27 0027 下午 3:46
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class Message {
    private String sender;
    private String date;
    private String content;

    public Message(String sender, String date, String content) {
        this.sender = sender;
        this.date = date;
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
