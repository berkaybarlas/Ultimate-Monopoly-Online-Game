package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class TaxRefundSquare extends Square {

	public TaxRefundSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		gameEngine.getMoneyController().increaseMoney(gameEngine.getPlayerController().getCurrentPlayer(), gameEngine.getMoneyController().getPoolMoney()/2);
	}

}
