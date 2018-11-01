package com.nullPointer.Model;
import java.awt.*;
import java.util.List;
import java.util.*;

public class Player {
	
	private String name;
	private int position;
	private int money;
	private HashMap<Color, List<Card>> propertyCardsMap;
	private List<Card> otherCards;
	private Pawn pawn;
	private boolean inJail;
	
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

	public int getMoney() {
		return money;
	}

	public HashMap<Color, List<Card>> getPropertyCardsMap() {
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
	
}
