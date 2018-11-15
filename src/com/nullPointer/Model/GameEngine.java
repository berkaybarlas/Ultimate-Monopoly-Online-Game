package com.nullPointer.Model;
import com.nullPointer.UI.Observer;
import java.util.*;

import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Controller.MoneyController;
import com.nullPointer.Controller.PlayerController;
import com.nullPointer.Model.Square.PropertySquare;
import com.nullPointer.Model.Square.UtilitySquare;
import com.nullPointer.UI.Board;
import com.nullPointer.UI.Navigator;

public class GameEngine{
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private MoneyController moneyController = MoneyController.getInstance();
    private Navigator navigator = Navigator.getInstance();
    private static int ownedUtilities=0;
    private DomainBoard domainBoard;
    
    private static GameEngine _instance;
    ArrayList<Observer> observers=new ArrayList<Observer>();

    private GameEngine() {
    	domainBoard=new DomainBoard();
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
    
    public void buyUtility(UtilitySquare uSquare, Player player) {
    		uSquare.setOwner(player);
		// playerController.upgradeInventory(uSquare, player); should this add utilities too?
		moneyController.decreaseMoney(player, uSquare.getPrice());
		ownedUtilities++;
    }

    public void nextTurn() {

    }

    public void sendToJail() {
    		playerController.putInJail();
    }

    public void newClient() {

    }
    
    public void addListener(Observer listener){
		observers.add(listener);
	}

	public void publishEvent(String message) {
		observers.forEach(listener->listener.onEvent(message));
		
	}

	public PlayerController getPlayerController() {
		return playerController;
	}
	
	public MoneyController getMoneyController() {
		return moneyController;
	}
	
	
	public void payRent(Player player, int amount) {
		moneyController.decreaseMoney(player, amount);
		if(player.getMoney()<0) {
			publishEvent("bankrupt");
		}
	}

	public static int getOwnedUtilities() {
		return ownedUtilities;
	}

	public RegularDie getRegularDie() {
		return regularDie;
	}

	public SpeedDie getSpeedDie() {
		return speedDie;
	}
	
	
	
}
