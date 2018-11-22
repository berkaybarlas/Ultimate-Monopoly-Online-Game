package com.nullPointer.Domain.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerInfo {

    private static ServerInfo _instance;
    private String serverIp;
    private int clientID;
    private int maxPlayer = 12;
    private List<Integer> clientList;

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

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public void addClient(String clientId) {
        clientList.add(Integer.parseInt(clientId));
    }

    public List<Integer> getClientList() {
        return clientList;
    }

    public void setClientList(List<Integer> clientList) {
        this.clientList = clientList;
    }
}
