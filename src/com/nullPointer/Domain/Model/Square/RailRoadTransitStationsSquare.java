package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class RailRoadTransitStationsSquare extends Square {

	public RailRoadTransitStationsSquare(String n, String t) {
		super(n, t);

	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		System.out.println("[TransitSquare] Player: " + currentPlayer.getName() + " at " + currentPlayer.getPosition());
		gameEngine.publishEvent("empty");
	}

}
