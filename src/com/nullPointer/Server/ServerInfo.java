package com.nullPointer.Server;

public class ServerInfo {

    private String serverIp;
    private String clientName;

    public ServerInfo(String serverIp, String clientName) {
        this.serverIp = serverIp;
        this.clientName = clientName;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
