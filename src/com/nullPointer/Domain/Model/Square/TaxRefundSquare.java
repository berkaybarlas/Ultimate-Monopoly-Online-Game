package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class TaxRefundSquare extends Square {

	private MoneyController moneyController = MoneyController.getInstance();
	public TaxRefundSquare(String n, String t) {
		super(n, t);

	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		Player currentPlayer = PlayerController.getInstance().getCurrentPlayer();
		int poolMoney =  moneyController.getPoolMoney();
		moneyController.increaseMoney(currentPlayer,poolMoney/2);
		gameEngine.publishEvent("empty");
	}

}
