package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;

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
