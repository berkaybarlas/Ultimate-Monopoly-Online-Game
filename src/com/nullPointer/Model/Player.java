package com.nullPointer.Model;
import java.awt.*;
import java.util.List;
import com.nullPointer.Model.Square.Square;

import java.util.*;

public class Player {
	
	private String name;
	private int position;
	private int money;
	private HashMap<Color, List<Square>> propertyCardsMap;
	private List<Card> otherCards;
	private Pawn pawn;
	private boolean inJail;
	public Player(){
		this.name="";
	}
	public Player(String name, Pawn pawn) {
		this.name = name;
		this.pawn = pawn;
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

	public int getMoney() {
		return money;
	}
	public void setMoney(int newmoney) {
		money=newmoney;
	}

	public HashMap<Color, List<Square>> getPropertyCardsMap() {
		return propertyCardsMap;
	}

	public List<Card> getOtherCards() {
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
	
}
