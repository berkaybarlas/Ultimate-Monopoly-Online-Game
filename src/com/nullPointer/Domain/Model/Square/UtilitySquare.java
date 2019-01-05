package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class UtilitySquare extends Square {

	private Player owner;
	private int price;
	
	public UtilitySquare(String n, String t, int p) {
		super(n, t);
		this.price = p;

	}

	public int calculateRent(int diceVal) {
		int multiplier = 0;
		switch(GameEngine.getInstance().getPlayerController().getCurrentPlayer().getUtilityList().size()) {
			case 1:
				multiplier = 4;
			case 2:
				multiplier = 10;
			case 3:
				multiplier = 20;
			case 4:
				multiplier = 40;
			case 5:
				multiplier = 80;
			case 6:
				multiplier = 100;
			case 7:
				multiplier = 120;
			case 8:
				multiplier = 150;
		}
		return diceVal * multiplier;
	}
	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		if(this.getOwner() == null) {
			gameEngine.publishEvent("buy");
		} else {
			int diceVal = gameEngine.getRegularDie().getLastValues().get(0) + gameEngine.getRegularDie().getLastValues().get(1) + gameEngine.getSpeedDie().getLastValues().get(0);
			gameEngine.payRent(currentPlayer, this.getOwner(), calculateRent(diceVal));
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
	@Override
    public String toString() {
        return getName();
    }

}
