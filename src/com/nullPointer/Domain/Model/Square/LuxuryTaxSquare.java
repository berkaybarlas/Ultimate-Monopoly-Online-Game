package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class LuxuryTaxSquare extends Square {

	public LuxuryTaxSquare(String name, String type) {
		super(name, type);
	}

	/**
	 * @requires gameEngine != null
	 * @modifies gameEngine.getPlayerController().getCurrentPlayer()
	 * @effects reduces current player's money by 75.
	 * @param gameEngine engine that controls the system
	 */
	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		MoneyController.getInstance().decreaseMoney(currentPlayer, 75);
		gameEngine.publishEvent("empty");
	}

}
