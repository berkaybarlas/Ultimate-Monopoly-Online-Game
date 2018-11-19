package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Model.GameEngine;

public abstract class ChanceCard extends Card {

	
	public ChanceCard(String title, boolean isImmediate) {
		super(title, isImmediate);
	}
	public abstract void playCard(GameEngine gameEngine);
}
