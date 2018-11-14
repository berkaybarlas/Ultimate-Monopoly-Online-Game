package com.nullPointer.Model.square;

import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class PropertySquare extends Square {
	private Player owner;
	private int price;
	private int rent;
	private String color;
	//inventory=1 if 1 house, 2 if 2 houses, 3 if 3 houses, 4 if 4 houses
	//5 if hotel, 6 if skyscraper
	private int inventory;
	private boolean isMortgaged;
	public PropertySquare(String n, String t, int p, int r) {
		super(n, t);
		owner=new Player();
		price=p;
		rent=r;
		isMortgaged=false;
		inventory=0;

	}
	public String getColor(){
		return color;
	}
	public void setRent(int newAmount){
		rent=newAmount;
	}
	public void mortgage(){
		if(!isMortgaged)
			isMortgaged=true;
	}
	public void improve(){
		//change inventory
	}
	public void downgrade(){
		//change inventory
	}
	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		if(this.getOwner() == null) {
			gameEngine.publishAlarmEvent();
		} else {
			
		}
		
	}
	public int getPrice() {
		return price;
	}
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}
	
}
