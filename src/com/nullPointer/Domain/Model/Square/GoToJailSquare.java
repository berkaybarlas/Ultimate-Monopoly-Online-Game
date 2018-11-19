package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;

public class GoToJailSquare extends Square {

	public GoToJailSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		gameEngine.getPlayerController().putInJail();
		// also need a method to put the player on Jail square.
		
	}
}
