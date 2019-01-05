package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.*;
import com.nullPointer.Domain.Model.Square.Square;

import java.util.HashMap;

public class ChanceGetOutOfJailFree extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();
	boolean firstTime = true;

	public ChanceGetOutOfJailFree(String title, boolean isImmediate) {
		super(title, isImmediate);

	}

	@Override
	public void playCard(GameEngine gameEngine) {
		DomainBoard db = gameEngine.getDomainBoard();
		HashMap<Integer, Square> squares = db.getSquareMap();
		Player currentPlayer = playerController.getCurrentPlayer();
		int pos = currentPlayer.getPosition();
		if (!firstTime)
		{
			if (currentPlayer.isInJail())
			{
				playerController.getOutFromJail();
				currentPlayer.getCardList().remove(this);
				this.firstTime = true;
				db.getChanceCards().add(this);
			}
		}
		else
		{
			currentPlayer.getCardList().add(this);
			firstTime = false;
			db.getChanceCards().remove(this);
		}

	}

}
