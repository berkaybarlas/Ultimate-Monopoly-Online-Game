package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class BonusSquare extends Square {

	public BonusSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
		this.setFlyover(true);
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		gameEngine.getMoneyController().increaseMoney(currentPlayer, 300);
		
	}

	@Override
	public void evaluateSquare(GameEngine gg, String args)
	{
		if (args.equals("flyover"))
		{
			Player currentPlayer = gg.getPlayerController().getCurrentPlayer();
			gg.getMoneyController().increaseMoney(currentPlayer, 250);
		}
		else
		{
			throw new IllegalArgumentException("Illegal argument: " + args);
		}
	}
}
