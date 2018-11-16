package com.nullPointer.Model;
import com.nullPointer.UI.Observer;
import java.util.*;

import com.nullPointer.Controller.CommunicationController;
import com.nullPointer.Controller.MoneyController;
import com.nullPointer.Controller.PlayerController;
import com.nullPointer.Model.Square.PropertySquare;
import com.nullPointer.Model.Square.Square;
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
    		/*playerController.getPlayers().add(new Player("Furkan"));
    		playerController.getPlayers().add(new Player("Berkay"));
    		playerController.getPlayers().add(new Player("Baran Berkay"));
    		playerController.getPlayers().add(new Player("Tumay"));
    		playerController.getPlayers().add(new Player("Alihan"));*/
    }

    public static GameEngine getInstance() {
        if(_instance == null) {
            _instance = new GameEngine();
        }
        return _instance;
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void publishEvent(String message) {
        observers.forEach(listener->listener.onEvent(message));
    }

    public void initPlayers(int playerNumber) {
        for(int i=0; i<playerNumber; i++) {
            playerController.addPlayer();
        }
        publishEvent("refreshPawnNumber");
    }

    public void startGame() {
        navigator.gameScreen();
        initPlayers(2);
    }

    
    public ArrayList<Integer> rollDice() {
	    	regularDie.roll(2);
	    	speedDie.roll(1);
	    	ArrayList<Integer> list=new ArrayList<Integer>(3);
	    	list.add(regularDie.getLastValues().get(0));
	    	list.add(regularDie.getLastValues().get(1));
	    	list.add(speedDie.getLastValues().get(0));

	    	return list;
    }

    public void movePlayer() {
        publishEvent("refresh");
        playerController.getCurrentPlayer().setTargetPosition(calculateMoveAmount());
    }

    public int calculateMoveAmount(){
        int total = 0;
        total+=regularDie.getLastValues().get(0);
        total+=regularDie.getLastValues().get(1);
        return total;
    }

    public void drawCard() {
    	domainBoard.getCards().element();
    }

    public void improveProperty() {

    }

    public void buy() {
    	
    	Player currentPlayer = playerController.getCurrentPlayer();
    	Square square = domainBoard.getSquares().get(currentPlayer.getPosition());
    	if(square.getType().equals("PropertySquare")) {
    		System.out.println(currentPlayer.getName());
        	playerController.upgradePropertyList((PropertySquare) square, currentPlayer);
        	moneyController.decreaseMoney(currentPlayer, ((PropertySquare) square).getPrice());
        	System.out.println(currentPlayer.getMoney());
    	}
    	else if(square.getType().equals("UtilitySquare")) {
    		playerController.upgradeUtilityList((UtilitySquare) square, currentPlayer);
        	moneyController.decreaseMoney(currentPlayer, ((UtilitySquare) square).getPrice());
    	}	

    	publishEvent("refresh");
    }

	public void makeAction() {
		Player currentPlayer = playerController.getCurrentPlayer();
    	
		
	}
	

    public void nextTurn() {

    }

    public void sendToJail() {
    		playerController.putInJail();
    }

    public void newClient() {

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

	public RegularDie getRegularDie() {
		return regularDie;
	}

	public SpeedDie getSpeedDie() {
		return speedDie;
	}
	
	public String getLastDiceValues() {
        return regularDie.getLastValues().get(0) + "/" + regularDie.getLastValues().get(1) + "/" + speedDie.getLastValues().get(0);
    }

}
