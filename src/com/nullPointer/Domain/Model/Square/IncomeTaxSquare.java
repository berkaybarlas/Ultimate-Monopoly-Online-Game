package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class IncomeTaxSquare extends Square {

	public IncomeTaxSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		System.out.println("Incometax" + currentPlayer.getName() + " " + currentPlayer.getPosition());
		gameEngine.nextTurn();
	}

}
