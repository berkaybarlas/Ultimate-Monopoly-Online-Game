package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.*;

public class ChanceAdvanceToPayCorner extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();


	public ChanceAdvanceToPayCorner(String title, boolean isImmediate) {
		super(title, isImmediate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void playCard(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		int go = 56;
		int goMoney = 200;
		int payDay = 28;
		int payDayMoney = 400;
		int bonus = 102;
		int bonusMoney = 300;

		Player currentPlayer = playerController.getCurrentPlayer();
		int currentPos = currentPlayer.getPosition();

		if (currentPos <= 55 && currentPos >= 0)
		{
			gameEngine.publishEvent("teleport" + payDay);
			playerController.movePlayer(payDay);
			moneyController.increaseMoney(currentPlayer, payDayMoney);
		}
		else if (currentPos <= 95 && currentPos >= 56)
		{
			gameEngine.publishEvent("teleport" + go);
			playerController.movePlayer(go);
			moneyController.increaseMoney(currentPlayer, goMoney);
		}
		else if (currentPos <= 119 && currentPos >= 96)
		{
			gameEngine.publishEvent("teleport" + bonus);
			playerController.movePlayer(bonus);
			moneyController.increaseMoney(currentPlayer, bonusMoney);
		}
		else
		{
			System.out.println("Error in drawing \"Advance to Pay Corner\" card\n");
		}
	}

}
