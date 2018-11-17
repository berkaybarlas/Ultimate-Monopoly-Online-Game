package com.nullPointer.Domain.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread{

    private  Thread thread;
    private  String threadName;
    private int portNumber = 4000;

    private ResponseController responseController;

    public GameServer(String name) {
        threadName = name;
        responseController = ResponseController.getInstance();
    }

    @Override
    public void run() {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(portNumber);
            while (true) {
                Socket socket = serverSocket.accept();
                responseController.addSocket(socket);
                responseController.sendResponse("client/create");
                new ClientHandler(socket).start();

            }
        } catch (IOException e) {
            System.out.println("[Server]: Exception caught when trying to listen on port "
                    + portNumber);
            System.out.println(e.getMessage());
        }
        System.out.println("[Server]: server Out");
    }
    @Override
    public void start() {
        System.out.println("[Server]: Starting thread with name " +  threadName );
        if (thread == null) {
            thread = new Thread (this, threadName);
            thread.start ();
        }
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
}
