package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class ReverseDirectionSquare extends Square {

	public ReverseDirectionSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		gameEngine.getPlayerController().getCurrentPlayer().setDirection(!gameEngine.getPlayerController().getCurrentPlayer().getDirection());
		// Moving the player should be done according to the direction boolean.
	}

}
