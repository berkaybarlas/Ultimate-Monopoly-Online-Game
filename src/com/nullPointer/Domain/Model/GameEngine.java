package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Cards.Roll3;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.Square;
import com.nullPointer.Domain.Model.Square.UtilitySquare;
import com.nullPointer.Domain.Observer;
import com.nullPointer.Domain.Server.ServerInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class GameEngine {
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private MoneyController moneyController = MoneyController.getInstance();
    private ServerInfo serverInfo = ServerInfo.getInstance();
    private static int ownedUtilities = 0;
    private DomainBoard domainBoard;
    private boolean gameIsPaused = false;
    private boolean roll3 = false;
    
    public boolean getRoll3(){
    	return roll3;
    }
    public void setRoll3(boolean set){
    	roll3 = set;
    }
    public DomainBoard getDomainBoard() {
        return domainBoard;
    }
    private static GameEngine _instance;
    ArrayList<Observer> observers = new ArrayList<Observer>();

    private GameEngine() {
        domainBoard = new DomainBoard();
    }

    public static GameEngine getInstance() {
        if (_instance == null) {
            _instance = new GameEngine();
        }
        return _instance;
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void publishEvent(String message) {
        observers.forEach(listener -> listener.onEvent(message));
    }

    public void initPlayers(int playerNumber) {
        publishEvent("initializePawns");
        publishEvent("initializePlayers");
    }

    public void addPlayer(Player newPlayer) {
        playerController.addPlayer(newPlayer);
        newPlayer.addRoll3Card((Roll3)domainBoard.getRoll3Cards().poll());
        publishEvent("newPlayer");
    }

    public void startGame() {
        publishEvent("screen/gameScreen");
        initPlayers(2);
        publishEvent("rollDice");
    }

    //    public void movePlayer() {                old code
    //        publishEvent("refresh");
    //        Player currentPlayer = playerController.getCurrentPlayer();
    //        playerController.movePlayer(calculateMoveAmount(), domainBoard.getSquaresInLayer(currentPlayer.getLayer()).size());
    //        evaluateSquare();
//    }

    public LinkedList<Integer> calculatePath() {
        publishEvent("refresh");
        LinkedList<Integer> path = new LinkedList<Integer>();
        HashMap<Integer, Square> squares = domainBoard.getSquareMap();
        HashMap<Integer, ArrayList<Integer>> connections = domainBoard.getConnectionsMap();
        Player currentPlayer = playerController.getCurrentPlayer();
        int currentPos;
        int regularDiceTotal = calculateMoveAmount();
        int target = -2;


        for (int i = 0; i < regularDiceTotal; i++) {
            currentPos = currentPlayer.getTargetPosition();

            int placeToGo = connections.get(currentPos).get(0);

            if (domainBoard.getSquareAt(currentPos).getType().equals("RailroadTransitSquare") && regularDiceTotal % 2 == 0) {
                if (connections.get(currentPos).get(1) != -1) placeToGo = connections.get(currentPos).get(1);
                else System.out.println("[GameEngine]: There seems to be a problem.");
            }

            //playerController.changeCurrentPosition(currentPlayer, placeToGo);
            path.add(placeToGo);
            target = placeToGo;
            playerController.movePlayer(target);
            
        }

        publishEvent("path/" + path);
        playerController.movePlayer(target);
        evaluateSquare();
        return path;
    }

    public ArrayList<Integer> rollDice() {
        regularDie.roll(2);
        speedDie.roll(1);
        ArrayList<Integer> list = new ArrayList<Integer>(3);
        list.add(regularDie.getLastValues().get(0));
        list.add(regularDie.getLastValues().get(1));
        list.add(speedDie.getLastValues().get(0));
        return list;
    }
    
    public ArrayList<Integer> roll3Dice() {
        regularDie.roll(3);
        ArrayList<Integer> list = new ArrayList<Integer>(3);
        list.add(regularDie.getLastValues().get(0));
        list.add(regularDie.getLastValues().get(1));
        list.add(regularDie.getLastValues().get(2));
        return list;
    }

    public int calculateMoveAmount() {
        int total = 0;
        total += regularDie.getLastValues().get(0);
        total += regularDie.getLastValues().get(1);
        return total;
    }

    public void drawCard() {
        Player currentPlayer = playerController.getCurrentPlayer();
        Square square = domainBoard.getSquareAt(currentPlayer.getTargetPosition());
        Card card;
        String type = square.getType();

        if (type.equals("CommunityChestCardSquare") || type.equals("ChanceCardSquare")) {
            if (type.equals("CommunityChestCardSquare")) {
                card = domainBoard.getCCCards().element();
            } 
            else if(type.equals("ChanceCardSquare")) {
                card = domainBoard.getChanceCards().element();
            }
            else{
            	card = domainBoard.getRoll3Cards().element();
            }
            publishEvent("message/" + "[System]: " + currentPlayer.getName() + " drew " + card.getTitle());
            if (card.getImmediate()) {
                card.playCard(this);
                System.out.println();
            } else {
                playerController.addCardToCurrentPlayer(card);
            }
            nextTurn();
        } else {
            System.out.println("Error: drawCard has been called while player is outside Community Chest or Chance squares.");
        }
    }

    public void improveProperty() {
    	
    }

    public void buy() {

    	domainBoard.getSquareAt(86).evaluateSquare(this);;
    	
        Player currentPlayer = playerController.getCurrentPlayer();
        Square square = domainBoard.getSquareAt(currentPlayer.getTargetPosition());
        String type = square.getType();

        if (type.equals("PropertySquare") && !((PropertySquare) square).isOwned()) {
            if (moneyController.hasEnoughMoney(currentPlayer, ((PropertySquare) square).getPrice())) {
                moneyController.decreaseMoney(currentPlayer, ((PropertySquare) square).getPrice());
                playerController.upgradePropertyList((PropertySquare) square, currentPlayer);
                ((PropertySquare) square).setOwner(currentPlayer);
            }
        } else if (type.equals("UtilitySquare")) {
            UtilitySquare utilitySquare = (UtilitySquare) square;
            if (moneyController.hasEnoughMoney(currentPlayer, utilitySquare.getPrice())) {
                moneyController.decreaseMoney(currentPlayer, utilitySquare.getPrice());
                playerController.upgradeUtilityList(utilitySquare, currentPlayer);
                utilitySquare.setOwner(currentPlayer);
            }
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

    public void newClient(String clientId) {
        serverInfo.addClient(clientId);
        publishEvent("newClient");
    }

    public PlayerController getPlayerController() {
        return playerController;
    }

    public MoneyController getMoneyController() {
        return moneyController;
    }


    public void payRent(Player player, Player owner, int amount) {
        moneyController.decreaseMoney(player, amount);
        if (player.getMoney() < 0) {
            player.setMoney(0);
            moneyController.increaseMoney(owner, player.getMoney());
            publishEvent("bankrupt");
        } else {
            moneyController.increaseMoney(owner, amount);
        }
        nextTurn();
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
        Square square = domainBoard.getSquareAt(currentPlayer.getTargetPosition());
        square.evaluateSquare(this);
    }

    public void loadData() {
        //serverInfo.setClientList();
    }

    public void resume() {
        gameIsPaused = false;
        System.out.println("Game Engine: Game resumed");
        publishEvent("resume");
    }

    public void pause() {
        gameIsPaused = true;
        System.out.println("Game Engine: Game paused");
        publishEvent("pause");
    }

    public boolean isMyTurn() {
        Player player = playerController.getCurrentPlayer();
        if (player != null && (player.getClientID() == serverInfo.getClientID())) {
            return true;
        }
        return false;
    }

	public void save() {
		publishEvent("save");
	}

    public void setCurrentPlayer(Player p) {
        playerController.setCurrentPlayerIndex(playerController.getPlayers().indexOf(p));
    }

}
