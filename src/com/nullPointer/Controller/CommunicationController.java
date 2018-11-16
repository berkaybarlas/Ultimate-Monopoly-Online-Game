package com.nullPointer.Controller;

import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.RegularDie;
import com.nullPointer.Model.SpeedDie;
import com.nullPointer.Server.Client;
import com.nullPointer.Server.GameServer;
import com.nullPointer.UI.DiceDisplay;
import com.nullPointer.UI.MessageBox;

import java.util.ArrayList;

public class CommunicationController {

    private static CommunicationController _instance;
    private GameServer gameServer;
    private Client client;
    private GameEngine gameEngine = GameEngine.getInstance();
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();

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

    public void createClient(String IP) {
        client = new Client(IP,"Client");
        client.start();
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

        if(input.contains("game")) {
            if(includes(rest(input), "start")) {
                gameEngine.startGame();
            }
        }

        if(input.indexOf("message")!=-1){
            gameEngine.publishEvent("message/" + rest(input));
        }

        if(input.contains("client")) {
            if(includes(rest(input), "create")){
                gameEngine.newClient();
            }
        }

        if(input.contains("dice")) {
            ArrayList<Integer> regularDice = new ArrayList<>();
            ArrayList<Integer> speedDice = new ArrayList<>();
            String[] values = input.split("/");
            regularDice.add(Integer.parseInt(values[1]));
            regularDice.add(Integer.parseInt(values[2]));
            speedDice.add(Integer.parseInt(values[3]));
            regularDie.setLastValues(regularDice);
            speedDie.setLastValues(speedDice);
            gameEngine.movePlayer();
        }
        
        if(input.contains("purchase")) {
        	gameEngine.buy();
        }
        if(input.contains("drawCard")) {
        	gameEngine.drawCard();
        }
        if(input.contains("playCard")) {
        	gameEngine.playCard();
        }
        if(input.contains("improveProperty")) {
        	gameEngine.improveProperty();
        }
    }

    private String rest(String word) {
        int slashIndex = word.indexOf('/');
        if(slashIndex==-1)
            return word;
        return word.substring(slashIndex+1);
    }

    private boolean includes(String sentence, String word) {
        return (sentence.indexOf(word) != -1);
    }
}
