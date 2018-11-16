package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class ReverseDirectionSquare extends Square {

	public ReverseDirectionSquare(String name, String type, int position, int layer) {
		super(name, type, position, layer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		gameEngine.getPlayerController().getCurrentPlayer().setDirection(!gameEngine.getPlayerController().getCurrentPlayer().getDirection());
		// Moving the player should be done according to the direction boolean.
	}

}
