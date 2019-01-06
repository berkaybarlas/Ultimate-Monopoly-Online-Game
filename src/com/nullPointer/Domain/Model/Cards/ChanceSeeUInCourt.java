package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

import java.util.ArrayList;
import java.util.Random;

public class ChanceSeeUInCourt extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();
	
	public ChanceSeeUInCourt(String title, boolean isImmediate) {
		super(title, isImmediate);
	}

	@Override
	public void playCard(GameEngine gameEngine) {
		if(gameEngine.isBot()) {
			ArrayList<Player> players = playerController.getPlayers();
			playerController.setChosen(players.get((new Random().nextInt(players.size()))));
		} else {
			while (gameEngine.getPlayerController().getChosen() == null) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		moneyController.increaseMoney(playerController.getCurrentPlayer(), 250);
		moneyController.decreaseMoney(gameEngine.getPlayerController().getChosen(), 250);
		playerController.setChosen(null);
	}

}
