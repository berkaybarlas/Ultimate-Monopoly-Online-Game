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

import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private static int ownedUtilities = 0;
	private DomainBoard domainBoard;
	private boolean gameIsPaused = false;
	private boolean roll3 = false;
	private int chosenSquareIndex = -1;

	public int getChosenSquareIndex() {
		return chosenSquareIndex;
	}

	public void setChosenSquareIndex(int chosenSquareIndex) {
		this.chosenSquareIndex = chosenSquareIndex;
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

	public void initPlayers() {
		publishEvent("initializePawns");
		publishEvent("initializePlayers");
	}

	public void addPlayer(Player newPlayer) {
		playerController.addPlayer(newPlayer);
		newPlayer.addRoll3Card((Roll3) domainBoard.getRoll3Cards().poll());
		publishEvent("newPlayer");
	}

	public void startGame() {
		publishEvent("screen/gameScreen");
		initPlayers();
		publishEvent("rollDice");
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
		playerController.setPath(currentPlayer, path);
		for (int j = 0; j < path.size() - 1; j++) {
			int i = path.get(j);
			Square onTheWay = squares.get(i);
			if (onTheWay.getFlyover()) {
				onTheWay.evaluateSquare(this, "flyover");
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
                System.out.println();
            } else {
                playerController.addCardToCurrentPlayer(card);
            }
            publishEvent("endTurn");
        } else {
            System.out.println("Error: drawCard has been called while player is outside Community Chest or Chance squares.");
        }
    }

	public void improveProperty() {

		while(getChosenSquareIndex() == -1){
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
		Player currentPlayer = playerController.getCurrentPlayer();
		Square square = domainBoard.getSquareAt(getChosenSquareIndex());

		if(square.getType().equals("PropertySquare")){

			PropertySquare propertySquare = ((PropertySquare) square);
			HashMap<String, ArrayList<PropertySquare>> propertyCardsMap = currentPlayer.getPropertyCardsMap();

			if(propertyCardsMap !=null){
				if(propertyCardsMap.get(propertySquare.getColor()) !=null){
					if(!propertySquare.hasHotel() && !propertySquare.hasSkyscraper() && propertySquare.numHouses()!=4){
						if(propertyCardsMap.get(propertySquare.getColor()).size()>=2){
							propertySquare.improve();
							publishEvent("improve");
						}
					}
					else if(propertySquare.hasHotel() || propertySquare.numHouses()==4){
						if(propertyCardsMap.get(propertySquare.getColor()).size()>=3){
							propertySquare.improve();
							publishEvent("improve");
						}
					}
				}
			}	
		}
		chosenSquareIndex =-1;
	}

	public void improveSelectedProperty(PropertySquare p)    // This is added only for bots to use
	{
		Player currentPlayer = playerController.getCurrentPlayer();
		HashMap<String, ArrayList<PropertySquare>> propertyCardsMap = currentPlayer.getPropertyCardsMap();
		if(!p.hasHotel() && !p.hasSkyscraper() && p.numHouses()!=4){
			p.improve();
			publishEvent("improve");
		}
		else if(p.hasHotel() || p.numHouses()==4){
			if(propertyCardsMap.get(p.getColor()).size()==3){
				p.improve();
				publishEvent("improve");
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
		if(square.getType() !="ChanceCardSquare" && square.getType() != "CommunityChestCardSquare"){
			publishEvent("endTurn");
		}
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
		if (player != null && (player.getClientID().equals(serverInfo.getClientID()))) {
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


    public boolean amIBot()
    {
        Player current = playerController.getCurrentPlayer();
        if (current != null && current.isBot())
        {
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
