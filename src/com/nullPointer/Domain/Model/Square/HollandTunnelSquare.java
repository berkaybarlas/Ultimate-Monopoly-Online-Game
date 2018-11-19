package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;

public class HollandTunnelSquare extends Square {

	public HollandTunnelSquare(String n, String t) {
		super(n, t);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		gameEngine.nextTurn();
	}

}
