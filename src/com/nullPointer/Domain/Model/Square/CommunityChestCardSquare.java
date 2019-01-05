package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;

public class CommunityChestCardSquare extends Square {

	public CommunityChestCardSquare(String name, String type) {
		super(name, type);
	}


	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// make them draw some card? 
		// maybe we can keep a community chest card deck here, and make the player draw a card and call its playCard()
		// open to suggestions
		gameEngine.publishEvent("drawCard");
	}

}
