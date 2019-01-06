package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Controller.CommunicationController;
import com.nullPointer.Domain.Controller.PlayerController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class SubwaySquare extends Square {

	public SubwaySquare(String n, String t) {
		super(n, t);

	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		Player currentPlayer = PlayerController.getInstance().getCurrentPlayer();
		if(gameEngine.isMyTurn() && !currentPlayer.isBot()) {
			while (gameEngine.getChosenSquareIndex() == -1) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			CommunicationController.getInstance().sendClientMessage("teleport/"+gameEngine.getChosenSquareIndex());
		}
		gameEngine.setSquareUnselected();
	}

}
