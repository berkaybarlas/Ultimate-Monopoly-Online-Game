package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class BonusSquare extends Square {

	public BonusSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		gameEngine.getMoneyController().increaseMoney(gameEngine.getPlayerController().getCurrentPlayer(), 300);
		
	}

}
