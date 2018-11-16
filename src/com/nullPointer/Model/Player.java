package com.nullPointer.Model;
import java.util.List;

import com.nullPointer.Model.Cards.Card;
import com.nullPointer.Model.Square.PropertySquare;
import com.nullPointer.Model.Square.Square;
import com.nullPointer.Model.Square.UtilitySquare;

import java.util.*;

public class Player {
	
	private String name;
	private int position = 0;
	private int layer = 1;
	private int targetPosition = 0;
	private int money = 3200;
	private HashMap<String, ArrayList<PropertySquare>> propertyCardsMap;
	private ArrayList<UtilitySquare> utilityList;
	private ArrayList<Card> otherCards;
	private Pawn pawn;
	private boolean inJail;
	private boolean direction = true;
	
	public Player(){
		this.name="";
	}
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getPosition() {
		return position;
	}
	
	public void setPosition(int newPosition) {
		position=newPosition;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}

	public int getTargetPosition() {
        return targetPosition;
    }

    public void setTargetPosition(int amount) {
        this.targetPosition = position + amount;
    }

    public int getMoney() {
		return money;
	}

	public void setMoney(int newmoney) {
		money=newmoney;
	}

	public HashMap<String, ArrayList<PropertySquare>> getPropertyCardsMap() {
		return propertyCardsMap;
	}
	
	public ArrayList<Card> getOtherCards() {
		return otherCards;
	}

	public Pawn getPawn() {
		return pawn;
	}

	public boolean isInJail() {
		return inJail;
	}

	public void setinJail(boolean b) {
		inJail=b;
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
	@Override
	public String toString() {
		String temp = "";
		return 	"Player name: " + name + "\n"+ 
				"Money: " + money + "\n"+
				"Owned properties: " + propertyCardsMap + "\n"+
				"Owned utilities: " + utilityList;
	}
	
}
