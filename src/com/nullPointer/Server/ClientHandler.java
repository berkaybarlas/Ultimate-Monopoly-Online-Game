package com.nullPointer.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class ClientHandler extends Thread
{
    private Socket clientSocket;

    private ResponseController responseController;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.responseController = ResponseController.getInstance();
    }

    public void run() {
         PrintWriter out;
         BufferedReader in;
         String inputLine, outputLine;
         ServerProtocol protocol;
        try {
            out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            //oOut = new ObjectOutputStream(clientSocket.getOutputStream());
            //oin = new ObjectInputStream(clientSocket.getInputStream());

            protocol = new ServerProtocol();
            out.println("[ClientHandler]: Listening with socket: " + clientSocket.toString());
            while (true) {
                if ((inputLine = in.readLine()) != null) {
                    //System.out.println("[ClientHandler]: Client -> " + inputLine);
                    outputLine = protocol.processInput(inputLine);
                    responseController.sendResponse(outputLine);
                    System.out.println("[ClientHandler]: Server -> " + outputLine);
                }
            }
        }catch (IOException e) {
            System.out.println("[ClientHandler]: Exception caught when trying to listen on port ");
            System.out.println(e.getMessage());
        }
    }
}

