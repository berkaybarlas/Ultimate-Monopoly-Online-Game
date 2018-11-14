package com.nullPointer.Model;
import java.util.*;

import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Controller.MoneyController;
import com.nullPointer.Controller.PlayerController;
import com.nullPointer.Model.square.PropertySquare;
import com.nullPointer.UI.Board;
import com.nullPointer.UI.Navigator;

public class GameEngine {
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private MoneyController moneyController = MoneyController.getInstance();
    private Navigator navigator = Navigator.getInstance();

    private static GameEngine _instance;

    private GameEngine() {

    }

    public static GameEngine getInstance() {
        if(_instance == null) {
            _instance = new GameEngine();
        }
        return _instance;
    }


    public void initPlayers() {

    }

    public void startGame() {
        navigator.gameScreen();
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

    public void buyProperty(PropertySquare pSquare, Player player) {
    		pSquare.setOwner(player);
    		moneyController.decreaseMoney(player, pSquare.getPrice());
    }

    public void nextTurn() {

    }

    public void sendToJail() {
    		playerController.putInJail();
    }
}
