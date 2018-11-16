package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class Roll3CardSquare extends Square {

	public Roll3CardSquare(String name, String type, int position, int layer) {
		super(name, type, position, layer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		// make them draw some card? 
		// maybe we can keep a roll 3 card deck here, and make the player draw a card and call its playCard()
		// open to suggestions
		
	}

}
