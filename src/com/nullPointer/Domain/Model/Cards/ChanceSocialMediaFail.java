package com.nullPointer.Domain.Model.Cards;

import java.util.ArrayList;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class ChanceSocialMediaFail extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();
	public ChanceSocialMediaFail(String title, boolean isImmediate) {
		super(title, isImmediate);

	}

	@Override
	public void playCard(GameEngine gameEngine) {
		Player currentPlayer = playerController.getCurrentPlayer();
		ArrayList<Player> players= playerController.getPlayers();
		for(int i=0;i<players.size();i++){
			moneyController.decreaseMoney(currentPlayer, 50);
			moneyController.increaseMoney(players.get(i), 50);
		}
		
	}

}
