package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;

public class ChanceForeclosedPropertySale extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();

	public ChanceForeclosedPropertySale(String title, boolean isImmediate) {
		super(title, isImmediate);

	}

	@Override
	public void playCard(GameEngine gameEngine) {
		while(gameEngine.getPlayerController().getChosen() == null){
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		playerController.setChosen(null);
	}

}
