package com.nullPointer.Domain.Model;

import com.nullPointer.Domain.Model.Cards.Card;
import com.nullPointer.Domain.Model.Cards.Roll3;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.UtilitySquare;

import java.io.Serializable;
import java.util.*;

public class Player implements Serializable {

    private String name;
    private String ClientID;
    private int position = 56;
    private int layer = 1;
    private int targetPosition = 56;
    private int money = 3200;
    private boolean bot = false;
    private HashMap<String, ArrayList<PropertySquare>> propertyCardsMap;
    private ArrayList<PropertySquare> propertySquares;
    private ArrayList<UtilitySquare> utilityList;
    private ArrayList<Card> cardList;
    private boolean inJail;
    private boolean direction = true;
    private int rentMultiplier = 1;
    private LinkedList<Integer> path = null;
    private int botBehaviourNumber = 1;
    private int placeHolder;
    
    private ArrayList<Roll3> roll3Cards = new ArrayList<>();

    public ArrayList<Roll3> getRoll3Cards() {
		return roll3Cards;
	}

	public void setRoll3Cards(ArrayList<Roll3> newRoll3)
    {
        this.roll3Cards = newRoll3;
    }

	public void addRoll3Card(Roll3 roll3) {
		this.roll3Cards.add(roll3);
	}

	public Player(String name, String ClientID, int placeHolder) {
        this.name = name;
		this.ClientID = ClientID;
        propertyCardsMap = new HashMap<>();
        propertySquares = new ArrayList<>();
        utilityList = new ArrayList<>();
        roll3Cards = new ArrayList<>();
        cardList = new ArrayList<>();
        this.placeHolder = placeHolder;
    }

    public Player(String name) {
        this.name = name;
        propertyCardsMap = new HashMap<>();
        propertySquares = new ArrayList<>();
        utilityList = new ArrayList<>();
        cardList = new ArrayList<>();
    }

    public int getRentMultiplier()
    {
        return rentMultiplier;
    }

    public void setRentMultiplier(int newRentMultiplier)
    {
        this.rentMultiplier = newRentMultiplier;
    }

    public String getName() {
        if(bot){
            return "Bot | " + name;
        }
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int newPosition) {
        position = newPosition;
    }

    public int getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(int position) {
        this.targetPosition = position;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int newmoney) {
        money = newmoney;
    }

    public HashMap<String, ArrayList<PropertySquare>> getPropertyCardsMap() {
        return propertyCardsMap;
    }

    public ArrayList<Card> getCardList() {
        return cardList;
    }

    public ArrayList<PropertySquare> getPropertySquares() {
        return propertySquares;
    }

    public boolean isInJail() {
        return inJail;
    }

    public void setinJail(boolean b) {
        inJail = b;
    }

    public boolean getDirection() {
        return direction;
    }

    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    public ArrayList<UtilitySquare> getUtilityList() {
        return this.utilityList;
    }

    public void addSquare(PropertySquare propertySquare) {
        // propertyCardsMap.put(propertySquare)
        propertySquares.add(propertySquare);
        if(propertyCardsMap.get(propertySquare.getColor()) != null) {
            propertyCardsMap.get(propertySquare.getColor()).add(propertySquare);
        } else {
            ArrayList<PropertySquare> pSA = new ArrayList<PropertySquare>();
            propertyCardsMap.put(propertySquare.getColor(),pSA);
            propertyCardsMap.get(propertySquare.getColor()).add(propertySquare);
        }
        System.out.println(propertyCardsMap.get(propertySquare.getColor()));
    }

    public int getPlaceHolder() {
        return placeHolder;
    }

    public void addCard(Card card) {
        cardList.add(card);
    }

    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String clientID) {
        ClientID = clientID;
    }

    public LinkedList<Integer> getPath()
    {
        return path;
    }

    public void setPath(LinkedList<Integer> path)
    {
        this.path = path;
    }

    public boolean repOk() {
        if(name != null && money >= 0 && propertySquares != null && utilityList != null && cardList != null) {
            return  true;
        }
        return false;
    }


    public boolean isBot()
    {
        return this.bot;
    }

    public void setBot()
    {
        this.bot = true;
    }

    public void setPerson()
    {
        this.bot = false;
    }


    @Override
    public String toString() {
        String playerProps = "";
        for(PropertySquare pSq : this.getPropertySquares()) {
            playerProps += pSq.toString() + "\n";
        }
        String playerUtils = "";
        for(UtilitySquare uSq : this.getUtilityList()) {
            playerUtils += uSq.getName() + "\n";
        }
        return "Name: " + this.getName() + "\n" +
                "Money: " + this.getMoney() + "\n"+
                "Properties: \n" + playerProps +
                "Utilities: \n" + playerUtils +
                "Is Bot? \t" + ((isBot()) ? "yes\nBehaviour Index:\t" + getBotBehaviourNumber() : "no") + "\n";

    }

    public int getBotBehaviourNumber() {
        return botBehaviourNumber;
    }

    public void setBotBehaviourNumberManually(int botBehaviourNumber) {
        this.botBehaviourNumber = botBehaviourNumber;
    }

    public void setBotBehaviourNumber() {
        Random rand = new Random();
        this.botBehaviourNumber = rand.nextInt(3) + 1;
    }
}
