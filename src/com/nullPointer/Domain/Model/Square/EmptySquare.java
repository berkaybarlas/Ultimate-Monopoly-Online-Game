package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Model.GameEngine;

public class EmptySquare extends Square {

	public EmptySquare(String n, String t) {
		super(n, t);

	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		gameEngine.publishEvent("empty");
	}

}
