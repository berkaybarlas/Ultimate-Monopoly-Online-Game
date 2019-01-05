package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class CCInsidersEdge extends CommunityChestCard {

	private int layer1begin = 0;
	private int layer1end = 55;
	private int layer2begin = 56;
	private int layer2end = 95;
	private int layer3begin = 96;
	private int layer3end = 119;

	public CCInsidersEdge(String title, boolean isImmediate) {
		super(title, isImmediate);

	}

	@Override
	public void playCard(GameEngine gameEngine) {
		Player currentPlayer = PlayerController.getInstance().getCurrentPlayer();
		int currentSquareIndex = currentPlayer.getTargetPosition();
		MoneyController moneyController = MoneyController.getInstance();
		if(currentSquareIndex >= layer1begin && currentSquareIndex <= layer1end) {
			moneyController.decreaseMoney(currentPlayer,50);
			moneyController.increasePoolMoney(50);
		} else if (currentSquareIndex >= layer3begin && currentSquareIndex <= layer3end) {
			moneyController.increaseMoney(currentPlayer,250);
		}
	}

}
