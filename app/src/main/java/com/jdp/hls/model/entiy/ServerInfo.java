package com.jdp.hls.model.entiy;

/**
 * Description:TODO
 * Create Time:2019/1/11 0011 上午 9:55
 * Author:KingJA
 * Email:kingjavip@gmail.com
 */
public class ServerInfo {
    public ServerInfo(String serverName, String serverUrl) {
        ServerName = serverName;
        ServerUrl = serverUrl;
    }

    private String ServerName;
    private String ServerUrl;

    public String getServerName() {
        return null == ServerName ? "" : ServerName;
    }

    public void setServerName(String serverName) {
        ServerName = serverName;
    }

    public String getServerUrl() {
        return null == ServerUrl ? "" : ServerUrl;
    }

    public void setServerUrl(String serverUrl) {
        ServerUrl = serverUrl;
    }
}
