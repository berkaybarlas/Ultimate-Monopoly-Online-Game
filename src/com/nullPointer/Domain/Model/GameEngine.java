package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Controller.CommunicationController;
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

/**
 * @overview This class contains the main flow of the game logic, i.e. the game controller.
 * Most of the game logic related operations are done by this class and other components are
 * notified by this class.
 */
public class GameEngine {
    private RegularDie regularDie = RegularDie.getInstance();
    private SpeedDie speedDie = SpeedDie.getInstance();
    private PlayerController playerController = PlayerController.getInstance();
    private MoneyController moneyController = MoneyController.getInstance();
    private ServerInfo serverInfo = ServerInfo.getInstance();
    private DomainBoard domainBoard;
    private boolean paused = false;
    private boolean roll3 = false;
    private boolean gameStarted = false;
    private int chosenSquareIndex = -1;
    int doublesCnt = 0;
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

    public void setSquareUnselected() {
        this.chosenSquareIndex = -1;
    }

    public void setChosenSquareIndex(int squareIndex) {
        this.chosenSquareIndex = squareIndex;
    }

    public int getChosenSquareIndex() {
        return chosenSquareIndex;
    }

    public boolean getRoll3() {
        return roll3;
    }

    public void setRoll3(boolean set) {
        roll3 = set;
    }

