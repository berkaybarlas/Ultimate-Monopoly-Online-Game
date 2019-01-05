package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;

public class ChanceHolidayBonus extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();

	private int holidayBonus = 100;

	public ChanceHolidayBonus(String title, boolean isImmediate) {
		super(title, isImmediate);

	}

	@Override
	public void playCard(GameEngine gameEngine) {
		moneyController.increaseMoney(playerController.getCurrentPlayer(), holidayBonus);
		gameEngine.publishEvent("message/" + "[System]: " + playerController.getCurrentPlayer().getName() + " gained " + holidayBonus + "$.");
	}

}
