package com.nullPointer.Model.Square;
import com.nullPointer.Model.GameEngine;
import com.nullPointer.Model.Player;

public class SubwaySquare extends Square {

	public SubwaySquare(String name, String type, int position, int layer) {
		super(name, type, position, layer);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		gameEngine.publishEvent("chooseTeleportatDestination");
		
	}

}
