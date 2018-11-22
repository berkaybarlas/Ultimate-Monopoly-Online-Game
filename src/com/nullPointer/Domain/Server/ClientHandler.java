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
        PrintWriter out;
        ObjectOutputStream oOut;
        BufferedReader in;
        Object inputLine, outputLine;

        try {
            //out = new PrintWriter(clientSocket.getOutputStream(), true);
            //in = new BufferedReader( new InputStreamReader(clientSocket.getInputStream()));
            //oOut = new ObjectOutputStream(clientSocket.getOutputStream());
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
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

