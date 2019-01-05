package com.nullPointer.Domain.Model.Cards;

import java.util.ArrayList;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Square.PropertySquare;

public class ChancePropertyTaxes extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();
	public ChancePropertyTaxes(String title, boolean isImmediate) {
		super(title, isImmediate);

	}

	@Override
	public void playCard(GameEngine gameEngine) {
		
		 ArrayList<PropertySquare> propertySquares = playerController.getCurrentPlayer().getPropertySquares();
		 for (int i=0;i<propertySquares.size();i++){
			 PropertySquare current = propertySquares.get(i);
			 if(current.getMortgaged() == false)
			 {
				 moneyController.decreaseMoney(playerController.getCurrentPlayer(), 25);
				 moneyController.increasePoolMoney(25);
			 }
				 
		 }
	}

}
