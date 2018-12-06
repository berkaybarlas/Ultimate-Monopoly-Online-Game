package com.nullPointer.Domain.Model.Cards;

import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.*;
import com.nullPointer.Domain.Model.Square.Square;

import java.util.ArrayList;
import java.util.HashMap;

public class ChanceAdvanceToNearestRailroad extends ChanceCard {
	private MoneyController moneyController = MoneyController.getInstance();
	private PlayerController playerController = PlayerController.getInstance();


	public ChanceAdvanceToNearestRailroad(String title, boolean isImmediate) {
		super(title, isImmediate);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void playCard(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		Player currentPlayer = playerController.getCurrentPlayer();
		DomainBoard db = gameEngine.getDomainBoard();
		HashMap<Integer, ArrayList<Integer>> connections =  db.getConnectionsMap();
		HashMap<Integer, Square> squares = db.getSquareMap();
		int pos = currentPlayer.getPosition();
		while(true)
		{
			if (connections.get(pos).get(1) != -1)
			{
				gameEngine.publishEvent("teleport" + pos);
				playerController.movePlayer(pos);
				// set rent factor, etc.
				currentPlayer.setRentMultiplier(2);
				squares.get(pos).evaluateSquare(gameEngine);
				break;
			}
			else
			{
				pos = connections.get(pos).get(0);
			}
		}
	}

}
