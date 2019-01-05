package com.nullPointer.Domain.Server;

import java.util.ArrayList;
import java.util.List;

public class ServerInfo {

    private static ServerInfo _instance;
    private String clientID;
    private List<String> clientList;
    private int failedAttempt = 0;
    private ServerInfo() {
        clientList = new ArrayList<>();
    }

    public static ServerInfo getInstance() {
        if (_instance == null) {
            _instance = new ServerInfo();
        }
        return _instance;
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
        if (clientList == null || clientList.size() == 1) return "";
        if (clientList.size() < 2)
            return clientList.get(0);
        failedAttempt = ( failedAttempt + 1 ) % clientList.size();
        return clientList.get(failedAttempt);
    }

    public void setClientList(List<String> clientList) {
        this.clientList = clientList;
    }

    public boolean isServer() {
        if (clientList == null || clientList.size() < 1) {
            return false;
        }
        return clientList.get(0).equals(clientID);
    }

    public boolean isOnline(String clientID) {

        if(clientList == null || clientID == null || clientID == ""){
            return  false;
        }
        return clientList.contains(clientID);
    }
}
