package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Model.GameEngine;

public interface iCard {
	String name = "WTF is going on";
	String type = "Help";
	void playCard(GameEngine gameEngine);
}
