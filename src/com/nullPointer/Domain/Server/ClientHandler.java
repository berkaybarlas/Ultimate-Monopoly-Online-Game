package com.nullPointer.Domain.Server;

import java.io.*;
import java.net.Socket;

class ClientHandler extends Thread {
    private Socket clientSocket;
    private ResponseController responseController;
    private ObjectInputStream oin;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.responseController = ResponseController.getInstance();
    }

    public void run() {
        Object inputLine = null;

        try {
            oin = new ObjectInputStream(clientSocket.getInputStream());

            //bak
            // out.println("[ClientHandler]: Listening with socket: " + clientSocket.toString());
            while (true) {
                if ((inputLine = oin.readObject()) != null) {
                    //System.out.println("[ClientHandler]: Client -> " + inputLine);
                    System.out.println("[ClientHandler]: Client -> " + inputLine);
                    //responseController.sendResponse(outputLine);
                    responseController.sendResponse(inputLine);
                }
            }
        } catch (IOException e) {
            System.out.println("[ClientHandler]: Exception caught when trying to listen on port ");
            //
            //delete client from list
            //
            System.out.println(inputLine);
            System.out.println(oin);
            e.printStackTrace();
            try {
                clientSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

