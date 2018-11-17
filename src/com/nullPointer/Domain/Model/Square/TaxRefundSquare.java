package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class TaxRefundSquare extends Square {

	public TaxRefundSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		int poolMoney =  gameEngine.getMoneyController().getPoolMoney();
		gameEngine.getMoneyController().increaseMoney(currentPlayer,poolMoney/2);
	}

}
