package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class GoSquare extends Square {

	public GoSquare(String n, String t) {
		super(n, t);

		this.setFlyover(true);
	}


	/**
	 * @requires gameEngine != null
	 * @modifies gameEngine.getPlayerController().getCurrentPlayer()
	 * @effects Increases player's money by 300.
	 * @param gameEngine engine that controls the system
	 */
	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		MoneyController.getInstance().increaseMoney(currentPlayer, 300);
		gameEngine.publishEvent("empty");
	}

	/**
	 * @requires gameEngine != null
	 * @modifies gameEngine.getPlayerController().getCurrentPlayer()
	 * @effects Calls the default evaluation method if args is "flyover".
	 * @param gg engine that controls the system
	 * @param args additional arguments
	 */
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
