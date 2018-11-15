package com.nullPointer.Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

//Singleton
//Controller/Adapter
//Observer
public class ResponseController {
    private static ResponseController _instance;
    private ArrayList<Socket> listenerClients;
    private PrintWriter out;

    private ResponseController() {
        this.listenerClients = new ArrayList<>();
    }

    public static ResponseController getInstance() {
        if(_instance == null) {
            _instance = new ResponseController();
        }
        return _instance;
    }

    public void addSocket(Socket socket){
        listenerClients.add(socket);
    }

    public void sendResponse(String message) {

        listenerClients.forEach(socket -> {
            try {
                out = new PrintWriter(socket.getOutputStream(), true);
                out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

}
