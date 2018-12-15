package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class GoSquare extends Square {

	public GoSquare(String n, String t) {
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
			this.evaluateSquare(gg);
		}
		else
		{
			throw new IllegalArgumentException("Illegal argument: " + args);
		}
	}

}
