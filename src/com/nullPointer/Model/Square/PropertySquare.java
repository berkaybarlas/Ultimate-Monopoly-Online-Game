package com.nullPointer.Model.Square;

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
	public int getRent() {
		return rent;
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
		// mortgagelanmýþ square'a gelirse hiçbir þey yapma else'i ekle
		if(this.getOwner() == null) {
			gameEngine.publishEvent("buy");
		} else {
			gameEngine.payRent(gameEngine.getPlayerController().getCurrentPlayer(), this.getRent());
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
