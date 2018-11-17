package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class ReverseDirectionSquare extends Square {

	public ReverseDirectionSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		currentPlayer.setDirection(!gameEngine.getPlayerController().getCurrentPlayer().getDirection());
		gameEngine.nextTurn();
		// Moving the player should be done according to the direction boolean.
	}

}
