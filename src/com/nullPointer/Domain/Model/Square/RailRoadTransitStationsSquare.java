package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;

public class RailRoadTransitStationsSquare extends Square {

	public RailRoadTransitStationsSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		gameEngine.nextTurn();
		System.out.println("here");
	}

}
