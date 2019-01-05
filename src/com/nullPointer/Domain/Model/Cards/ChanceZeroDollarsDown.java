package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Model.GameEngine;

public class ChanceZeroDollarsDown extends ChanceCard {

	public ChanceZeroDollarsDown(String title, boolean isImmediate) {
		super(title, isImmediate);

	}

	@Override
	public void playCard(GameEngine gameEngine) {

	}

}
