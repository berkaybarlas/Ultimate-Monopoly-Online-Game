package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;

public class PayDaySquare extends Square {

	public PayDaySquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		gameEngine.getMoneyController().increaseMoney(gameEngine.getPlayerController().getCurrentPlayer(), 400);
		// Not so sure about this. The explanations aren't clear enough, but it says that if someone moves directly to this square,
		// they should collect $400.
		
	}

}
