package com.nullPointer.Domain.Model.Cards;

import java.util.ArrayList;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;
import com.nullPointer.Domain.Model.Square.PropertySquare;
import com.nullPointer.Domain.Model.Square.Square;

public class ChanceMakeGeneralRepairs extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();

	public ChanceMakeGeneralRepairs(String title, boolean isImmediate) {
		super(title, isImmediate);

	}

	@Override
	public void playCard(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		Player player = playerController.getCurrentPlayer();
		ArrayList<PropertySquare> propertySquares = player.getPropertySquares();
		for(int i=0;i<propertySquares.size();i++){
			PropertySquare ownedProperty = propertySquares.get(i);
			int rentListIndex = ownedProperty.getRentListIndex();
			if(rentListIndex == 1)
				moneyController.decreaseMoney(playerController.getCurrentPlayer(), 25);
			else if(rentListIndex == 2)
				moneyController.decreaseMoney(playerController.getCurrentPlayer(), 2*25);
			else if(rentListIndex == 3)
				moneyController.decreaseMoney(playerController.getCurrentPlayer(), 3*25);
			else if(rentListIndex == 4)
				moneyController.decreaseMoney(playerController.getCurrentPlayer(), 4*25);
			else if(rentListIndex == 5)
				moneyController.decreaseMoney(playerController.getCurrentPlayer(), 100);
			else if(rentListIndex == 6)
				moneyController.decreaseMoney(playerController.getCurrentPlayer(), 100);
		}
	}

}
