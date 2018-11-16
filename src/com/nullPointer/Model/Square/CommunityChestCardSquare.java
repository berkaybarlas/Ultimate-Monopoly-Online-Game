package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class CommunityChestCardSquare extends Square {

	public CommunityChestCardSquare(String name, String type, int position, int layer) {
		super(name, type, position, layer);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		// make them draw some card? 
		// maybe we can keep a community chest card deck here, and make the player draw a card and call its playCard()
		// open to suggestions
		
	}

}
