package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Model.GameEngine;

public class ChanceHolidayBonus extends ChanceCard {

	public ChanceHolidayBonus(String title, boolean isImmediate) {
		super(title, isImmediate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void playCard(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		gameEngine.getMoneyController().increaseMoney(gameEngine.getPlayerController().getCurrentPlayer(), 100);
	}

}
