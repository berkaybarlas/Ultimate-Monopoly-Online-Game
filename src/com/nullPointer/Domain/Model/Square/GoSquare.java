package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class GoSquare extends Square {

	public GoSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		//Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		//gameEngine.getMoneyController().increaseMoney(currentPlayer, 300);
		gameEngine.nextTurn();
	}

}
