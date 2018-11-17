package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class LuxuryTaxSquare extends Square {

	public LuxuryTaxSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		gameEngine.getMoneyController().decreaseMoney(currentPlayer, 75);
	}

}
