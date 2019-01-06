package com.nullPointer.Domain.Model.Square;
import com.nullPointer.Domain.Controller.MoneyController;
import com.nullPointer.Domain.Model.GameEngine;
import com.nullPointer.Domain.Model.Player;

public class IncomeTaxSquare extends Square {

	public IncomeTaxSquare(String n, String t) {
		super(n, t);

	}

	@Override
	public void evaluateSquare(GameEngine gameEngine) {
		// TODO Auto-generated method stub
		Player currentPlayer = gameEngine.getPlayerController().getCurrentPlayer();
		if(currentPlayer.getMoney()*0.2 >= 200)
		{
			MoneyController.getInstance().decreaseMoney(currentPlayer, 200);
		}
		else MoneyController.getInstance().decreaseMoney(currentPlayer, (int) (currentPlayer.getMoney()*0.2));
		System.out.println("Incometax" + currentPlayer.getName() + " " + currentPlayer.getPosition());

		gameEngine.publishEvent("empty");
	}

}
