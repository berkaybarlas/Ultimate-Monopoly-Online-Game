package com.nullPointer.Controller;

import com.nullPointer.Server.Client;
import com.nullPointer.Server.GameServer;

public class CommunicationController {

    private static CommunicationController _instance;
    private GameServer gameServer;
    private Client client;

    private CommunicationController() {
    }

    public static CommunicationController getInstance() {
        if(_instance == null) {
            _instance = new CommunicationController();
        }
        return _instance;
    }

    public void startServer() {
        gameServer = new GameServer("SERVER");
        gameServer.start();
    }

    public void createClient() {
        client = new Client("Client");
        client.start();
    }

    public void sendClientMessage(String msg) {
        client.sendMessage(msg);
    }
}
