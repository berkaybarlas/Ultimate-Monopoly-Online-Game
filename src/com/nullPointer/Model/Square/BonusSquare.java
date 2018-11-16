package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class BonusSquare extends Square {

	public BonusSquare(String name, String type, int position, int layer) {
		super(name, type, position, layer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		gameEngine.getMoneyController().increaseMoney(gameEngine.getPlayerController().getCurrentPlayer(), 300);
		
	}

}
