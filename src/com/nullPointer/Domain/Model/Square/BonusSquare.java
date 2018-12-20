package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class BonusSquare extends Square {

	public BonusSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
		this.setFlyover(true);
	}

	/**
	 * @requires gameEngine != null
	 * @modifies gameEngine.getPlayerController.getCurrentPlayer
	 * @effects Increases the current player's money by 300.
	 * @param gameEngine The gameEngine that controls the system.
	 */
	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		gameEngine.getMoneyController().increaseMoney(currentPlayer, 300);
		
	}

	/**
	 *  @requires gameEngine != null, args != null
	 *  @modifies gameEngine.getPlayerController.getCurrentPlayer
	 *  @effects If args = flyover, increases the current player's money by 250.
	 * 	@param gg The gameEngine that controls the system.
	 * 	@param args The string that contains additional information to be considered when evaluating the square.
	 * 	@throws IllegalArgumentException when {@code args} is not an expected string
	 */
	@Override
	public void evaluateSquare(GameEngine gg, String args) throws IllegalArgumentException
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
