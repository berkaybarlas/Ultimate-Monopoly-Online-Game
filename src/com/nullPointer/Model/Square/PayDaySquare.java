package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class PayDaySquare extends Square {

	public PayDaySquare(String name, String type, int position, int layer) {
		super(name, type, position, layer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		gameEngine.getMoneyController().increaseMoney(gameEngine.getPlayerController().getCurrentPlayer(), 400);
		// Not so sure about this. The explanations aren't clear enough, but it says that if someone moves directly to this square,
		// they should collect $400.
		
	}

}
