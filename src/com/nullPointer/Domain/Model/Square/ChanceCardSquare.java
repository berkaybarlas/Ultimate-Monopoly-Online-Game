package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;

public class ChanceCardSquare extends Square {

	public ChanceCardSquare(String name, String type) {
		super(name, type);
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		// make them draw some card? 
		// maybe we can keep a chance card deck here, and make the player draw a card and call its playCard()
		// open to suggestions
		gameEngine.publishEvent("drawCard");
		
	}

}
