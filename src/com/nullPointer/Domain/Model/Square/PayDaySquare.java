package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class PayDaySquare extends Square {

	public PayDaySquare(String n, String t) {
		super(n, t);
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		MoneyController.getInstance().increaseMoney(currentPlayer, 400);
		// Not so sure about this. The explanations aren't clear enough, but it says that if someone moves directly to this square,
		// they should collect $400.
		gameEngine.publishEvent("empty");
	}

}
