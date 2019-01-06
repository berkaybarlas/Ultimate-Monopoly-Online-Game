package com.nullPointer.Domain.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer extends Thread {

    private Thread thread;
    private String threadName;
    private int portNumber = 4000;
    private ServerSocket serverSocket;

    private ResponseController responseController;

    public GameServer(String name) {
        threadName = name;
        responseController = ResponseController.getInstance();
    }

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(portNumber);
            while (true) {
                Thread.sleep(100);
                Socket socket = serverSocket.accept();
                responseController.addSocket(socket);
                responseController.sendGameData(socket);
                String socketIp = socket.getInetAddress().toString().substring(1);
                if(socketIp.equals("127.0.0.1")){
                    socketIp = InetAddress.getLocalHost().getHostAddress();
                }
                responseController.sendResponse("client/create/" + socketIp + ":" + socket.getPort());
                System.out.println("[GameServer]" + socket.getInetAddress().toString());
                new ClientHandler(socket).start();

            }
        } catch (IOException e) {
            System.out.println("[Server]: Exception caught when trying to listen on port "
                    + portNumber);
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("[Server]: server error");
        }
        System.out.println("[Server]: server Out");
    }

    @Override
    public void start() {
        System.out.println("[Server]: Starting thread with name " + threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.out.println("[GamerServer]: There was an error during server close.");
        }
        if(responseController!=null){
            responseController.closeConnections();
        }
    }

}
