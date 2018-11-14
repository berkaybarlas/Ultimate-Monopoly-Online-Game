package com.nullPointer.Controller;

import com.nullPointer.Model.GameEngine;
import com.nullPointer.Server.Client;
import com.nullPointer.Server.GameServer;
import com.nullPointer.UI.MessageBox;

public class CommunicationController {

    private static CommunicationController _instance;
    private GameServer gameServer;
    private Client client;
    private GameEngine gameEngine = GameEngine.getInstance();

    ///yanlis
    private MessageBox messageBox;

    public void setMessageBox(MessageBox messageBox){
        this.messageBox = messageBox;
    }
    //wrong

    private CommunicationController() {
    }

    public static CommunicationController getInstance() {
        if(_instance == null) {
            _instance = new CommunicationController();
        }
        return _instance;
    }

    public void startServer() {
        gameServer = new GameServer("SERVER");
        gameServer.start();
    }

    public void createClient() {
        client = new Client("Client");
        client.start();
    }

    public void sendClientMessage(String msg) {
        client.sendMessage(msg);
    }

    public void removeClient() {

    }

    public void processInput(String input) {

        if(input.equals("start")) {
            gameEngine.startGame();
        }

        if(input.indexOf("message")!=-1){
            messageBox.addMessage(second(input));
            messageBox.repaint();
        }

        if(includes(input,"client")){
            if(includes(second(input), "create")){
                gameEngine.newClient();
            }
        }

    }

    private String second(String word) {
        String[] words = word.split("/");
        if(words.length>1)
            return words[1];
        return word;
    }

    private boolean includes(String sentence, String word) {
        return (sentence.indexOf(word) != -1);
    }
}
