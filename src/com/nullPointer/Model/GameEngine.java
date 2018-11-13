package com.nullPointer.Model;
import java.util.*;

import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Controller.MoneyController;
import com.nullPointer.Controller.PlayerController;
import com.nullPointer.UI.Board;

public class GameEngine {
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private CommunicationController communicationController = CommunicationController.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private MoneyController moneyController = MoneyController.getInstance();

    public void initPlayers() {

    }

    public void startGame() {

    }

    
    public ArrayList<Integer> rollDice() {
    	regularDie.roll();
    	speedDie.roll();
    	ArrayList<Integer> list=new ArrayList<Integer>(2);
    	list.add(regularDie.getLastValues().get(0)+regularDie.getLastValues().get(1));
    	list.add(speedDie.getLastValues().get(0));
    	return list;
    }

    public void playCard() {

    }

    public void movePlayer(int newPosition) {
    	playerController.movePlayer(newPosition);
    }

    public void drawCard() {

    }

    public void improveProperty() {

    }

    public void buyProperty() {

    }

    public void nextTurn() {

    }

    public void sendToJail() {
    	playerController.putInJail();
    }
}
