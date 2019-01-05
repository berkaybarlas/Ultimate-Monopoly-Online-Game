package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Model.GameEngine;

public abstract class CommunityChestCard extends Card {

	public CommunityChestCard(String title, boolean isImmediate) {
		super(title, isImmediate);

	}
	public abstract void playCard(GameEngine gameEngine);
}
