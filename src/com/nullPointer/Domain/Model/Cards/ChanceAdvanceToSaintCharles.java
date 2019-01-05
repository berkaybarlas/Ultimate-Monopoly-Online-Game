package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.*;

public class ChanceAdvanceToSaintCharles extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();


	public ChanceAdvanceToSaintCharles(String title, boolean isImmediate) {
		super(title, isImmediate);

	}

	@Override
	public void playCard(GameEngine gameEngine) {
		int StCharles = 67;
		Player currenPlayer = playerController.getCurrentPlayer();
		gameEngine.publishEvent("teleport" + StCharles);
		playerController.movePlayer(StCharles);
	}

}
