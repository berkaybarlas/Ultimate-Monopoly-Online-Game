package com.nullPointer.Model;
import com.nullPointer.UI.Observer;
import java.util.*;

import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Controller.MoneyController;
import com.nullPointer.Controller.PlayerController;
import com.nullPointer.Model.Square.PropertySquare;
import com.nullPointer.UI.Board;
import com.nullPointer.UI.Navigator;

public class GameEngine implements Runnable{
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private MoneyController moneyController = MoneyController.getInstance();
    private Navigator navigator = Navigator.getInstance();

    private static GameEngine _instance;
    ArrayList<Observer> observers=new ArrayList<Observer>();

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
    		playerController.upgradeInventory(pSquare, player);
    		moneyController.decreaseMoney(player, pSquare.getPrice());
    }

    public void nextTurn() {

    }

    public void sendToJail() {
    		playerController.putInJail();
    }

    public void newClient() {

    }
    public void run() {

		  Random random  = new Random();

	      while (true) {

	    	  try {
				Thread.sleep(random.nextInt(10000));
			  } catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			  }
	    	  publishEvent("");
	      }
	}
    public void addListener(Observer listener){
		observers.add(listener);
	}

	public void publishEvent(String message) {
		observers.forEach(listener->listener.onEvent(message));
		System.out.println("Published");
	}
}
