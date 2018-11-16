package com.nullPointer.Server;

import com.nullPointer.Controller.CommunicationController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread{
    private  Thread thread;
    private  String threadName;
    private Socket socket;
    private String hostName = "localhost";
    private int portNumber = 4000;
    private PrintWriter out;
    private BufferedReader in;
    private CommunicationController communicationController = CommunicationController.getInstance();

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
            System.out.println("Client created with IP: " + socket.getInetAddress() + ". Port: " + socket.getPort());
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            BufferedReader stdIn =
                    new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;
            System.out.println("[Client]: Listening!");
            while (true) {
                if ((fromServer = in.readLine()) != null) {
                    System.out.println("[Client]: Server -> " + fromServer);
                    communicationController.processInput(fromServer);
                }
            }
        } catch (UnknownHostException er) {
            System.err.println("[Client]: Unknown host " + hostName);
            System.exit(1);
        } catch (IOException er) {
            System.err.println("[Client]: Couldn't get I/O for the connection to " +
                    hostName);
            System.exit(1);
        } finally {
            System.out.println("[Client]: Client is not listening anymore");
        }
    }

    public void sendMessage(String msg) {
        try{
            out.println(msg);
        }catch (Exception e){
        }
    }

    @Override
    public void start() {
        System.out.println("[Client]: Thread created with name " +  threadName );
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
