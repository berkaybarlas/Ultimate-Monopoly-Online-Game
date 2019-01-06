package com.nullPointer.Domain.Server;

import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//Singleton
//Controller/Adapter
//Observer
public class ResponseController {
    private static ResponseController _instance;
    private ArrayList<Socket> listenerClients;
    private ArrayList<ObjectOutputStream> listenerClientOutputs;
    private ObjectOutputStream outObject;
    private ServerInfo serverInfo = ServerInfo.getInstance();
    private PlayerController playerController = PlayerController.getInstance();

    private ResponseController() {
        this.listenerClients = new ArrayList<>();
        this.listenerClientOutputs = new ArrayList<>();
    }

    public static ResponseController getInstance() {
        if (_instance == null) {
            _instance = new ResponseController();
        }
        return _instance;
    }

    public void addSocket(Socket socket) {
        listenerClients.add(socket);
        try {
            listenerClientOutputs.add(new ObjectOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void sendResponse(Object message) {


        for (Iterator<ObjectOutputStream> iterator = listenerClientOutputs.iterator(); iterator.hasNext();) {
            ObjectOutputStream socketOutput = iterator.next();
            try {
                socketOutput.writeObject(message);
                socketOutput.reset();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("[ResponseController]:" + " error during sendResponse: " + e);;
                iterator.remove();
            }
        }
    }

    public void sendGameData(Socket socket) throws IOException {

        int indexOfClient = listenerClients.indexOf(socket);
        try {
            System.out.println("[ResponseController]:" + "trying to send object.");
            outObject = listenerClientOutputs.get(indexOfClient);
            List<String> clientList = serverInfo.getClientList();
            outObject.writeObject(clientList);
            //outObject.writeObject(PlayerController.getInstance());
            outObject.writeObject(playerController);
            outObject.writeObject(GameEngine.getInstance().getDomainBoard());
            outObject.writeObject("loadData");
            outObject.reset();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ResponseController]:" + "sending object failed.");
            outObject.close();
            listenerClients.remove(indexOfClient);
        } finally {
            System.out.println("[ResponseController]:" + "sending object finished.");
        }
    }

    public void closeConnections() {
        if (listenerClientOutputs != null) {
            listenerClientOutputs.forEach(out -> {
                try {
                    out.close();
                    listenerClientOutputs.remove(out);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        if(listenerClients != null){
            listenerClients.forEach(socket -> {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