    public DomainBoard getDomainBoard() {
        return domainBoard;
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void publishEvent(String message) {
        observers.forEach(listener -> listener.onEvent(message));
    }

    public void initPlayers() {
        publishEvent("initializePawns");
        publishEvent("initializePlayers");
    }

    public void addPlayer(Player newPlayer) {
        playerController.addPlayer(newPlayer);
        newPlayer.addRoll3Card((Roll3) domainBoard.getRoll3Cards().poll());
        publishEvent("newPlayer");
    }

    public void gameStopped() {
        gameStarted = false;
    }

    public void startGame() {
        if (!isGameStarted()) {
            publishEvent("screen/gameScreen");
            initPlayers();
            publishEvent("rollDice");
        }
        gameStarted();
    }

    public void gameStarted() {
        gameStarted = true;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public LinkedList<Integer> calculatePath() {
        publishEvent("refresh");
        LinkedList<Integer> path = new LinkedList<Integer>();
        HashMap<Integer, Square> squares = domainBoard.getSquareMap();
        HashMap<Integer, ArrayList<Integer>> connections = domainBoard.getConnectionsMap();
        Player currentPlayer = playerController.getCurrentPlayer();
        int currentPos;
        int regularDiceTotal = calculateMoveAmount();
        int target = -2;
        if (currentPlayer != null) {

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

            playerController.movePlayer(target);

            if (path.getLast() == 114) {
                path.add(14);
                playerController.movePlayer(14);
            } else if (path.getLast() == 14) {
                path.add(114);
                playerController.movePlayer(114);
            }

            publishEvent("path/" + path);

            playerController.setPath(currentPlayer, path);
            for (int j = 0; j < path.size() - 1; j++) {
                int i = path.get(j);
                Square onTheWay = squares.get(i);
                if (onTheWay.getFlyover()) {
                    onTheWay.evaluateSquare(this, "flyover");
                }
            }
        }

        evaluateSquare();
        return path;
    }

    /**
     * @param -
     * @requires -
     * @modifies regularDie and speedDie
     * @effects lastValues ArrayLists of regularDie and speedDie change according
     * to the result of roll() methods
     */
    public ArrayList<Integer> rollDice() {
        regularDie.roll(2);
        speedDie.roll(1);
        ArrayList<Integer> list = new ArrayList<Integer>(3);
        list.add(regularDie.getLastValues().get(0));
        list.add(regularDie.getLastValues().get(1));
        list.add(speedDie.getLastValues().get(0));
        if (regularDie.getLastValues().get(0) == regularDie.getLastValues().get(1)) {
            doublesCnt++;
            if (doublesCnt == 3) {
                CommunicationController.getInstance().sendClientMessage("penalty");
            }
        }
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

    /**
     * @param -
     * @requires lastValues ArrayLists are not null and regularDie's lastValues
     * ArrayList contains at least 2 elements
     * @modifies -
     * @effects -
     */
    public int calculateMoveAmount() {
        int total = 0;
        total += regularDie.getLastValues().get(0);
        total += regularDie.getLastValues().get(1);
        return total;
    }

    /**
     * @param -
     * @requires 1)playerController's currentPlayer's not being null
     * 2)currentPlayer's targetPosition's not being null
     * 3)domainBoard's not being null
     * 4)domainBoard's containing a not null Square in targetPosition
     * in squaresMap
     * @modifies -CCCards or ChanceCards or Roll3Cards queues according to the type of the Square
     * @effects takes the first element from the queue and if it is immediate plays else puts it into the cards ArrayList
     */
    public void drawCard() {
        Player currentPlayer = playerController.getCurrentPlayer();
        Square square = domainBoard.getSquareAt(currentPlayer.getTargetPosition());
        Card card;
        String type = square.getType();

        if (type.equals("CommunityChestCardSquare") || type.equals("ChanceCardSquare")) {
            if (type.equals("CommunityChestCardSquare")) {
                card = domainBoard.getCCCards().remove();
            } else if (type.equals("ChanceCardSquare")) {
                card = domainBoard.getChanceCards().remove();
            } else {
                card = domainBoard.getRoll3Cards().remove();
            }
            if(card != null){
                publishEvent("message/" + "[System]: " + currentPlayer.getName() + " drew " + card.getTitle());
                if (card.getImmediate()) {
                    card.playCard(this);
                    if (type.equals("CommunityChestCardSquare")) {
                        domainBoard.getCCCards().add(card);
                    } else if (type.equals("ChanceCardSquare")) {
                        domainBoard.getChanceCards().add(card);
                    } else {
                        domainBoard.getRoll3Cards().add(card);
                    }
                } else {
                    playerController.addCardToCurrentPlayer(card);
                }
            }

            if (regularDie.getLastValues().get(0) == regularDie.getLastValues().get(1)) {
                publishEvent("doubles");
            } else {
                publishEvent("endTurn");
            }
        } else {
            System.out.println("Error: drawCard has been called while player is outside Community Chest or Chance squares.");
        }
    }

    public void downgradeProperty(int propertySquareIndex) {
        Square currentSquare = getDomainBoard().getSquareAt(propertySquareIndex);
        PropertySquare propertySquare = ((PropertySquare) currentSquare);
        if (propertySquare.isOwned()) {
            if (propertySquare.numHouses() > 0 || propertySquare.hasHotel() || propertySquare.hasSkyscraper())
                propertySquare.downgrade();
        }
        setSquareUnselected();
        publishEvent("refresh");
    }

    public void improveProperty(int propertySquareIndex) {
        Square square = domainBoard.getSquareAt(propertySquareIndex);
        PropertySquare propertySquare = ((PropertySquare) square);
        System.out.println("Chose " + propertySquare.getName());
        Player currentPlayer = playerController.getCurrentPlayer();
        MoneyController.getInstance().decreaseMoney(currentPlayer, propertySquare.getRentList()[8]);
        propertySquare.improve();
        setSquareUnselected();
        publishEvent("refresh");
    }

    public boolean tryImproveProperty() {
        if (!isMyTurn()) {
            return false;
        }

        while (chosenSquareIndex == -1) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException er) {
                er.printStackTrace();
            }
        }

        Player currentPlayer = playerController.getCurrentPlayer();
        Square square = domainBoard.getSquareAt(getChosenSquareIndex());

        if (square.getType().equals("PropertySquare")) {

            PropertySquare propertySquare = ((PropertySquare) square);
            HashMap<String, ArrayList<PropertySquare>> propertyCardsMap = currentPlayer.getPropertyCardsMap();

            if (propertyCardsMap != null) {
                if (propertyCardsMap.get(propertySquare.getColor()) != null) {
                    if (!propertySquare.hasHotel() && !propertySquare.hasSkyscraper() && propertySquare.numHouses() != 4) {
                        if (propertyCardsMap.get(propertySquare.getColor()).size() >= 2) {
                            publishEvent("improve/" + chosenSquareIndex);
                            return true;
                        }
                    } else if (propertySquare.hasHotel() || propertySquare.numHouses() == 4) {
                        if (propertyCardsMap.get(propertySquare.getColor()).size() >= 3) {
                            publishEvent("improve/" + chosenSquareIndex);
                            return true;
                        }
                    }
                }
            }
        }

        setSquareUnselected();
        return false;
    }

    public void improveSelectedProperty(PropertySquare p)    // This is added only for bots to use
    {
        HashMap<Integer, Square> squareMap = domainBoard.getSquareMap();
        for (int i = 0; i < squareMap.size(); i++) {
            if (squareMap.get(i).getName().equals(p.getName())) {
                chosenSquareIndex = i;
                tryImproveProperty();
                return;
            }
        }
    }

    /**
     * @param -
     * @requires 1)currentPlayer's not being null
     * 2)currentPlayer's targetPosition's not being null
     * 3)domainBoard's not being null
     * 4)domainBoard's containing a not null Square in targetPosition
     * in squaresMap
     * @modifies currentPlayer's money, propertyList or utilityList, square's owner
     * @effects currentPlayer's money decreases if she/he has, the owner of the square becomes currentPlayer
     * the square is added to propertyList or utilityList of currentPlayer
     */
    public void buy() {
        //playerController.getCurrentPlayer().setTargetPosition(86);
        //domainBoard.getSquareAt(86).evaluateSquare(this);
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
    }

    public void nextTurn() {
        playerController.nextPlayer();
        doublesCnt = 0;
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

    public void removeClient(String clientId) {
        serverInfo.removeClient(clientId);
        publishEvent("newClient");
    }


    /**
     * @param player, owner, amount
     * @requires player's and owner's not being null
     * @modifies player's and owner's money if player has any money
     * @effects if player has money, her/his money is decreased and player's increased
     * else an bankrupt event is published
     */
    public void payRent(Player player, Player owner, int amount) {
        moneyController.decreaseMoney(player, amount);
        if (player.getMoney() < 0) {
            player.setMoney(0);
            moneyController.increaseMoney(owner, player.getMoney());
            publishEvent("bankrupt");
        } else {
            moneyController.increaseMoney(owner, amount);
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

    }

    public void evaluateSquare() {
        Player currentPlayer = playerController.getCurrentPlayer();
        Square square = domainBoard.getSquareAt(currentPlayer.getTargetPosition());
        square.evaluateSquare(this);
        if (square.getType() != "ChanceCardSquare" && square.getType() != "CommunityChestCardSquare") {
            if (regularDie.getLastValues().get(0) == regularDie.getLastValues().get(1)) {
                publishEvent("doubles");
            } else {
                publishEvent("endTurn");
            }
        }

    }

    public void loadData() {
        //serverInfo.setClientList();
    }

    public void resume() {
        paused = false;
        System.out.println("Game Engine: Game resumed");
        publishEvent("resume");
    }

    public void pause() {
        paused = true;
        System.out.println("Game Engine: Game paused");
        publishEvent("pause");
    }

    public boolean isPaused() {
        return paused;
    }

    public boolean isMyTurn() {
        Player player = playerController.getCurrentPlayer();
        if (player == null) {
            return false;
        }
        if (player.getClientID().equals(serverInfo.getClientID())) {
            return true;
        }

        if (!serverInfo.isOnline(player.getClientID()) && serverInfo.isServer()) {
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

    public boolean isBot() {
        Player currentPlayer = playerController.getCurrentPlayer();
        if (currentPlayer == null) {
            return false;
        }
        if (currentPlayer != null && currentPlayer.isBot()) {
            return true;
        }
        if (!serverInfo.isOnline(currentPlayer.getClientID())) {
            currentPlayer.setBot();
            return true;
        }
        return false;
    }

    public String toString() {
        return "PlayerController: " + playerController.toString() + "/n" +
                "MoneyController: " + moneyController.toString() + "/n" +
                "ServerInfo" + serverInfo.toString();
    }

    public boolean repOk() {
        if (regularDie != null && speedDie != null && playerController != null && moneyController != null
                && serverInfo != null && domainBoard != null) {
            return true;
        }
        return false;
    }

}
