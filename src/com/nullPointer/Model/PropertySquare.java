package com.nullPointer.Model;

public class PropertySquare extends Square {
	private Player owner;
	private int price;
	private int rent;
	private String color;
	//inventory ??
	private boolean isMortgaged;
	public PropertySquare(String n, String t, int p, int r) {
		super(n, t);
		owner=new Player();
		price=p;
		rent=r;
		isMortgaged=false;

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
	public void evaluateSquare(int diceValue, int speedDieValue, Player player) {
		// TODO Auto-generated method stub

	}
}
