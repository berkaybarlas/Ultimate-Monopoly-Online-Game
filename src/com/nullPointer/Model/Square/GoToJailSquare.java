package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class GoToJailSquare extends Square {

	public GoToJailSquare(String name, String type, int position, int layer) {
		super(name, type, position, layer);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		gameEngine.getPlayerController().putInJail();
		// also need a method to put the player on Jail square.
		
	}
}
