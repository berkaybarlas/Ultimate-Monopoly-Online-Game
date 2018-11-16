package com.nullPointer.Domain.Model;
import com.nullPointer.UI.Observer;
import java.util.*;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.Square;
import com.nullPointer.Domain.Model.Square.UtilitySquare;
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
        evaluateSquare();
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
    	Square square = domainBoard.getSquaresInLayer().get(currentPlayer.getPosition());
    	if(square.getType().equals("PropertySquare") && ((PropertySquare) square).getOwner() == null) {
        	playerController.upgradePropertyList((PropertySquare) square, currentPlayer);
        	moneyController.decreaseMoney(currentPlayer, ((PropertySquare) square).getPrice());
        	((PropertySquare) square).setOwner(currentPlayer);
    	}
    	else if(square.getType().equals("UtilitySquare")) {
    		playerController.upgradeUtilityList((UtilitySquare) square, currentPlayer);
        	moneyController.decreaseMoney(currentPlayer, ((UtilitySquare) square).getPrice());
    	}	
        nextTurn();
    }

    public void nextTurn() {
        playerController.nextPlayer();
        publishEvent("rollDice");
        publishEvent("refresh");
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
	
	
	public void payRent(Player player, Player owner, int amount) {
		moneyController.decreaseMoney(player, amount);
		moneyController.increaseMoney(owner, amount);
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

	public void playCard() {
		// TODO Auto-generated method stub
		
	}

	public void evaluateSquare() {
        Player currentPlayer = playerController.getCurrentPlayer();
        Square square = domainBoard.getSquaresInLayer().get(currentPlayer.getTargetPosition());
        square.evaluateSquare(this);
    }

}
