package com.nullPointer.Domain.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerInfo {

    private static ServerInfo _instance;
    private String serverIp;
    private String clientID;
    private int maxPlayer = 12;
    private List<String> clientList;

    private ServerInfo() {
        clientList = new ArrayList<>();
    }

    public static ServerInfo getInstance() {
        if (_instance == null) {
            _instance = new ServerInfo();
        }
        return _instance;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
    }

    public void addClient(String clientId) {
        clientList.add(clientId);
    }

    public void removeClient(String clientId) {
        int clientIndex = clientList.indexOf(clientId);
        if (clientIndex == -1) {
            return;
        }
        clientList.remove(clientIndex);
    }

    public List<String> getClientList() {
        return clientList;
    }

    public String next() {
        if (clientList == null || clientList.size() < 2) return clientList.get(0);

        return clientList.get(1);
    }

    public void setClientList(List<String> clientList) {
        this.clientList = clientList;
    }
}
