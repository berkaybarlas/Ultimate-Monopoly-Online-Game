package com.nullPointer.Domain.Server;

import com.nullPointer.Domain.Controller.CommunicationController;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {
    private Thread thread;
    private String threadName;
    private Socket socket;
    private String hostName = "localhost";
    private int portNumber = 4000;
    private PrintWriter out;
    private ObjectOutputStream outObject;
    private BufferedReader in;
    private ObjectInputStream inObject;
    private CommunicationController communicationController = CommunicationController.getInstance();
    private ServerInfo serverInfo = ServerInfo.getInstance();

    public Client(String name) {
        threadName = name;
    }

    public Client(String IP, String name) {
        threadName = name;
        hostName = IP; //127.0.0.1
    }

    @Override
    public void run() {
        //args[0];
        try {
            socket = new Socket(hostName, portNumber);
            System.out.println("Client created with IP: " + socket.getInetAddress() + ". Port: " + socket.getLocalPort());

            outObject = new ObjectOutputStream(socket.getOutputStream());

            inObject = new ObjectInputStream(socket.getInputStream());

            String fromServer;

            System.out.println("[Client]: Listening!");
            serverInfo.setClientID(socket.getLocalPort());
            while (true) {
                if ((fromServer = (String) inObject.readObject()) != null) {
                    System.out.println("[Client]: Server -> " + fromServer);
                    communicationController.processInput(fromServer);
                }
            }
        } catch (UnknownHostException er) {
            System.err.println("[Client]: Unknown host " + hostName);
            System.exit(1);
        } catch (IOException er) {
            System.err.println("[Client]: Couldn't get I/O for the connection to " +
                    hostName + ". Error : " + er);
            System.exit(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            System.out.println("[Client]: Client is not listening anymore");
        }
    }

    public void sendMessage(String msg) {
        try {
            outObject.writeObject(msg);
        } catch (Exception e) {
            System.out.println("[Client]: Error during sendMessage" + e);
        }
    }

    @Override
    public void start() {
        System.out.println("[Client]: Thread created with name " + threadName);
        if (thread == null) {
            thread = new Thread(this, threadName);
            thread.start();
        }
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
}
