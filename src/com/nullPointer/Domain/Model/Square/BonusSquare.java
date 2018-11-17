package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class BonusSquare extends Square {

	public BonusSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		gameEngine.getMoneyController().increaseMoney(currentPlayer, 300);
		
	}

}
