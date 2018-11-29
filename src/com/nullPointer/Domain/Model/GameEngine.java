package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.Square;
import com.nullPointer.Domain.Model.Square.UtilitySquare;
import com.nullPointer.Domain.Server.ServerInfo;
import com.nullPointer.Domain.Observer;

import java.util.ArrayList;

public class GameEngine {
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private MoneyController moneyController = MoneyController.getInstance();
    private ServerInfo serverInfo = ServerInfo.getInstance();
    private static int ownedUtilities = 0;
    private DomainBoard domainBoard;

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
        publishEvent("newPlayer");
    }

    public void startGame() {
        publishEvent("screen/gameScreen");
        initPlayers(2);
        publishEvent("rollDice");
    }

    public void movePlayer() {
        publishEvent("refresh");
        Player currentPlayer = playerController.getCurrentPlayer();
        playerController.movePlayer(calculateMoveAmount(), domainBoard.getSquaresInLayer(currentPlayer.getLayer()).size());
        evaluateSquare();
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

    public int calculateMoveAmount() {
        int total = 0;
        total += regularDie.getLastValues().get(0);
        total += regularDie.getLastValues().get(1);
        return total;
    }

    public void drawCard() {
        Player currentPlayer = playerController.getCurrentPlayer();
        Square square = domainBoard.getSquareInLayerAtPosition(currentPlayer.getLayer(), currentPlayer.getTargetPosition());
        if (square.getType().equals("CommunityChestCardSquare")) {
            Card card = domainBoard.getCCCards().element();
            publishEvent("message/" + "[System]: " + playerController.getCurrentPlayer().getName() + " drew " + card.getTitle());
            if (card.getImmediate()) {
                card.playCard(this);
                System.out.println();
            } else {
                playerController.addCardToCurrentPlayer(card);
            }
            nextTurn();
        } else if (square.getType().equals("ChanceCardSquare")) {
            Card card = domainBoard.getChanceCards().element();
            publishEvent("message/" + "[System]: " + playerController.getCurrentPlayer().getName() + " drew " + card.getTitle());
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

        Player currentPlayer = playerController.getCurrentPlayer();
        Square square = domainBoard.getSquareInLayerAtPosition(currentPlayer.getLayer(), currentPlayer.getTargetPosition());
        if (square.getType().equals("PropertySquare") && ((PropertySquare) square).getOwner() == null) {
            if (moneyController.hasEnoughMoney(currentPlayer, ((PropertySquare) square).getPrice())) {
                moneyController.decreaseMoney(currentPlayer, ((PropertySquare) square).getPrice());
                playerController.upgradePropertyList((PropertySquare) square, currentPlayer);
                ((PropertySquare) square).setOwner(currentPlayer);
            }
        } else if (square.getType().equals("UtilitySquare")) {
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
        moneyController.increaseMoney(owner, amount);
        if (player.getMoney() < 0) {
            publishEvent("bankrupt");
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
        Square square = domainBoard.getSquareInLayerAtPosition(currentPlayer.getLayer(), currentPlayer.getTargetPosition());
        square.evaluateSquare(this);
    }

    public void loadData() {
        //serverInfo.setClientList();
    }
}
