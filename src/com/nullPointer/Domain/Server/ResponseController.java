package com.nullPointer.Domain.Server;

import com.nullPointer.Domain.Controller.PlayerController;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//Singleton
//Controller/Adapter
//Observer
public class ResponseController {
    private static ResponseController _instance;
    private ArrayList<Socket> listenerClients;
    private ArrayList<ObjectOutputStream> listenerClientOutputs;
    private PrintWriter out;
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

    public void sendResponse(Object message) {

        listenerClientOutputs.forEach(socketOutput -> {
            try {
                socketOutput.writeObject(message);
                socketOutput.reset();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("[ResponseController]:" + " error during sendResponse: " + e);
            }
        });
    }

    public void sendGameData(Socket socket) throws IOException {

        int indexOfClient = listenerClients.indexOf(socket);
        try {
            System.out.println("[ResponseController]:" + "trying to send object.");
            outObject = listenerClientOutputs.get(indexOfClient);
            List<Integer> clientList = serverInfo.getClientList();
            outObject.writeObject(clientList);
            //outObject.writeObject(PlayerController.getInstance());
            outObject.writeObject(playerController);
            outObject.writeObject("loadData");
            outObject.reset();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("[ResponseController]:" + "sending object failed.");
        } finally {
            // outObject.close();
            System.out.println("[ResponseController]:" + "sending object finished.");
        }
    }
}
