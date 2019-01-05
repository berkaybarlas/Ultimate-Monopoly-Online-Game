package com.nullPointer.Domain.Model.Cards;

import java.util.ArrayList;

import com.nullPointer.Domain.Model.GameEngine;

public class Roll3 extends Card {
	private ArrayList<Integer> roll3Values;
	public Roll3(String title, boolean isImmediate, int first, int second, int third) {
		super(title, isImmediate);

		roll3Values = new ArrayList<Integer>();
		roll3Values.add(first);
		roll3Values.add(second);
		roll3Values.add(third);
	}

	public ArrayList<Integer> getValues() {
		return roll3Values;
	}
	@Override
	public void playCard(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		gameEngine.publishEvent("Roll3 "+roll3Values.get(0)+" "+roll3Values.get(1)+" "+roll3Values.get(2));
		gameEngine.getPlayerController().getCurrentPlayer().addRoll3Card(this);

	}

}
