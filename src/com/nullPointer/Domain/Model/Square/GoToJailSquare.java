package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Model.GameEngine;

public class GoToJailSquare extends Square {

	public GoToJailSquare(String n, String t) {
		super(n, t);

	}

	/**
	 * @requires gameEngine != null
	 * @modifies - (currentPlayer is modified by the player controller.)
	 * @effects Sets the jail flag of currentPlayer to the opposite of its current value (i.e. sends it to the jail).
	 * @param gameEngine engine that controls the system
	 */
	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		CommunicationController.getInstance().sendClientMessage("penalty");
		gameEngine.publishEvent("empty");
	}
}
